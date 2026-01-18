# Slidev concepts used by the DSL

This document captures the **minimum Slidev details** needed to design a stable external DSL and compiler target.

## 1) Slide file structure
- Slidev input is a Markdown file (default `slides.md`).
- Slides are separated by a line containing `---`.

## 2) Headmatter vs frontmatter
- **Headmatter**: YAML at the very top of `slides.md` (applies globally).
  - Common keys: `theme`, `title`, `mdc`.
- **Frontmatter**: YAML at the beginning of *each* slide.
  - Common keys: `layout`, `class`, `transition`.

## 3) Layouts
- Layout is selected per slide with `layout: <name>`.
- Layout resolution order: built-ins -> theme -> addons -> local `layouts/` folder.

## 4) Notes
- Presenter notes are comment blocks placed at the end of a slide.

## 5) Steps / click animations
- Slidev click animations are expressed with `v-click` / `<v-click>`.
- A slide can have multiple clicks; elements can appear/hide on specific clicks.
- This maps well to the domain model's `Step` that reveals elements.

## 6) Slide transitions
- Enable with `transition:` in headmatter (global) or frontmatter (per slide).
- Built-in transition names include: `fade`, `fade-out`, `slide-left`, `slide-right`, `slide-up`, `slide-down`, `view-transition`.

## 7) Export/offline
- `slidev export` can export to PDF/PPTX/PNG/MD.
- `--with-clicks` exports click steps into multiple pages.

## Mapping from UML domain model to Slidev
- `SlideDeck` -> headmatter YAML + list of slides.
- `Slide.layout` -> slide frontmatter `layout:`.
- `Transition` / `TransitionType` -> slide frontmatter `transition:` (string or advanced object).
- `Step` -> click count + `v-click` wrappers.
- `TextBlock/ListBlock/ImageElement/VideoElement` -> Markdown + optional HTML wrappers for positioning.
- `CodeBlock` / `CodeReveal` -> code fences; reveals can be compiled as multiple code fences, or line-level `v-click`.
- `Slot` / `SlotSwitch` -> variants compiled as mutually-exclusive `v-click` blocks.

Sources (Slidev docs):
- `https://sli.dev/guide/syntax` (separators, frontmatter, notes)
- `https://sli.dev/guide/layout` (layouts)
- `https://sli.dev/guide/animations` (clicks + transitions)
- `https://sli.dev/guide/exporting` (export)
- `https://sli.dev/custom/directory-structure` (project structure)
