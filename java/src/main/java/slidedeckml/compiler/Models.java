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
  }

  interface Item {
    String render(SlideRenderContext ctx);
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
      String body = element.renderMarkdown();
      Integer at = ctx.revealAtByElementId.get(element.id);
      if (at != null) {
        return "<v-click at=\"" + at + "\">\n" + body + "\n</v-click>\n";
      }
      return body + "\n";
    }
  }

  static final class SlideRenderContext {
    final Map<String, Integer> revealAtByElementId;

    SlideRenderContext(Map<String, Integer> revealAtByElementId) {
      this.revealAtByElementId = revealAtByElementId;
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

    String renderMarkdown() {
      if ("TextBlock".equals(type)) {
        return safe(content);
      }

      if ("ListBlock".equals(type)) {
        String c = safe(content);
        if (c.trim().isEmpty()) return "";
        String[] lines = c.split("\\r?\\n");
        StringBuilder b = new StringBuilder();
        boolean isOrdered = ordered != null && ordered.booleanValue();
        int i = 1;
        for (String line : lines) {
          String t = line.trim();
          if (t.isEmpty()) continue;
          if (isOrdered) b.append(i++).append(". ");
          else b.append("- ");
          b.append(t).append("\n");
        }
        return b.toString();
      }

      if ("ImageElement".equals(type)) {
        String a = altText == null ? "" : altText;
        return "![" + a + "](" + safe(src).trim() + ")";
      }

      if ("VideoElement".equals(type)) {
        StringBuilder attrs = new StringBuilder();
        if (truthy(autoplay)) attrs.append(" autoplay");
        if (truthy(loop)) attrs.append(" loop");
        if (truthy(muted)) attrs.append(" muted");
        if (truthy(controls)) attrs.append(" controls");
        return "<video src=\"" + safe(src).trim() + "\"" + attrs + "></video>";
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

    private static boolean truthy(Boolean b) {
      return b != null && b.booleanValue();
    }

    private static String safe(String s) {
      return s == null ? "" : s;
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
}
