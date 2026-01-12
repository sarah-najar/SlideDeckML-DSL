# SlideDeckML (external DSL) -> Slidev

This repo contains a starting point for an **external DSL** (parsed with **ANTLR4**) that can be compiled into a Slidev project (primarily a `slides.md`).

## Domain model
- The UML model used for the DSL is in `docs/domain-model.png`.
- Slidev-specific mapping notes are in `docs/slidev-domain.md`.

## Files
- `grammar/SlideDeckML.g4` - ANTLR4 grammar (combined lexer+parser).
- `examples/demo.sdeck` - example DSL input.
- `tools/COMPILER_OUTLINE.md` - suggested compiler pipeline.
- `docs/current-syntax.md` - current DSL syntax (implemented).
- `docs/antlr-project-guide.md` - project structure + how to extend.

## Generate a parser (example)
1. Download ANTLR4 (jar) locally.
2. Generate code (pick one):
   - Java: `java -jar antlr-4.xx-complete.jar -Dlanguage=Java grammar/SlideDeckML.g4 -o generated`
   - C#: `java -jar antlr-4.xx-complete.jar -Dlanguage=CSharp grammar/SlideDeckML.g4 -o generated`
   - TypeScript: `java -jar antlr-4.xx-complete.jar -Dlanguage=TypeScript grammar/SlideDeckML.g4 -o generated`
3. Use the generated parser in your compiler to emit Slidev Markdown.

## Slidev compilation target (baseline)
- Deck headmatter -> YAML at top of `slides.md`.
- Each slide -> `---` separator + optional YAML frontmatter (`layout`, `class`, `transition`) + Markdown body.
- Steps -> `v-click` wrappers (or equivalent) to encode `Step.reveal` and variants.

## Java compiler
- See `java/README.md` for Maven build/run commands and a no-Maven fallback.
