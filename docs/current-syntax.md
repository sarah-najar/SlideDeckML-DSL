# SlideDeckML — current syntax (implemented)

This document explains the **current, working syntax** of SlideDeckML as implemented by:
- the grammar: `grammar/SlideDeckML.g4` (and `java/src/main/antlr4/SlideDeckML.g4`)
- the Java compiler: `java/src/main/java/slidedeckml/compiler/*`

It is intentionally strict and explicit. A more “friendly” syntax can be added later (see `docs/preferred-syntax.md`).

## Big picture
- One input file (`.sdeck`) describes a **deck** and multiple **slides**.
- The compiler generates a Slidev `slides.md`:
  - Deck metadata becomes YAML **headmatter** (top of file).
  - Slides become Markdown sections separated by `---`.
  - Per-slide options become YAML **frontmatter**.
  - `step N { reveal X; }` becomes Slidev `<v-click at="N"> ... </v-click>`.

## Minimal example

```sdeck
deck "My Demo" {
  head {
    theme "default";
    title "Demo";
    author "Me";
    defaults { transition slide-left; }
  }

  slide intro {
    markdown """# Hello

This slide comes from the DSL.
""";
  }
}
```

## Grammar overview

### 1) Deck
```
deck "Deck Name" { ... }
```

Inside a deck, you can define:
- `head { ... }` (global options)
- `slide ... { ... }` (slides)
- `template ... { ... }` and `layout ... { ... }` (parsed, not fully compiled yet)

### 2) Head (global options)
```
head {
  theme "seriph";
  title "Welcome";
  author "Alice";
  mdc true;
  exportFilename "my-export";

  defaults { transition slide-left; }
}
```

Notes:
- `theme` is a **Slidev theme** identifier (or a local folder path). The DSL/compiler only forwards it into Slidev headmatter; the theme must exist in the Slidev project where you open the generated `slides.md`.
- Slidev theme docs: `https://sli.dev/guide/theme-addon`
- Values can be strings (`"..."`), booleans (`true/false`), numbers, identifiers, arrays (`[...]`), or objects (`{ ... }`).
- `defaults { ... }` currently supports `transition ...;` in the compiler output.

### 3) Slides
```
slide myId {
  title "Optional";
  layout cover;
  class "text-center";
  transition fade;
  notes """<!-- Presenter note at end of slide -->""";

  markdown """# Markdown block
  ...
  """;

  element thing { ... }
  step 1 { reveal thing; }
}
```

What each property does:
- `title` is metadata (the compiler may use it if the slide has no content).
- `layout`, `class`, `transition` compile to Slidev slide frontmatter **for most slides**.
- `notes` is appended to the end of the slide content (intended for Slidev presenter notes).

Important behavior:
- If the deck has global headmatter, the compiler currently **omits slide frontmatter for the first slide** to avoid Slidev showing an empty first slide.

### 4) Markdown blocks
```
markdown """# Hello

Any Markdown you want.
""";
```

Use `"""` for multi-line text.

### 5) Elements
Elements are “typed” blocks that compile to Markdown or HTML.

```
element myElement {
  type TextBlock;
  content "Hello";
}
```

Supported element types in the current Java compiler:
- `TextBlock` (`content`)
- `ListBlock` (`content` as lines, `ordered true/false`)
- `CodeBlock` (`language`, `content`)
- `EquationBlock` (`latexSource`, `displayMode INLINE|BLOCK`)
- `ImageElement` (`src`, `altText`) *(works best with local/accessible files)*
- `VideoElement` (`src`, optional `controls/muted/autoplay/loop`)

### 6) Steps (click reveals)
```
step 1 { reveal myElement; }
step 2 { reveal anotherElement; }
```

This wraps the element output in:
```
<v-click at="1"> ... </v-click>
```

## Reserved words / common gotchas
- Some identifiers are also keywords (e.g. `content`). Avoid using them as slide IDs or element IDs.
- If you see `token recognition error at: '?'` at `line 1:0`, it’s usually a UTF‑8 BOM. The repo is configured to skip it, but your editor may still insert one.

## “What is implemented vs. planned”
Implemented end-to-end:
- `deck` + `head` + `slide` + `markdown` + basic `element` types + `step { reveal ... }`

Parsed but not compiled (yet):
- `template`, `layout zones`, `position`, `animation`, `codeReveal`, `slot/switch`
