package slidedeckml.compiler;

import java.util.Map;

final class Models {
  static final class Deck {
    String name;
    final YamlMap head = new YamlMap();
    final java.util.List<Slide> slides = new java.util.ArrayList<>();
  }

  static final class Slide {
    String id;
    String title;
    String layout;
    String cssClass;
    Transition transition;
    String notes;
    final java.util.List<Item> items = new java.util.ArrayList<>();
    final Map<String, Integer> revealAtByElementId = new java.util.HashMap<>();

    final Map<String, Element> elementsById = new java.util.HashMap<>();
    final Map<String, java.util.List<CodeRevealStep>> codeRevealsByCodeId = new java.util.HashMap<>();
    final Map<String, Map<Integer, String>> slotSwitchesBySlot = new java.util.HashMap<>();
  }

  interface Item {
    String render(SlideRenderContext ctx);
  }

  static final class SlotItem implements Item {
    final String slotName;

    SlotItem(String slotName) {
      this.slotName = slotName;
    }

    @Override
    public String render(SlideRenderContext ctx) {
      Map<Integer, String> schedule = ctx.slide.slotSwitchesBySlot.get(slotName);
      if (schedule == null || schedule.isEmpty()) return "";

      java.util.List<Integer> steps = new java.util.ArrayList<>(schedule.keySet());
      java.util.Collections.sort(steps);

      StringBuilder out = new StringBuilder();
      for (int i = 0; i < steps.size(); i++) {
        int step = steps.get(i);
        Integer next = (i + 1 < steps.size()) ? steps.get(i + 1) : null;
        String elementId = schedule.get(step);
        Element element = ctx.slide.elementsById.get(elementId);
        if (element == null) continue;

        String inner = element.wrapPositionIfNeeded(element.renderMarkdown());
        out.append(intervalClickBlock(step, next, inner)).append("\n");
      }

      return out.toString();
    }
  }

  static final class MarkdownItem implements Item {
    final String markdown;

    MarkdownItem(String markdown) {
      this.markdown = markdown;
    }

    @Override
    public String render(SlideRenderContext ctx) {
      return markdown;
    }
  }

  static final class ElementItem implements Item {
    final Element element;

    ElementItem(Element element) {
      this.element = element;
    }

    @Override
    public String render(SlideRenderContext ctx) {
      if (element.slotOf != null) {
        // Variants are rendered via SlotItem placement (`slot name;`) so they don't duplicate.
        return "";
      }

      // Code reveal: render progressive states rather than a single block.
      java.util.List<CodeRevealStep> reveals = ctx.slide.codeRevealsByCodeId.get(element.id);
      if ("CodeBlock".equals(element.type) && reveals != null && !reveals.isEmpty()) {
        java.util.List<CodeRevealStep> sorted = new java.util.ArrayList<>(reveals);
        java.util.Collections.sort(sorted, (a, b) -> Integer.compare(a.stepIndex, b.stepIndex));

        StringBuilder out = new StringBuilder();
        // Use Slidev's built-in "highlight steps" syntax: ```lang {1|2-3|4}```
        // This gives the intended dim/hover-like effect while keeping a single
        // code block rendered. Steps are driven by clicks automatically.
        String lang = element.language == null ? "" : element.language;
        String fullCode = element.content == null ? "" : element.content;

        java.util.List<String> ranges = new java.util.ArrayList<>();
        for (CodeRevealStep r : sorted) {
          ranges.add(highlightRangeText(r.fromLine, r.toLine));
        }
        String meta = "{" + String.join("|", ranges) + "}";

        String block = "```" + lang + " " + meta + "\n" + fullCode + "\n```";
        String body = element.wrapPositionIfNeeded(block);

        Integer at = ctx.revealAtByElementId.get(element.id);
        if (at != null) {
          return "<v-click at=\"" + at + "\">\n" + body + "\n</v-click>\n";
        }
        return body + "\n";
      }

      String body = element.wrapPositionIfNeeded(element.renderMarkdown());
      Integer at = ctx.revealAtByElementId.get(element.id);
      if (at != null) {
        return "<v-click at=\"" + at + "\">\n" + body + "\n</v-click>\n";
      }
      return body + "\n";
    }
  }

  static final class SlideRenderContext {
    final Map<String, Integer> revealAtByElementId;
    final Slide slide;

    SlideRenderContext(Slide slide, Map<String, Integer> revealAtByElementId) {
      this.slide = slide;
      this.revealAtByElementId = revealAtByElementId;
    }
  }

  static final class CodeRevealStep {
    final int stepIndex;
    final int fromLine;
    final int toLine;

    CodeRevealStep(int stepIndex, int fromLine, int toLine) {
      this.stepIndex = stepIndex;
      this.fromLine = fromLine;
      this.toLine = toLine;
    }
  }

  static final class Transition {
    String name;
    String backwardName;
    final java.util.LinkedHashMap<String, String> objectProps = new java.util.LinkedHashMap<>();

    boolean isObject() {
      return !objectProps.isEmpty();
    }
  }

  static final class Element {
    String id;
    String type = "TextBlock";

    String content;

    Boolean ordered;

    String src;
    String altText;

    String language;

    String latexSource;
    String displayMode; // INLINE or BLOCK

    Boolean autoplay;
    Boolean loop;
    Boolean muted;
    Boolean controls;

    Integer zIndex;
    AbsolutePosition absolutePosition;
    String slotOf;

    static final class AbsolutePosition {
      double x;
      double y;
      Double width;
      Double height;
      String unit;   // PX or PERCENT
      String anchor; // TOP_LEFT, TOP_CENTER, CENTER, BOTTOM_CENTER, BOTTOM_RIGHT
    }

    String renderMarkdown() {
      if ("TextBlock".equals(type)) {
        if (absolutePosition != null) {
          return "<div>" + htmlEscapeWithBreaks(safe(content)) + "</div>";
        }
        return safe(content);
      }

      if ("ListBlock".equals(type)) {
        String c = safe(content);
        if (c.trim().isEmpty()) return "";
        String[] lines = c.split("\\r?\\n");
        StringBuilder b = new StringBuilder();
        boolean isOrdered = ordered != null && ordered.booleanValue();
        b.append(isOrdered ? "<ol>\n" : "<ul>\n");
        for (String line : lines) {
          String t = line.trim();
          if (t.isEmpty()) continue;
          b.append("<li>").append(htmlEscape(t)).append("</li>\n");
        }
        b.append(isOrdered ? "</ol>" : "</ul>");
        return b.toString();
      }

      if ("ImageElement".equals(type)) {
        String a = altText == null ? "" : altText;
        String s = safe(src).trim();
        // Always emit HTML to ensure the image renders correctly inside wrappers
        // like <v-click> (Markdown inside HTML is not re-parsed).
        String style = absolutePosition != null
            ? "width:100%;height:100%;object-fit:contain;"
            : "max-width:100%;height:auto;";
        return "<img src=\"" + htmlEscapeAttr(s) + "\" alt=\"" + htmlEscapeAttr(a) + "\" style=\"" + style + "\" />";
      }

      if ("VideoElement".equals(type)) {
        StringBuilder attrs = new StringBuilder();
        if (truthy(autoplay)) attrs.append(" autoplay");
        if (truthy(loop)) attrs.append(" loop");
        if (truthy(muted)) attrs.append(" muted");
        if (truthy(controls)) attrs.append(" controls");
        String s = safe(src).trim();
        String style = absolutePosition != null ? " style=\"width:100%;height:100%;object-fit:contain;\"" : "";
        return "<video src=\"" + htmlEscapeAttr(s) + "\"" + attrs + style + "></video>";
      }

      if ("CodeBlock".equals(type)) {
        String lang = language == null ? "" : language;
        return "```" + lang + "\n" + safe(content) + "\n```";
      }

      if ("EquationBlock".equals(type)) {
        String latex = safe(latexSource).trim();
        if ("INLINE".equals(displayMode)) {
          return "$" + latex + "$";
        }
        return "$$\n" + latex + "\n$$";
      }

      return safe(content);
    }

    String wrapPositionIfNeeded(String inner) {
      if (absolutePosition == null) return inner;
      StringBuilder style = new StringBuilder();
      style.append("position:absolute;");
      style.append("left:").append(fmt(absPos().x, absPos().unit)).append(";");
      style.append("top:").append(fmt(absPos().y, absPos().unit)).append(";");
      if (absPos().width != null) style.append("width:").append(fmt(absPos().width, absPos().unit)).append(";");
      if (absPos().height != null) style.append("height:").append(fmt(absPos().height, absPos().unit)).append(";");
      if (zIndex != null) style.append("z-index:").append(zIndex.intValue()).append(";");
      String transform = anchorTransform(absPos().anchor);
      if (!transform.isEmpty()) style.append("transform:").append(transform).append(";");
      return "<div style=\"" + style + "\">\n" + inner + "\n</div>";
    }

    private AbsolutePosition absPos() {
      return absolutePosition;
    }

    private static String fmt(double v, String unit) {
      String suffix = "PX".equals(unit) ? "px" : "%";
      if (Math.rint(v) == v) return ((long) v) + suffix;
      return v + suffix;
    }

    private static String anchorTransform(String anchor) {
      if (anchor == null) return "";
      switch (anchor) {
        case "TOP_CENTER":
          return "translate(-50%, 0)";
        case "CENTER":
          return "translate(-50%, -50%)";
        case "BOTTOM_CENTER":
          return "translate(-50%, -100%)";
        case "BOTTOM_RIGHT":
          return "translate(-100%, -100%)";
        case "TOP_LEFT":
        default:
          return "";
      }
    }

    private static boolean truthy(Boolean b) {
      return b != null && b.booleanValue();
    }

    private static String safe(String s) {
      return s == null ? "" : s;
    }

    private static String htmlEscape(String s) {
      return s
          .replace("&", "&amp;")
          .replace("<", "&lt;")
          .replace(">", "&gt;");
    }

    private static String htmlEscapeAttr(String s) {
      return htmlEscape(s).replace("\"", "&quot;");
    }

    private static String htmlEscapeWithBreaks(String s) {
      return htmlEscape(s).replace("\r\n", "\n").replace("\n", "<br/>");
    }
  }

  static final class YamlMap {
    final java.util.LinkedHashMap<String, Object> values = new java.util.LinkedHashMap<>();

    void put(String key, Object value) {
      if (value == null) return;
      values.put(key, value);
    }

    boolean isEmpty() {
      return values.isEmpty();
    }
  }

  static String intervalClickBlock(int startAt, Integer hideAt, String inner) {
    if (hideAt == null) {
      return "<v-click at=\"" + startAt + "\">\n" + inner + "\n</v-click>";
    }
    // Show at startAt, then hide exactly when the next state activates.
    return "<v-click at=\"" + startAt + "\">\n"
        + "<v-click at=\"" + hideAt + "\" hide>\n"
        + inner + "\n"
        + "</v-click>\n"
        + "</v-click>";
  }

  static String highlightRange(int fromLine, int toLine) {
    int f = Math.max(1, fromLine);
    int t = Math.max(f, toLine);
    if (f == t) return "{" + f + "}";
    return "{" + f + "-" + t + "}";
  }

  static String highlightRangeText(int fromLine, int toLine) {
    int f = Math.max(1, fromLine);
    int t = Math.max(f, toLine);
    if (f == t) return String.valueOf(f);
    return f + "-" + t;
  }
}
