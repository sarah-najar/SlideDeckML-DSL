# Optional: Compiler outline

This repo currently provides the grammar + examples.

To compile `*.sdeck` -> Slidev `slides.md`, implement:

1. Parse with generated ANTLR parser (listener/visitor).
2. Build an AST that matches `docs/domain-model.png`.
3. Emit `slides.md`:
   - write headmatter YAML
   - for each slide: `---` + slide frontmatter YAML + markdown body
   - use `v-click` wrappers to encode `Step.reveal`

If you tell me your target language (C#, Java, TypeScript), I can scaffold a minimal compiler project.
