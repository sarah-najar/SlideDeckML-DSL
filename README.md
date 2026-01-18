# SlideDeckML (external DSL) -> Slidev

This repo contains an **external DSL** that compiles into Slidev (generates a `slides.md`).

The current implementation targets the **BNF-style syntax** from `docs/BNF.pdf` (line-oriented parser in Java).

## Domain model
- The UML model used for the DSL is in `docs/domain-model.png`.
- Slidev-specific mapping notes are in `docs/slidev-domain.md`.

## Files
- `examples/bnf-basic-scenarios.deck` - main demo input (BNF-style)
- `docs/BNF.pdf` - scenario syntax reference
- `docs/current-syntax.md` - current implemented syntax summary
- `docs/antlr-project-guide.md` - project structure + how to extend
- `java/` - Maven Java compiler module
- `slidev-test/demo-project/-demo` - Slidev demo project (optional)

## Slidev compilation target (baseline)
- Deck headmatter -> YAML at top of `slides.md`.
- Each slide -> `---` separator + optional YAML frontmatter (`layout`, `class`, `transition`) + Markdown body.
- Steps -> `v-click` wrappers (or equivalent) to encode `Step.reveal` and variants.

## Java compiler
- See `java/README.md` for Maven build/run commands and a no-Maven fallback.
