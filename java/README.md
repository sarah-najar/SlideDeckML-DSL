# Java compiler

This compiler parses the **BNF-style deck syntax** (see `docs/BNF.pdf`) and emits Slidev `slides.md`.

## Maven (recommended)

From the repo root:
- Build: `mvn -f java/pom.xml package`
- Run (default example): `mvn -f java/pom.xml -q clean compile exec:java`
- Run (custom): `mvn -f java/pom.xml -q clean compile exec:java -Dslidedeckml.input=../examples/bnf-basic-scenarios.deck -Dslidedeckml.out=../slides.md`

Notes:
- This module is now **BNF-only** (legacy ANTLR compiler removed).
- Default input is `../examples/bnf-basic-scenarios.deck` (override with `-Dslidedeckml.input=...`).

Notes on extensions:
- Live code blocks use Slidev Monaco and are emitted as `{monaco-run}` (autorun). JS/TS are built-in; other languages require `setup/code-runners.ts`.
- Quiz/Poll blocks can render a QR code when `url:` or `googleForm:` is provided. If the URL is a `docs.google.com/forms/...` link, the compiler also embeds the form in an `<iframe>`.
- To show *live-updating* results, provide `resultsEmbed:` with an embeddable URL (typically a published Google Sheets chart) and the Slidev component `AutoRefreshFrame` (included in `slidev-test/demo-project/-demo/components/AutoRefreshFrame.vue`).

## Preview the generated slides in Slidev

### Option A: use your own Slidev project
- Create a Slidev project and put the generated `slides.md` in it.
- Run Slidev: `npx slidev slides.md` (or `npm run dev` if your project has it configured).

### Option B: use the included demo Slidev project (already in this repo)
From `slidev-test/demo-project/-demo`:
- Start Slidev: `npm run dev`
- Replace the Slidev project’s `slidev-test/demo-project/-demo/slides.md` with the generated one (copy from `slides.md` at the repo root).

### Python live coding (REMOTE runner)
- Start the local Python runner: `python tools/python-runner/server.py`
- Ensure your Slidev project has `setup/code-runners.ts` (example: `slidev-test/demo-project/-demo/setup/code-runners.ts`)
- Use a live block with `runtime=REMOTE` + `endpoint="http://127.0.0.1:8787/run/python"` (see `examples/bnf-basic-scenarios.deck`)
- Note: code-runner output is per viewer (Slidev runs it in each browser). Aggregating outputs from multiple viewers requires a separate backend/app.



### Notes
- The generated ANTLR sources go to `java/generated/`.
- Compiled class files go to `java/build/`.
- This compiler focuses on: headmatter/frontmatter, markdown blocks, basic elements, and `step { reveal ... }` -> `v-click`.

## Supported subset
- See `java/SUPPORTED.md`.
