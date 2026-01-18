package slidedeckml.compiler.bnf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Compiler for the updated BNF syntax (docs/BNF.pdf).
 *
 * It parses a Markdown-like input with @deck/@slide headers and emits Slidev slides.md.
 * This implementation is intentionally line-oriented (robust for tomorrow's demo).
 */
public final class BnfCompiler {
  public String compileToSlidevMarkdown(String input) {
    String normalized = normalize(input);
    List<String> lines = splitLines(normalized);

    ParseResult parsed = new Parser(lines).parse();
    return new Emitter().emit(parsed.deck, parsed.slides);
  }

  private static final class ParseResult {
    final Deck deck;
    final List<Slide> slides;

    ParseResult(Deck deck, List<Slide> slides) {
      this.deck = deck;
      this.slides = slides;
    }
  }

  private static final class Deck {
    final LinkedHashMap<String, String> meta = new LinkedHashMap<>();
  }

  private static final class Slide {
    final LinkedHashMap<String, String> meta = new LinkedHashMap<>();
    final List<Block> blocks = new ArrayList<>();
    final Map<String, InteractiveBlock> interactivesById = new HashMap<>();
  }

  private interface Block {
    String render(Slide slide);
  }

  private static final class RawMarkdownBlock implements Block {
    final String markdown;

    RawMarkdownBlock(String markdown) {
      this.markdown = markdown;
    }

    @Override
    public String render(Slide slide) {
      return markdown;
    }
  }

  private static final class RevealedBlock implements Block {
    final int step;
    final Block inner;

    RevealedBlock(int step, Block inner) {
      this.step = step;
      this.inner = inner;
    }

    @Override
    public String render(Slide slide) {
      String body = inner.render(slide).trim();
      if (body.isEmpty())
        return "";
      return "<v-click at=\"" + step + "\">\n" + body + "\n</v-click>\n";
    }
  }

  private static final class SlotBlock implements Block {
    final String slotId;
    final List<SlotEntry> entries;

    SlotBlock(String slotId, List<SlotEntry> entries) {
      this.slotId = slotId;
      this.entries = entries;
    }

    @Override
    public String render(Slide slide) {
      if (entries.isEmpty())
        return "";
      StringBuilder out = new StringBuilder();
      for (int i = 0; i < entries.size(); i++) {
        SlotEntry e = entries.get(i);
        Integer hideAt = (i + 1 < entries.size()) ? entries.get(i + 1).step : null;
        out.append(intervalClickBlock(e.step, hideAt, e.block.render(slide).trim())).append("\n");
      }
      return out.toString();
    }
  }

  private static final class SlotEntry {
    final int step;
    final Block block;

    SlotEntry(int step, Block block) {
      this.step = step;
      this.block = block;
    }
  }

  private static final class StepActionBlock implements Block {
    final int step;
    final String interactiveId;

    StepActionBlock(int step, String interactiveId) {
      this.step = step;
      this.interactiveId = interactiveId;
    }

    @Override
    public String render(Slide slide) {
      InteractiveBlock ib = slide.interactivesById.get(interactiveId);
      if (ib == null)
        return "";
      if (!"ON_DEMAND".equalsIgnoreCase(ib.resultsVisibility))
        return "";
      return "<v-click at=\"" + step + "\">\n" + ib.renderResults() + "\n</v-click>\n";
    }
  }

  private static final class InteractiveBlock implements Block {
    final String kind; // QUIZ or POLL
    final String id;
    String question;
    String resultsVisibility = "ALWAYS";
    String view = "SUMMARY";
    boolean allowMultiple = false;
    String url;
    String resultsEmbed;
    Integer resultsRefreshMs;
    final List<Choice> choices = new ArrayList<>();
    String shortAnswerText;

    InteractiveBlock(String kind, String id) {
      this.kind = kind;
      this.id = id;
    }

    @Override
    public String render(Slide slide) {
      StringBuilder out = new StringBuilder();
      out.append("## ").append(kind).append("\n\n");
      if (question != null && !question.trim().isEmpty()) {
        out.append("**").append(escapeMd(question.trim())).append("**\n\n");
      }

      if (url != null && !url.trim().isEmpty()) {
        String link = url.trim();
        out.append(renderQrBlock(link)).append("\n\n");
        String iframe = googleFormsEmbedUrl(link);
        if (iframe != null) {
          out.append(renderGoogleFormsEmbed(iframe)).append("\n\n");
        }
      }

      if (!choices.isEmpty()) {
        out.append("### Choices\n\n");
        for (Choice c : choices) {
          out.append("- ").append(escapeMd(c.text)).append("\n");
        }
        out.append("\n");
      } else if (shortAnswerText != null && !shortAnswerText.trim().isEmpty()) {
        out.append("### Short answer\n\n");
        out.append(shortAnswerText.trim()).append("\n\n");
      }

      if ("ALWAYS".equalsIgnoreCase(resultsVisibility)) {
        out.append(renderResults()).append("\n");
      } else {
        out.append("_Results: on demand_\n\n");
      }

      return out.toString();
    }

    String renderResults() {
      if ("POLL".equalsIgnoreCase(kind)) {
        if (resultsEmbed != null && !resultsEmbed.trim().isEmpty()) {
          return "### Results\n\n" + renderResultsEmbed() + "\n";
        }
        return "### Results\n\n_(Poll results are collected at runtime)_\n";
      }

      // QUIZ
      StringBuilder out = new StringBuilder();
      out.append("### Answer\n\n");
      boolean anyCorrect = false;
      for (Choice c : choices) {
        if (c.correct) {
          out.append("- ✅ ").append(escapeMd(c.text)).append("\n");
          anyCorrect = true;
        }
      }
      if (!anyCorrect) {
        out.append("_(No correct option marked)_\n");
      }
      if (resultsEmbed != null && !resultsEmbed.trim().isEmpty()) {
        out.append("\n### Live results\n\n").append(renderResultsEmbed()).append("\n");
      }
      return out.toString();
    }

    private String renderResultsEmbed() {
      String src = resultsEmbed.trim();
      int refresh = resultsRefreshMs == null ? 5000 : resultsRefreshMs.intValue();
      if (refresh < 1000) refresh = 1000;
      return "<AutoRefreshFrame src=\"" + escapeAttr(src) + "\" :intervalMs=\"" + refresh + "\" height=\"420\" />";
    }

    private static String renderQrBlock(String link) {
      // Practical approach: generate a QR image via a public endpoint.
      // This enables “scan to vote” workflows without requiring a custom Slidev component.
      // Note: internet is required for the QR image request.
      String encoded = urlEncode(link);
      String qr = "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + encoded;
      return "<div style=\"display:flex;gap:16px;align-items:center;\">\n"
          + "  <img src=\"" + escapeAttr(qr) + "\" alt=\"QR code\" width=\"200\" height=\"200\" />\n"
          + "  <div>\n"
          + "    <div><strong>Scan to participate</strong></div>\n"
          + "    <div><a href=\"" + escapeAttr(link) + "\" target=\"_blank\" rel=\"noreferrer\">" + escapeMd(link) + "</a></div>\n"
          + "  </div>\n"
          + "</div>";
    }

    private static String googleFormsEmbedUrl(String link) {
      // Google Forms embed works with a docs.google.com URL using `embedded=true`.
      // `forms.gle/...` short links generally cannot be embedded without resolving.
      String l = link.trim();
      if (l.contains("forms.gle/"))
        return null;
      if (!l.contains("docs.google.com/forms"))
        return null;

      if (l.contains("embedded=true"))
        return l;
      if (l.contains("?"))
        return l + "&embedded=true";
      return l + "?embedded=true";
    }

    private static String renderGoogleFormsEmbed(String embedUrl) {
      return "<div>\n"
          + "  <div style=\"font-size:0.9em;opacity:0.8;margin-bottom:8px;\">Fill the form directly (Google Forms)</div>\n"
          + "  <iframe src=\"" + escapeAttr(embedUrl) + "\" width=\"100%\" height=\"520\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\">Loading…</iframe>\n"
          + "</div>";
    }
  }

  private static final class Choice {
    final String text;
    final boolean correct;

    Choice(String text, boolean correct) {
      this.text = text;
      this.correct = correct;
    }
  }

  private static final class LiveCodeBlock implements Block {
    final String lang;
    final boolean editable;
    final String runtime;
    final String endpoint;
    final Integer timeoutMs;
    final Boolean showOutput;
    final String plot;
    final boolean revealLines;
    final String code;

    LiveCodeBlock(String lang, boolean editable, String runtime, String endpoint, Integer timeoutMs, Boolean showOutput, String plot, boolean revealLines, String code) {
      this.lang = lang;
      this.editable = editable;
      this.runtime = runtime;
      this.endpoint = endpoint;
      this.timeoutMs = timeoutMs;
      this.showOutput = showOutput;
      this.plot = plot;
      this.revealLines = revealLines;
      this.code = code;
    }

    @Override
    public String render(Slide slide) {
      // Slidev Monaco Runner: `{monaco-run}` shows a Run button and output.
      // Only JS/TS run out-of-box; other languages require custom code runners.
      String ro = editable ? "false" : "true";
      String normalizedLang = lang == null ? "" : lang.trim().toLowerCase();
      boolean builtInRunnable = normalizedLang.equals("js")
          || normalizedLang.equals("jsx")
          || normalizedLang.equals("javascript")
          || normalizedLang.equals("ts")
          || normalizedLang.equals("tsx")
          || normalizedLang.equals("typescript");

      // Prefer monaco-run when showOutput is requested, even for custom languages,
      // as long as the Slidev project provides a code runner.
      boolean wantOutput = showOutput == null || showOutput.booleanValue();
      String tag = wantOutput ? "{monaco-run}" : "{monaco}";

      // Make more space by default (editor + output). Output will scroll when needed.
      String editorHeight = "420px";
      String outputHeight = "320px";

      StringBuilder options = new StringBuilder();
      options.append("{ ");
      options.append("height: '").append(editorHeight).append("', ");
      options.append("outputHeight: '").append(outputHeight).append("', ");
      options.append("autorun: true, ");
      options.append("editorOptions: { readOnly: ").append(ro).append(", wordWrap: 'on' }");

      // Pass DSL opts through to the runner as runnerOptions.
      boolean hasRunnerOptions = runtime != null || endpoint != null || timeoutMs != null || plot != null;
      if (hasRunnerOptions) {
        options.append(", runnerOptions: {");
        boolean first = true;
        if (runtime != null) {
          options.append(first ? "" : ", ").append("runtime: '").append(escapeJs(runtime)).append("'");
          first = false;
        }
        if (endpoint != null) {
          options.append(first ? "" : ", ").append("endpoint: '").append(escapeJs(endpoint)).append("'");
          first = false;
        }
        if (timeoutMs != null) {
          options.append(first ? "" : ", ").append("timeoutMs: ").append(timeoutMs.intValue());
          first = false;
        }
        if (plot != null) {
          options.append(first ? "" : ", ").append("plot: '").append(escapeJs(plot)).append("'");
          first = false;
        }
        options.append(" }");
      }

      options.append(" }");

      String highlightMeta = revealLines ? (" " + buildRevealLinesMeta(code)) : "";
      String fence = "```" + lang + " " + tag + highlightMeta + " " + options;

      StringBuilder out = new StringBuilder();
      out.append(fence).append("\n");
      out.append(code).append("\n");
      out.append("```\n");
      boolean hasCustomRunnerHint = (endpoint != null && !endpoint.trim().isEmpty())
          || (runtime != null && runtime.trim().equalsIgnoreCase("REMOTE"));
      if (!builtInRunnable && !hasCustomRunnerHint) {
        out.append("\n")
            .append("> Note: live execution for `")
            .append(lang)
            .append("` requires a Slidev code runner (see /custom/config-code-runners).\n");
      }
      return out.toString();
    }
  }

  private static final class RevealCodeBlock implements Block {
    final String lang;
    final boolean revealLines;
    final String code;

    RevealCodeBlock(String lang, boolean revealLines, String code) {
      this.lang = lang;
      this.revealLines = revealLines;
      this.code = code;
    }

    @Override
    public String render(Slide slide) {
      String highlightMeta = revealLines ? (" " + buildRevealLinesMeta(code)) : "";
      return "```" + lang + highlightMeta + "\n" + code + "\n```\n";
    }
  }

  private static final class FenceInfo {
    final String lang;
    final String rest;

    FenceInfo(String lang, String rest) {
      this.lang = lang;
      this.rest = rest;
    }

    static FenceInfo tryParse(String line) {
      if (line == null)
        return null;
      String t = line.trim();
      if (!t.startsWith("```"))
        return null;
      String after = t.substring(3).trim();
      if (after.isEmpty())
        return new FenceInfo("", "");
      int sp = after.indexOf(' ');
      if (sp < 0)
        return new FenceInfo(after, "");
      String lang = after.substring(0, sp).trim();
      String rest = after.substring(sp + 1).trim();
      return new FenceInfo(lang, rest);
    }
  }

  private static String buildRevealLinesMeta(String code) {
    // Slidev code highlight steps: ```lang {1|2|3}
    // We generate 1..N (one step per line).
    String normalized = normalize(code == null ? "" : code).trim();
    if (normalized.isEmpty())
      return "{1}";
    String[] lines = normalized.split("\n", -1);
    int n = Math.max(1, lines.length);
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 1; i <= n; i++) {
      if (i > 1) sb.append("|");
      sb.append(i);
    }
    sb.append("}");
    return sb.toString();
  }

  private static final class VideoBlock implements Block {
    final String src;
    final List<String> flags;

    VideoBlock(String src, List<String> flags) {
      this.src = src;
      this.flags = flags;
    }

    @Override
    public String render(Slide slide) {
      StringBuilder attrs = new StringBuilder();
      for (String f : flags) {
        if ("autoplay".equalsIgnoreCase(f))
          attrs.append(" autoplay");
        else if ("loop".equalsIgnoreCase(f))
          attrs.append(" loop");
        else if ("muted".equalsIgnoreCase(f))
          attrs.append(" muted");
        else if ("controls".equalsIgnoreCase(f))
          attrs.append(" controls");
      }
      return "<video src=\"" + escapeAttr(src) + "\"" + attrs + " style=\"max-width:100%;height:auto;\"></video>\n";
    }
  }

  private static final class ImageBlock implements Block {
    final String alt;
    final String src;
    final String fit; // CONTAIN/COVER/STRETCH

    ImageBlock(String alt, String src, String fit) {
      this.alt = alt;
      this.src = src;
      this.fit = fit;
    }

    @Override
    public String render(Slide slide) {
      String objectFit = "contain";
      if ("COVER".equalsIgnoreCase(fit))
        objectFit = "cover";
      else if ("STRETCH".equalsIgnoreCase(fit))
        objectFit = "fill";
      return "<img src=\"" + escapeAttr(src) + "\" alt=\"" + escapeAttr(alt) + "\" style=\"max-width:100%;height:auto;object-fit:"
          + objectFit + ";\" />\n";
    }
  }

  private static final class CodeBlock implements Block {
    final String fenceHeader; // the whole opening fence line (includes highlight spec)
    final String code;

    CodeBlock(String fenceHeader, String code) {
      this.fenceHeader = fenceHeader;
      this.code = code;
    }

    @Override
    public String render(Slide slide) {
      // Safety: if a raw ` ```code <lang> {...} ` block leaks through as a normal code block,
      // rewrite it to a standard fenced code block so Slidev/Shiki doesn't try to load language "code".
      String trimmed = fenceHeader == null ? "" : fenceHeader.trim();
      FenceInfo fi = FenceInfo.tryParse(trimmed);
      if (fi != null && "code".equalsIgnoreCase(fi.lang)) {
        CodeHeader ch = CodeHeader.parse(trimmed);
        if (ch.lang != null && !ch.lang.trim().isEmpty()) {
          String highlightMeta = ch.revealLines ? (" " + buildRevealLinesMeta(code)) : "";
          return "```" + ch.lang + highlightMeta + "\n" + code + "\n```\n";
        }
      }
      return fenceHeader + "\n" + code + "\n```\n";
    }
  }

  private static final class Parser {
    final List<String> lines;
    int i = 0;

    Parser(List<String> lines) {
      this.lines = lines;
    }

    ParseResult parse() {
      Deck deck = new Deck();

      // optional @deck header at beginning
      // allow leading blank lines
      while (i < lines.size() && lines.get(i).trim().isEmpty())
        i++;

      if (peekIs("@deck") || (i < lines.size() && lines.get(i).trim().startsWith("@deck"))) {
        String first = next().trim();
        if (!"@deck".equals(first)) {
          String rest = first.substring("@deck".length()).trim();
          MetaLine ml = MetaLine.tryParse(rest);
          if (ml != null)
            deck.meta.put(ml.key, ml.value);
        }
        while (i < lines.size() && !peekIs("@enddeck")) {
          String line = next();
          if (line.trim().isEmpty())
            continue;
          MetaLine ml = MetaLine.tryParse(line);
          if (ml != null) deck.meta.put(ml.key, ml.value);
        }
        if (peekIs("@enddeck")) next();
      }

      List<List<String>> slideChunks = splitSlides(lines.subList(i, lines.size()));
      List<Slide> slides = new ArrayList<>();
      for (List<String> chunk : slideChunks) {
        slides.add(parseSlide(chunk));
      }
      return new ParseResult(deck, slides);
    }

    private Slide parseSlide(List<String> chunk) {
      Slide slide = new Slide();
      int idx = 0;

      // Allow optional leading blank lines before @slide header.
      while (idx < chunk.size() && chunk.get(idx).trim().isEmpty())
        idx++;

      if (idx < chunk.size() && chunk.get(idx).trim().startsWith("@slide")) {
        // Support both:
        // - "@slide" on its own line (BNF)
        // - "@slide id: something" (common shorthand people type in practice)
        String first = chunk.get(idx).trim();
        if (!"@slide".equals(first)) {
          String rest = first.substring("@slide".length()).trim();
          if (!rest.isEmpty()) {
            // Parse inline meta if present, e.g. "id: foo"
            MetaLine ml = MetaLine.tryParse(rest);
            if (ml != null)
              slide.meta.put(ml.key, ml.value);
          }
        }
        idx++;
        while (idx < chunk.size() && !"@endslide".equals(chunk.get(idx).trim())) {
          String line = chunk.get(idx++);
          if (line.trim().isEmpty())
            continue;
          MetaLine ml = MetaLine.tryParse(line);
          if (ml != null) slide.meta.put(ml.key, ml.value);
          if (ml != null && "notes".equals(ml.key) && "|".equals(ml.value)) {
            // notes: | then indented lines
            StringBuilder notes = new StringBuilder();
            while (idx < chunk.size() && chunk.get(idx).startsWith("  ")) {
              notes.append(chunk.get(idx).substring(2)).append("\n");
              idx++;
            }
            slide.meta.put("notesBody", notes.toString().trim());
          }
        }
        if (idx < chunk.size() && "@endslide".equals(chunk.get(idx).trim())) idx++;
      }

      List<String> body = chunk.subList(idx, chunk.size());
      int bi = 0;
      while (bi < body.size()) {
        String line = body.get(bi);
        String trimmed = line.trim();

        if (trimmed.isEmpty()) {
          slide.blocks.add(new RawMarkdownBlock(""));
          bi++;
          continue;
        }

        // Step action: @step N: showResults(id)
        StepAction sa = StepAction.tryParse(trimmed);
        if (sa != null) {
          slide.blocks.add(new StepActionBlock(sa.step, sa.interactiveId));
          bi++;
          continue;
        }

        // Slot block
        if (trimmed.startsWith(":::slot ")) {
          String slotId = trimmed.substring(":::slot ".length()).trim();
          bi++;
          List<SlotEntry> entries = new ArrayList<>();
          while (bi < body.size() && !body.get(bi).trim().equals(":::")) {
            String entryLine = body.get(bi).trim();
            RevealPrefix rp = RevealPrefix.tryParse(entryLine);
            if (rp != null) {
              Block b = parseInlineBlock(rp.rest);
              entries.add(new SlotEntry(rp.step, b));
            }
            bi++;
          }
          if (bi < body.size() && body.get(bi).trim().equals(":::")) bi++;
          slide.blocks.add(new SlotBlock(slotId, entries));
          continue;
        }

        // Interactive block
        if (trimmed.startsWith(":::interactive ")) {
          String[] parts = trimmed.split("\\s+");
          String kind = parts.length > 1 ? parts[1] : "POLL";
          String id = parts.length > 2 ? parts[2] : "interactive";
          InteractiveBlock ib = new InteractiveBlock(kind, id);
          slide.interactivesById.put(id, ib);
          bi++;

          // header lines until choices:/short-answer: or :::
          while (bi < body.size()) {
            String l = body.get(bi);
            String t = l.trim();
            if (t.equals(":::") || t.equals("choices:") || t.equals("short-answer:"))
              break;
            MetaLine ml = MetaLine.tryParse(t);
             if (ml != null) {
               if ("question".equals(ml.key)) ib.question = ml.value;
               else if ("results".equals(ml.key)) ib.resultsVisibility = ml.value;
               else if ("view".equals(ml.key)) ib.view = ml.value;
               else if ("allowMultiple".equals(ml.key)) ib.allowMultiple = "true".equalsIgnoreCase(ml.value);
               else if ("url".equals(ml.key)) ib.url = ml.value;
               else if ("googleForm".equals(ml.key) || "googleForms".equals(ml.key)) ib.url = ml.value;
               else if ("resultsEmbed".equals(ml.key)) ib.resultsEmbed = ml.value;
               else if ("resultsRefreshMs".equals(ml.key)) ib.resultsRefreshMs = tryInt(ml.value);
             }
            bi++;
          }

          if (bi < body.size() && body.get(bi).trim().equals("choices:")) {
            bi++;
            while (bi < body.size()) {
              String l = body.get(bi);
              String t = l.trim();
              if (t.equals(":::"))
                break;
              if (t.startsWith("- ")) {
                String text = t.substring(2);
                boolean correct = false;
                if (text.endsWith("[correct]")) {
                  correct = true;
                  text = text.substring(0, text.length() - "[correct]".length()).trim();
                }
                ib.choices.add(new Choice(text, correct));
              }
              bi++;
            }
          } else if (bi < body.size() && body.get(bi).trim().equals("short-answer:")) {
            bi++;
            StringBuilder sb = new StringBuilder();
            while (bi < body.size()) {
              String l = body.get(bi);
              if (l.trim().equals(":::"))
                break;
              sb.append(l).append("\n");
              bi++;
            }
            ib.shortAnswerText = sb.toString();
          }

          if (bi < body.size() && body.get(bi).trim().equals(":::")) bi++;
          slide.blocks.add(ib);
          continue;
        }

        // Reveal code block: ```code lang {opts}
        FenceInfo codeFence = FenceInfo.tryParse(trimmed);
        if (codeFence != null && "code".equalsIgnoreCase(codeFence.lang)) {
          String header = trimmed;
          CodeHeader ch = CodeHeader.parse(header);
          bi++;
          StringBuilder code = new StringBuilder();
          while (bi < body.size() && !body.get(bi).trim().equals("```")) {
            code.append(body.get(bi)).append("\n");
            bi++;
          }
          if (bi < body.size() && body.get(bi).trim().equals("```")) bi++;
          slide.blocks.add(new RevealCodeBlock(ch.lang, ch.revealLines, code.toString().trim()));
          continue;
        }

        // Live code block: ```live lang {opts}
        if (trimmed.toLowerCase().startsWith("```live")) {
          String header = trimmed;
          LiveHeader lh = LiveHeader.parse(header);
          bi++;
          StringBuilder code = new StringBuilder();
          while (bi < body.size() && !body.get(bi).trim().equals("```")) {
            code.append(body.get(bi)).append("\n");
            bi++;
          }
          if (bi < body.size() && body.get(bi).trim().equals("```")) bi++;
          slide.blocks.add(new LiveCodeBlock(
              lh.lang,
              lh.editable,
              lh.runtime,
              lh.endpoint,
              lh.timeoutMs,
              lh.showOutput,
              lh.plot,
              lh.revealLines,
              code.toString().trim()
          ));
          continue;
        }

        // Regular code block: ```lang {1|2-3|4}
        if (trimmed.startsWith("```")) {
          String header = trimmed;
          bi++;
          StringBuilder code = new StringBuilder();
          while (bi < body.size() && !body.get(bi).trim().equals("```")) {
            code.append(body.get(bi)).append("\n");
            bi++;
          }
          if (bi < body.size() && body.get(bi).trim().equals("```")) bi++;
          slide.blocks.add(new CodeBlock(header, code.toString().trim()));
          continue;
        }

        // Video line: !video(path) {flags}
        VideoLine vl = VideoLine.tryParse(trimmed);
        if (vl != null) {
          slide.blocks.add(wrapRevealIfNeeded(line, new VideoBlock(vl.src, vl.flags)));
          bi++;
          continue;
        }

        // Image line: ![alt](path) {fit=...}
        ImageLine il = ImageLine.tryParse(trimmed);
        if (il != null) {
          slide.blocks.add(wrapRevealIfNeeded(line, new ImageBlock(il.alt, il.src, il.fit)));
          bi++;
          continue;
        }

        // Default: keep raw line (may include headings/lists/paragraphs).
        slide.blocks.add(wrapRevealIfNeeded(line, new RawMarkdownBlock(line + "\n")));
        bi++;
      }

      return slide;
    }

    private Block wrapRevealIfNeeded(String originalLine, Block fallback) {
      RevealPrefix rp = RevealPrefix.tryParse(originalLine.trim());
      if (rp == null)
        return fallback;
      Block inner = parseInlineBlock(rp.rest);
      return new RevealedBlock(rp.step, inner);
    }

    private Block parseInlineBlock(String text) {
      String t = text.trim();
      ImageLine il = ImageLine.tryParse(t);
      if (il != null) return new ImageBlock(il.alt, il.src, il.fit);
      VideoLine vl = VideoLine.tryParse(t);
      if (vl != null) return new VideoBlock(vl.src, vl.flags);
      return new RawMarkdownBlock(t + "\n");
    }

    private boolean peekIs(String s) {
      return i < lines.size() && s.equals(lines.get(i).trim());
    }

    private String next() {
      return lines.get(i++);
    }
  }

  private static List<List<String>> splitSlides(List<String> lines) {
    List<List<String>> slides = new ArrayList<>();
    List<String> cur = new ArrayList<>();
    boolean inFence = false;
    for (String l : lines) {
      String t = l.trim();
      if (t.startsWith("```")) {
        // naive but enough for our syntax: toggles on opening and closing fences
        if (inFence && "```".equals(t)) inFence = false;
        else if (!inFence) inFence = true;
      }
      if (!inFence && "---".equals(t)) {
        slides.add(cur);
        cur = new ArrayList<>();
      } else {
        cur.add(l);
      }
    }
    slides.add(cur);
    // drop trailing empty slide chunks
    List<List<String>> filtered = new ArrayList<>();
    for (List<String> s : slides) {
      boolean any = false;
      for (String l : s) {
        if (!l.trim().isEmpty()) {
          any = true;
          break;
        }
      }
      if (any) filtered.add(s);
    }
    return filtered;
  }

  private static final class MetaLine {
    final String key;
    final String value;

    MetaLine(String key, String value) {
      this.key = key;
      this.value = value;
    }

    static MetaLine tryParse(String line) {
      int idx = line.indexOf(':');
      if (idx < 0)
        return null;
      String key = line.substring(0, idx).trim();
      String value = line.substring(idx + 1).trim();
      return new MetaLine(key, stripQuotes(value));
    }
  }

  private static final class RevealPrefix {
    final int step;
    final String rest;

    RevealPrefix(int step, String rest) {
      this.step = step;
      this.rest = rest;
    }

    static RevealPrefix tryParse(String line) {
      if (!line.startsWith("@"))
        return null;
      int sp = line.indexOf(' ');
      if (sp < 0)
        return null;
      String n = line.substring(1, sp).trim();
      try {
        int step = Integer.parseInt(n);
        return new RevealPrefix(step, line.substring(sp + 1));
      } catch (NumberFormatException e) {
        return null;
      }
    }
  }

  private static final class StepAction {
    final int step;
    final String interactiveId;

    StepAction(int step, String interactiveId) {
      this.step = step;
      this.interactiveId = interactiveId;
    }

    static StepAction tryParse(String line) {
      // @step 3: showResults(q1)
      if (!line.startsWith("@step "))
        return null;
      int colon = line.indexOf(':');
      if (colon < 0)
        return null;
      String stepStr = line.substring("@step ".length(), colon).trim();
      int step;
      try {
        step = Integer.parseInt(stepStr);
      } catch (NumberFormatException e) {
        return null;
      }
      String rest = line.substring(colon + 1).trim();
      String prefix = "showResults(";
      if (!rest.startsWith(prefix) || !rest.endsWith(")"))
        return null;
      String id = rest.substring(prefix.length(), rest.length() - 1).trim();
      return new StepAction(step, id);
    }
  }

  private static final class LiveHeader {
    final String lang;
    final boolean editable;
    final String runtime;
    final String endpoint;
    final Integer timeoutMs;
    final Boolean showOutput;
    final String plot;
    final boolean revealLines;

    LiveHeader(String lang, boolean editable, String runtime, String endpoint, Integer timeoutMs, Boolean showOutput, String plot, boolean revealLines) {
      this.lang = lang;
      this.editable = editable;
      this.runtime = runtime;
      this.endpoint = endpoint;
      this.timeoutMs = timeoutMs;
      this.showOutput = showOutput;
      this.plot = plot;
      this.revealLines = revealLines;
    }

    static LiveHeader parse(String line) {
      // ```live py { editable=true runtime=LOCAL ... }
      String rest = line.substring("```live".length()).trim();
      String[] parts = rest.split("\\s+");
      String lang = parts.length > 0 ? parts[0] : "";
      Map<String, String> opts = parseBraceOptions(line);

      boolean editable = !"false".equalsIgnoreCase(opts.getOrDefault("editable", "true"));
      String runtime = opts.get("runtime");
      String endpoint = opts.get("endpoint");
      Integer timeoutMs = tryInt(opts.get("timeoutMs"));
      Boolean showOutput = opts.containsKey("showOutput") ? Boolean.valueOf("true".equalsIgnoreCase(opts.get("showOutput"))) : null;
      String plot = opts.get("plot");
      boolean revealLines = "true".equalsIgnoreCase(opts.getOrDefault("revealLines", "false"))
          || "true".equalsIgnoreCase(opts.getOrDefault("lineByLine", "false"));

      return new LiveHeader(lang, editable, runtime, endpoint, timeoutMs, showOutput, plot, revealLines);
    }
  }

  private static final class CodeHeader {
    final String lang;
    final boolean revealLines;

    CodeHeader(String lang, boolean revealLines) {
      this.lang = lang;
      this.revealLines = revealLines;
    }

    static CodeHeader parse(String line) {
      // Examples:
      // - ```code py {revealLines=true}
      // - ``` code py {revealLines=true}
      FenceInfo fi = FenceInfo.tryParse(line);
      String lang = "";
      if (fi != null && "code".equalsIgnoreCase(fi.lang)) {
        String rest = fi.rest == null ? "" : fi.rest.trim();
        if (!rest.isEmpty())
          lang = rest.split("\\s+")[0].trim();
      }

      Map<String, String> opts = parseBraceOptions(line);
      boolean revealLines = !"false".equalsIgnoreCase(opts.getOrDefault("revealLines", "true"))
          || "true".equalsIgnoreCase(opts.getOrDefault("lineByLine", "false"));
      return new CodeHeader(lang, revealLines);
    }
  }

  private static final class VideoLine {
    final String src;
    final List<String> flags;

    VideoLine(String src, List<String> flags) {
      this.src = src;
      this.flags = flags;
    }

    static VideoLine tryParse(String line) {
      if (!line.startsWith("!video("))
        return null;
      int end = line.indexOf(')', "!video(".length());
      if (end < 0)
        return null;
      String src = line.substring("!video(".length(), end).trim();
      List<String> flags = new ArrayList<>();
      int brace = line.indexOf('{', end);
      if (brace >= 0 && line.endsWith("}")) {
        String inside = line.substring(brace + 1, line.length() - 1).trim();
        for (String p : inside.split("\\s+")) {
          if (!p.isEmpty()) flags.add(p.trim());
        }
      }
      return new VideoLine(stripQuotes(src), flags);
    }
  }

  private static final class ImageLine {
    final String alt;
    final String src;
    final String fit;

    ImageLine(String alt, String src, String fit) {
      this.alt = alt;
      this.src = src;
      this.fit = fit;
    }

    static ImageLine tryParse(String line) {
      if (!line.startsWith("!["))
        return null;
      int altEnd = line.indexOf(']');
      if (altEnd < 0)
        return null;
      if (altEnd + 1 >= line.length() || line.charAt(altEnd + 1) != '(')
        return null;
      int srcEnd = line.indexOf(')', altEnd + 2);
      if (srcEnd < 0)
        return null;
      String alt = line.substring(2, altEnd);
      String src = line.substring(altEnd + 2, srcEnd);
      String fit = "CONTAIN";
      int brace = line.indexOf('{', srcEnd);
      if (brace >= 0 && line.endsWith("}")) {
        String inside = line.substring(brace + 1, line.length() - 1).trim();
        // fit=CONTAIN
        int eq = inside.indexOf('=');
        if (eq >= 0) {
          String k = inside.substring(0, eq).trim();
          String v = inside.substring(eq + 1).trim();
          if ("fit".equalsIgnoreCase(k)) fit = v;
        }
      }
      return new ImageLine(alt, stripQuotes(src.trim()), fit);
    }
  }

  private static final class Emitter {
    String emit(Deck deck, List<Slide> slides) {
      StringBuilder out = new StringBuilder();

      boolean hasHead = !deck.meta.isEmpty();
      if (hasHead) {
        out.append("---\n");
        for (Map.Entry<String, String> e : deck.meta.entrySet()) {
          out.append(e.getKey()).append(": ").append(yamlString(e.getValue())).append("\n");
        }
        out.append("---\n\n");
      }

      for (int si = 0; si < slides.size(); si++) {
        Slide slide = slides.get(si);

        boolean isFirst = si == 0;
        LinkedHashMap<String, String> fm = slideFrontmatter(slide);

        // Avoid an empty first slide: don't emit slide frontmatter right after headmatter.
        boolean emitFm = !fm.isEmpty() && !(hasHead && isFirst);

        if (si > 0) {
          if (emitFm) out.append("\n");
          else out.append("\n---\n\n");
        }

        if (emitFm) {
          out.append("---\n");
          for (Map.Entry<String, String> e : fm.entrySet()) {
            out.append(e.getKey()).append(": ").append(yamlString(e.getValue())).append("\n");
          }
          out.append("---\n\n");
        }

        for (Block b : slide.blocks) {
          String r = b.render(slide);
          if (r == null || r.isEmpty())
            continue;
          out.append(r);
          if (!r.endsWith("\n")) out.append("\n");
        }

        String notes = slide.meta.get("notesBody");
        if (notes != null && !notes.trim().isEmpty()) {
          out.append("\n<!-- ").append(notes.replace("\n", " ")).append(" -->\n");
        }
      }

      return out.toString();
    }

    private static LinkedHashMap<String, String> slideFrontmatter(Slide slide) {
      LinkedHashMap<String, String> fm = new LinkedHashMap<>();
      String layout = slide.meta.get("layout");
      if (layout != null && !layout.isEmpty()) fm.put("layout", layout);
      String transition = slide.meta.get("transition");
      if (transition != null && !transition.isEmpty()) fm.put("transition", mapTransition(transition));
      return fm;
    }

    private static String mapTransition(String transition) {
      // BNF: NONE | FADE | SLIDE | ZOOM
      String t = transition.trim().toUpperCase();
      if (t.startsWith("FADE")) return "fade";
      if (t.startsWith("SLIDE")) return "slide-left";
      if (t.startsWith("ZOOM")) return "view-transition";
      return "none";
    }
  }

  private static String intervalClickBlock(int startAt, Integer hideAt, String inner) {
    if (inner == null || inner.trim().isEmpty())
      return "";
    if (hideAt == null) {
      return "<v-click at=\"" + startAt + "\">\n" + inner + "\n</v-click>";
    }
    return "<v-click at=\"" + startAt + "\">\n"
        + "<v-click at=\"" + hideAt + "\" hide>\n"
        + inner + "\n"
        + "</v-click>\n"
        + "</v-click>";
  }

  private static String yamlString(String s) {
    if (s == null) return "\"\"";
    return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
  }

  private static String stripQuotes(String s) {
    if (s == null) return null;
    String t = s.trim();
    if (t.length() >= 2 && t.startsWith("\"") && t.endsWith("\"")) {
      return t.substring(1, t.length() - 1);
    }
    return t;
  }

  private static String escapeAttr(String s) {
    return s.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;");
  }

  private static String escapeMd(String s) {
    return s.replace("*", "\\*").replace("_", "\\_");
  }

  private static String urlEncode(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

  private static String escapeJs(String s) {
    return s.replace("\\", "\\\\").replace("'", "\\'");
  }

  private static Integer tryInt(String s) {
    if (s == null) return null;
    try {
      return Integer.valueOf(Integer.parseInt(s.trim()));
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private static Map<String, String> parseBraceOptions(String line) {
    // Parses the first {...} block on the header line into key=value pairs separated by spaces.
    // Example: { editable=true runtime=REMOTE endpoint="http://..." timeoutMs=5000 }
    int start = line.indexOf('{');
    int end = line.lastIndexOf('}');
    Map<String, String> out = new HashMap<>();
    if (start < 0 || end < 0 || end <= start) return out;
    String inside = line.substring(start + 1, end).trim();
    if (inside.isEmpty()) return out;

    // Tokenize respecting quotes.
    List<String> tokens = new ArrayList<>();
    StringBuilder cur = new StringBuilder();
    boolean inQuotes = false;
    for (int i = 0; i < inside.length(); i++) {
      char ch = inside.charAt(i);
      if (ch == '"') {
        inQuotes = !inQuotes;
        cur.append(ch);
        continue;
      }
      if (!inQuotes && Character.isWhitespace(ch)) {
        if (cur.length() > 0) {
          tokens.add(cur.toString());
          cur.setLength(0);
        }
        continue;
      }
      cur.append(ch);
    }
    if (cur.length() > 0) tokens.add(cur.toString());

    for (String t : tokens) {
      int eq = t.indexOf('=');
      if (eq < 0) continue;
      String k = t.substring(0, eq).trim();
      String v = t.substring(eq + 1).trim();
      out.put(k, stripQuotes(v));
    }

    return out;
  }

  private static String normalize(String s) {
    if (s == null) return "";
    if (!s.isEmpty() && s.charAt(0) == '\uFEFF') s = s.substring(1);
    return s.replace("\r\n", "\n").replace("\r", "\n");
  }

  private static List<String> splitLines(String s) {
    List<String> out = new ArrayList<>();
    String[] ls = s.split("\n", -1);
    for (String l : ls) out.add(l);
    return out;
  }
}
