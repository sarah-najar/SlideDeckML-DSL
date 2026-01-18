# Java compiler

This compiler parses the **BNF-style deck syntax** (see `docs/BNF.pdf`) and emits Slidev `slides.md`.

## Maven (recommended)

From the repo root:
- Build: `mvn -f java/pom.xml package`
- Run (default example): `mvn -f java/pom.xml -q clean compile exec:java`
- Run (custom): `mvn -f java/pom.xml -q clean compile exec:java -Dslidedeckml.input=../examples/bnf-basic-scenarios.deck -Dslidedeckml.out=../slidev-test/demo-project/-demo/slides.md`

Notes:
- This module is now **BNF-only** (legacy ANTLR compiler removed).
- Default input is `../examples/bnf-basic-scenarios.deck` (override with `-Dslidedeckml.input=...`).

Notes on extensions:
- Live code blocks use Slidev Monaco and are emitted as `{monaco-run}` (autorun). JS/TS are built-in; other languages require `setup/code-runners.ts`.
- Python plots: set `plot="plot.png"` in the `live` block and save a PNG in code; the runner will return it.
- Quiz/Poll blocks render a QR code when `url:` or `googleForm:` is provided (QR only).
- To show results in-slide, provide either `resultsSheet:` (recommended) or `resultsEmbed:` + `resultsRefreshMs:` (examples in `examples/bnf-basic-scenarios.deck`).
- For `resultsSheet:` the Google Sheet must be shared publicly (at least “Anyone with the link can view”).

## Preview the generated slides in Slidev

### Option A: use your own Slidev project
- Create a Slidev project and put the generated `slides.md` in it.
- Run Slidev: `npx slidev slides.md` (or `npm run dev` if your project has it configured).

### Option B: use the included demo Slidev project (already in this repo)
From `slidev-test/demo-project/-demo`:
- Start Slidev: `npm run dev`
- Generate directly into the Slidev project: use `-Dslidedeckml.out=../slidev-test/demo-project/-demo/slides.md` when running Maven.

## Offline export (web presentation)

In a Slidev project folder (example: `slidev-test/demo-project/-demo`):
- Build static site: `npx slidev build slides.md --base ./`
- Open offline: `dist/index.html`

## Scenario decks
- Scenario 1 (apprenticeship): `examples/scenario1-apprenticeship.deck`
- Scenario 2 (python intro): `examples/scenario2-python-intro.deck`

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
