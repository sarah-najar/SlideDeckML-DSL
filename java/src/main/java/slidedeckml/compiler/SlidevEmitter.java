package slidedeckml.compiler;

import java.util.LinkedHashMap;
import java.util.Map;

final class SlidevEmitter {
  String emit(Models.Deck deck) {
    StringBuilder out = new StringBuilder();

    boolean hasHeadmatter = !deck.head.isEmpty();
    if (hasHeadmatter) {
      out.append("---\n");
      emitYamlMap(out, deck.head.values, 0);
      out.append("---\n\n");
    }

    for (int i = 0; i < deck.slides.size(); i++) {
      Models.Slide slide = deck.slides.get(i);

      LinkedHashMap<String, Object> fm = new LinkedHashMap<>();
      if (slide.layout != null) fm.put("layout", slide.layout);
      if (slide.cssClass != null) fm.put("class", slide.cssClass);
      if (slide.transition != null) {
        if (slide.transition.isObject()) {
          // emit as nested YAML object
          fm.put("transition", slide.transition.objectProps);
        } else {
          String t = slide.transition.name;
          if (slide.transition.backwardName != null) t = t + " | " + slide.transition.backwardName;
          fm.put("transition", t);
        }
      }

      // Slidev can show an empty first slide if we place a slide frontmatter block
      // immediately after the global headmatter. Keep slide 1 as pure content and
      // rely on Slidev defaults (first slide layout is `cover`).
      boolean hasFrontmatter = !fm.isEmpty() && !(hasHeadmatter && i == 0);

      // Slidev uses a single line `---` both as slide separator and (optionally) as
      // the start of a slide frontmatter block. Avoid emitting two separators.
      if (i > 0) {
        if (hasFrontmatter) out.append("\n");
        else out.append("\n---\n\n");
      }

      if (hasFrontmatter) {
        out.append("---\n");
        emitYamlMap(out, fm, 0);
        out.append("---\n\n");
      }

      boolean hasAnyContent = false;

      Models.SlideRenderContext ctx = new Models.SlideRenderContext(slide.revealAtByElementId);
      for (Models.Item item : slide.items) {
        String r = item.render(ctx);
        if (r != null && !r.trim().isEmpty()) {
          out.append(r);
          if (!r.endsWith("\n")) out.append("\n");
          hasAnyContent = true;
        }
      }

      if (!hasAnyContent && slide.title != null) {
        out.append("# ").append(slide.title).append("\n");
      } else if (slide.title != null && !startsWithHeading(out)) {
        // non-invasive: prepend title only if slide doesn't begin with a heading
        // (best-effort; compiler keeps markdown literal in most cases)
      }

      if (slide.notes != null && !slide.notes.trim().isEmpty()) {
        if (!out.toString().endsWith("\n")) out.append("\n");
        out.append(slide.notes.trim()).append("\n");
      }
    }

    return out.toString();
  }

  private static boolean startsWithHeading(StringBuilder out) {
    // heuristic only; we avoid complex rewriting.
    return out.indexOf("#") == 0;
  }

  private static void emitYamlMap(StringBuilder out, Map<String, ?> map, int indent) {
    String pad = repeat("  ", indent);
    for (Map.Entry<String, ?> e : map.entrySet()) {
      out.append(pad).append(e.getKey()).append(":");
      Object v = e.getValue();
      if (v instanceof Map) {
        out.append("\n");
        @SuppressWarnings("unchecked")
        Map<String, ?> nested = (Map<String, ?>) v;
        emitYamlMap(out, nested, indent + 1);
      } else {
        out.append(" ").append(yamlScalar(v)).append("\n");
      }
    }
  }

  private static String yamlScalar(Object v) {
    if (v == null) return "\"\"";
    if (v instanceof Boolean) return ((Boolean) v) ? "true" : "false";
    if (v instanceof Number) return v.toString();
    String s = String.valueOf(v);
    // Always quote strings for safety.
    return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
  }

  private static String repeat(String s, int n) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < n; i++) b.append(s);
    return b.toString();
  }
}
