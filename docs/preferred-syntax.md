# Preferred (friendlier) syntax — proposal

This is a **proposed** surface syntax intended for non-technical users. It is **not implemented in the compiler yet**.

## Goals
- Minimal punctuation (no `{}`, no `;`)
- Slide authoring feels like writing a document
- Advanced features remain optional

## Example

```text
deck "Professor Demo"
theme "default"
title "SlideDeckML -> Slidev (Demo)"
author "Student"
transition "slide-left"

slide "Cover" layout "cover" class "text-center"
  # SlideDeckML DSL
  External DSL (ANTLR) compiled into Slidev `slides.md`.

slide "Agenda"
  # Agenda
  - What we generate (Slidev Markdown)
  - DSL elements (text/list/code/math)
  - Click steps (step -> v-click)

slide "Steps" transition "slide-up"
  # Steps / clicks
  step 1 reveal "snippet"
  step 2 reveal "explain"

  code "java" id "snippet":
    public static void main(String[] args) {
      System.out.println("Hello Slidev");
    }

  text id "explain": This line appears on click 2.
```

## Mapping (same domain model)
- `deck/theme/title/author/transition` -> Slidev headmatter YAML
- `slide ...` -> Slidev slide blocks separated by `---`
- `layout/class/transition` on a slide -> Slidev slide frontmatter YAML
- `step N reveal X` -> `<v-click at="N"> ... </v-click>`
- `text/list/code/math/image/video` -> the existing `SlideElement` model

