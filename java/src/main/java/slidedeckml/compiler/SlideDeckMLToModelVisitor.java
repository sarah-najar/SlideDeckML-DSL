package slidedeckml.compiler;

import java.util.Map;

import slidedeckml.grammar.SlideDeckMLBaseVisitor;
import slidedeckml.grammar.SlideDeckMLParser;

final class SlideDeckMLToModelVisitor extends SlideDeckMLBaseVisitor<Object> {

  @Override
  public Object visitFile(SlideDeckMLParser.FileContext ctx) {
    return visit(ctx.deckDecl());
  }

  @Override
  public Object visitDeckDecl(SlideDeckMLParser.DeckDeclContext ctx) {
    Models.Deck deck = new Models.Deck();
    deck.name = unquote(ctx.deckName.getText());

    for (SlideDeckMLParser.DeckItemContext item : ctx.deckItem()) {
      Object o = visit(item);
      if (o instanceof Models.Slide) {
        deck.slides.add((Models.Slide) o);
      } else if (o instanceof Models.YamlMap) {
        // head
        Models.YamlMap head = (Models.YamlMap) o;
        deck.head.values.putAll(head.values);
      }
    }

    return deck;
  }

  @Override
  public Object visitHeadDecl(SlideDeckMLParser.HeadDeclContext ctx) {
    Models.YamlMap head = new Models.YamlMap();
    for (SlideDeckMLParser.HeadPropContext p : ctx.headProp()) {
      applyHeadProp(head, p);
    }
    return head;
  }

  private void applyHeadProp(Models.YamlMap head, SlideDeckMLParser.HeadPropContext p) {
    if (p.getChildCount() == 0) return;

    String key = p.getChild(0).getText();

    if ("defaults".equals(key)) {
      // Only compile a few slide defaults into headmatter for now.
      for (SlideDeckMLParser.SlideFrontPropContext sp : p.slideFrontProp()) {
        applySlideFrontPropToHeadDefaults(head, sp);
      }
      return;
    }

    SlideDeckMLParser.ValueContext v = p.value();
    if (v == null) return;

    head.put(key, valueToScalar(v));
  }

  private void applySlideFrontPropToHeadDefaults(Models.YamlMap head, SlideDeckMLParser.SlideFrontPropContext sp) {
    String key = sp.getChild(0).getText();
    if ("transition".equals(key)) {
      Models.Transition t = parseTransition(sp.transitionValue());
      if (t != null) {
        if (t.isObject()) head.put("transition", t.objectProps);
        else {
          String s = t.name;
          if (t.backwardName != null) s = s + " | " + t.backwardName;
          head.put("transition", s);
        }
      }
    }
  }

  @Override
  public Object visitSlideDecl(SlideDeckMLParser.SlideDeclContext ctx) {
    Models.Slide slide = new Models.Slide();
    slide.id = ctx.slideId == null ? null : ctx.slideId.getText();

    for (SlideDeckMLParser.SlideFrontPropContext p : ctx.slideFrontProp()) {
      applySlideFrontProp(slide, p);
    }

    for (SlideDeckMLParser.SlideBodyItemContext item : ctx.slideBodyItem()) {
      Object o = visit(item);
      if (o instanceof Models.Item) {
        Models.Item it = (Models.Item) o;
        if (it instanceof Models.ElementItem) {
          Models.Element element = ((Models.ElementItem) it).element;
          slide.elementsById.put(element.id, element);
        }
        slide.items.add(it);
      } else if (o instanceof StepInfo) {
        StepInfo si = (StepInfo) o;
        for (String eid : si.revealIds) {
          slide.revealAtByElementId.put(eid, si.stepIndex);
        }
        for (CodeRevealInfo cr : si.codeReveals) {
          slide.codeRevealsByCodeId
              .computeIfAbsent(cr.targetElementId, k -> new java.util.ArrayList<>())
              .add(new Models.CodeRevealStep(si.stepIndex, cr.fromLine, cr.toLine));
        }
        for (SlotSwitchInfo sw : si.slotSwitches) {
          slide.slotSwitchesBySlot
              .computeIfAbsent(sw.slotName, k -> new java.util.HashMap<>())
              .put(si.stepIndex, sw.variantElementId);
        }
      }
    }

    return slide;
  }

  private void applySlideFrontProp(Models.Slide slide, SlideDeckMLParser.SlideFrontPropContext p) {
    String key = p.getChild(0).getText();

    if ("layout".equals(key)) {
      slide.layout = p.ID().getText();
      return;
    }

    if ("transition".equals(key)) {
      slide.transition = parseTransition(p.transitionValue());
      return;
    }

    SlideDeckMLParser.ValueContext v = p.value();
    if (v == null) return;

    if ("title".equals(key)) slide.title = valueToString(v);
    else if ("class".equals(key)) slide.cssClass = valueToString(v);
    else if ("notes".equals(key)) slide.notes = valueToString(v);
  }

  private Models.Transition parseTransition(SlideDeckMLParser.TransitionValueContext tv) {
    if (tv == null) return null;

    Models.Transition t = new Models.Transition();

    if (tv.ID() != null && !tv.ID().isEmpty()) {
      t.name = tv.ID(0).getText();
      if (tv.ID().size() > 1) t.backwardName = tv.ID(1).getText();
      return t;
    }

    for (SlideDeckMLParser.TransitionObjPropContext p : tv.transitionObjProp()) {
      String k = p.ID().getText();
      String v = valueToString(p.value());
      t.objectProps.put(k, v);
    }

    return t;
  }

  @Override
  public Object visitMarkdownDecl(SlideDeckMLParser.MarkdownDeclContext ctx) {
    return new Models.MarkdownItem(valueToString(ctx.value()));
  }

  @Override
  public Object visitSlotDecl(SlideDeckMLParser.SlotDeclContext ctx) {
    return new Models.SlotItem(ctx.slotName.getText());
  }

  @Override
  public Object visitElementDecl(SlideDeckMLParser.ElementDeclContext ctx) {
    Models.Element e = new Models.Element();
    e.id = ctx.elementId.getText();
    if (ctx.elementType() != null) e.type = ctx.elementType().getText();

    for (SlideDeckMLParser.ElementPropContext p : ctx.elementProp()) {
      applyElementProp(e, p);
    }

    // Register for later lookups (slots, codeReveal, etc.)
    // (Slide is not directly accessible here; we also return an ElementItem, so the caller can add it.)
    return new Models.ElementItem(e);
  }

  private void applyElementProp(Models.Element e, SlideDeckMLParser.ElementPropContext p) {
    String key = p.getChild(0).getText();

    if ("content".equals(key)) e.content = valueToString(p.value());
    else if ("ordered".equals(key)) e.ordered = valueToBoolean(p.value());
    else if ("src".equals(key)) e.src = valueToString(p.value());
    else if ("altText".equals(key)) e.altText = valueToString(p.value());
    else if ("language".equals(key)) e.language = valueToString(p.value());
    else if ("latexSource".equals(key)) e.latexSource = valueToString(p.value());
    else if ("displayMode".equals(key)) e.displayMode = p.equationDisplayMode().getText();
    else if ("autoplay".equals(key)) e.autoplay = valueToBoolean(p.value());
    else if ("loop".equals(key)) e.loop = valueToBoolean(p.value());
    else if ("muted".equals(key)) e.muted = valueToBoolean(p.value());
    else if ("controls".equals(key)) e.controls = valueToBoolean(p.value());
    else if ("zIndex".equals(key)) e.zIndex = Integer.valueOf(Integer.parseInt(p.INT().getText()));
    else if (p.positionDecl() != null) applyPosition(e, p.positionDecl());
    else if ("slotOf".equals(key)) e.slotOf = p.ID().getText();
  }

  private void applyPosition(Models.Element e, SlideDeckMLParser.PositionDeclContext pos) {
    if (pos.absolutePosition() == null) return;

    SlideDeckMLParser.AbsolutePositionContext a = pos.absolutePosition();
    Models.Element.AbsolutePosition ap = new Models.Element.AbsolutePosition();
    ap.x = Double.parseDouble(a.number(0).getText());
    ap.y = Double.parseDouble(a.number(1).getText());

    // width/height are optional and appear after x/y in the grammar
    if (a.number().size() >= 3) ap.width = Double.valueOf(Double.parseDouble(a.number(2).getText()));
    if (a.number().size() >= 4) ap.height = Double.valueOf(Double.parseDouble(a.number(3).getText()));

    ap.unit = a.unit() == null ? "PX" : a.unit().getText();
    ap.anchor = a.anchorType() == null ? "TOP_LEFT" : a.anchorType().getText();
    e.absolutePosition = ap;
  }

  @Override
  public Object visitStepDecl(SlideDeckMLParser.StepDeclContext ctx) {
    StepInfo si = new StepInfo();
    si.stepIndex = Integer.parseInt(ctx.stepIndex.getText());

    for (SlideDeckMLParser.StepItemContext item : ctx.stepItem()) {
      if (item.getChildCount() >= 2 && "reveal".equals(item.getChild(0).getText()) && item.ID() != null && !item.ID().isEmpty()) {
        si.revealIds.add(item.ID(0).getText());
      }
      if (item.getChildCount() >= 3 && "codeReveal".equals(item.getChild(0).getText()) && item.ID() != null && !item.ID().isEmpty() && item.range() != null) {
        int from = Integer.parseInt(item.range().INT(0).getText());
        int to = Integer.parseInt(item.range().INT(1).getText());
        si.codeReveals.add(new CodeRevealInfo(item.ID(0).getText(), from, to));
      }
      if (item.getChildCount() >= 4 && "switch".equals(item.getChild(0).getText()) && item.ID() != null && item.ID().size() >= 2) {
        si.slotSwitches.add(new SlotSwitchInfo(item.ID(0).getText(), item.ID(1).getText()));
      }
    }

    return si;
  }

  private static final class StepInfo {
    int stepIndex;
    final java.util.List<String> revealIds = new java.util.ArrayList<>();
    final java.util.List<CodeRevealInfo> codeReveals = new java.util.ArrayList<>();
    final java.util.List<SlotSwitchInfo> slotSwitches = new java.util.ArrayList<>();
  }

  private static final class CodeRevealInfo {
    final String targetElementId;
    final int fromLine;
    final int toLine;

    CodeRevealInfo(String targetElementId, int fromLine, int toLine) {
      this.targetElementId = targetElementId;
      this.fromLine = fromLine;
      this.toLine = toLine;
    }
  }

  private static final class SlotSwitchInfo {
    final String slotName;
    final String variantElementId;

    SlotSwitchInfo(String slotName, String variantElementId) {
      this.slotName = slotName;
      this.variantElementId = variantElementId;
    }
  }

  private static Object valueToScalar(SlideDeckMLParser.ValueContext v) {
    if (v == null) return null;

    if (v.bool() != null) {
      return "true".equals(v.bool().getText());
    }

    if (v.number() != null) {
      String t = v.number().getText();
      if (t.contains(".")) return Double.parseDouble(t);
      return Long.parseLong(t);
    }

    if (v.STR() != null) return unquote(v.STR().getText());
    if (v.MLSTR() != null) return untriple(v.MLSTR().getText());

    if (v.ID() != null) return v.ID().getText();

    // For now, keep arrays/objects as their raw text.
    return v.getText();
  }

  private static String valueToString(SlideDeckMLParser.ValueContext v) {
    Object o = valueToScalar(v);
    return o == null ? null : String.valueOf(o);
  }

  private static Boolean valueToBoolean(SlideDeckMLParser.ValueContext v) {
    Object o = valueToScalar(v);
    if (o instanceof Boolean) return (Boolean) o;
    if (o instanceof String) {
      String s = ((String) o).trim();
      if ("true".equalsIgnoreCase(s)) return Boolean.TRUE;
      if ("false".equalsIgnoreCase(s)) return Boolean.FALSE;
    }
    return null;
  }

  private static String unquote(String s) {
    if (s == null) return null;
    if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
      s = s.substring(1, s.length() - 1);
    }
    return s.replace("\\\"", "\"").replace("\\\\", "\\");
  }

  private static String untriple(String s) {
    if (s == null) return null;
    if (s.startsWith("\"\"\"") && s.endsWith("\"\"\"")) {
      return s.substring(3, s.length() - 3);
    }
    return s;
  }
}
