# ANTLR + Java compiler — project structure & how to extend

This repo is split into two layers:
1) **Parsing** (ANTLR grammar → parse tree)
2) **Compilation** (parse tree → model → Slidev `slides.md`)

The goal is to keep syntax work (grammar) and output work (emitter) easy to change independently.

## What’s in the repo

### Grammar (ANTLR4)
- Canonical grammar copy: `grammar/SlideDeckML.g4`
- Java/Maven grammar copy (used by Maven builds): `java/src/main/antlr4/SlideDeckML.g4`

Why there are two:
- Maven’s ANTLR plugin expects grammars under `src/main/antlr4`.
- The root `grammar/` folder is convenient for sharing the DSL grammar outside the Java module.

If you update the grammar, update **both files** (or later, we can automate keeping them in sync).

### Java compiler module
- Java sources: `java/src/main/java/slidedeckml/compiler/`
- Maven config: `java/pom.xml`
- Generated ANTLR sources (after build): `java/target/generated-sources/antlr4/`

Important files:
- `java/src/main/java/slidedeckml/compiler/Main.java`
  - CLI entry point: reads `.sdeck`, writes `slides.md`.
- `java/src/main/java/slidedeckml/compiler/SlideDeckMLCompiler.java`
  - Creates lexer/parser, runs the visitor, calls the emitter.
- `java/src/main/java/slidedeckml/compiler/SlideDeckMLToModelVisitor.java`
  - Converts parse tree → in-memory model (`Models.*`).
- `java/src/main/java/slidedeckml/compiler/Models.java`
  - Very small “AST/model” the emitter consumes.
- `java/src/main/java/slidedeckml/compiler/SlidevEmitter.java`
  - Converts the model → Slidev Markdown (`slides.md`).

## How the build works (Maven)
`java/pom.xml` uses:
- `antlr4-maven-plugin` to generate parser/lexer/visitor sources.
- `exec-maven-plugin` to run the compiler.

In IntelliJ, use the Maven tool window to run:
- `Lifecycle -> package` (generates sources + compiles)
- `Plugins -> exec -> exec:java` (runs compiler)

## How to add a new language feature (practical recipe)

### Step 1 — Decide the user-facing syntax
Write an example in `examples/` first. This prevents “grammar-first” changes that don’t feel good to write.

### Step 2 — Extend the grammar
Edit:
- `java/src/main/antlr4/SlideDeckML.g4`
- `grammar/SlideDeckML.g4`

Tips:
- Prefer adding new rules over making existing rules ambiguous.
- Avoid introducing keywords that collide with common identifiers (we already hit this with `content`).
- If you add a new keyword, consider whether it should still be allowed as an `ID` in some contexts.

### Step 3 — Regenerate sources
Run:
- `mvn clean package` (in the `java` module)

This updates generated classes like:
- `slidedeckml.grammar.SlideDeckMLParser`
- `slidedeckml.grammar.SlideDeckMLBaseVisitor`

### Step 4 — Update the visitor (parse tree → model)
Edit `java/src/main/java/slidedeckml/compiler/SlideDeckMLToModelVisitor.java`:
- Add logic that reads the new parse tree nodes.
- Store the result into the model (usually `Models.Slide`, `Models.Element`, or a new model class).

Keep this layer “semantic”:
- Parse tree types are ANTLR-specific; the model should be compiler-friendly.

### Step 5 — Update the model (if needed)
Edit `java/src/main/java/slidedeckml/compiler/Models.java`:
- Add fields/classes only when the emitter needs them.
- Keep it small; don’t mirror the entire parse tree.

### Step 6 — Update the emitter (model → Slidev)
Edit `java/src/main/java/slidedeckml/compiler/SlidevEmitter.java`:
- Decide how the new feature maps to Slidev Markdown/frontmatter/components.
- Make sure you don’t accidentally create extra `---` separators (they can produce empty slides).

### Step 7 — Add/adjust examples
- Add a “happy path” example in `examples/`.
- Re-run the compiler and inspect the generated `slides.md`.

## Common IntelliJ issues

### “package slidedeckml.grammar does not exist”
It means the generated sources were not created or not added as a source root.
Fix:
- Run `mvn generate-sources` or `mvn package`
- Reimport Maven project in IntelliJ
- Check `java/target/generated-sources/antlr4/slidedeckml/grammar/` exists

### “token recognition error at: '?' line 1:0”
Usually a UTF‑8 BOM or a strange first character.
Fix:
- Save the `.sdeck` as UTF‑8 without BOM, or keep the repo’s BOM-skipping rules.

## Where to look for “what is supported”
- Current syntax: `docs/current-syntax.md`
- Compiler supported subset: `java/SUPPORTED.md`
- Friendlier syntax proposal: `docs/preferred-syntax.md`

