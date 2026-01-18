## Java compiler (supported subset — BNF)

Input syntax: `docs/BNF.pdf` (implemented in `java/src/main/java/slidedeckml/compiler/bnf/BnfCompiler.java`)

### Supported
- Deck headmatter: `@deck ... @enddeck` with `key: value` lines
- Slides: `@slide ... @endslide` + Markdown content
- Click reveals: `@N ...` (wraps the line/block in `v-click` directives)
- Slots/variants: `:::slot name ... :::` with `@N` entries
- Code:
  - Regular fenced code blocks (passed through)
  - Line-by-line highlight: ` ```code lang {revealLines=true} ... ``` ` (emits Slidev highlight-steps meta)
  - Live coding: ` ```live lang {...} ... ``` ` (emits `{monaco-run}` + `runnerOptions`)
- Interactive blocks:
  - `:::interactive QUIZ|POLL id ... :::`
  - QR code (only) when `url:`/`googleForm:` is provided
  - Optional results chart: `resultsSheet:` + `resultsGid:` + `resultsQuery:` via `GoogleSheetChart`
  - Optional results embed: `resultsEmbed:` + auto-refresh via `AutoRefreshFrame`
  - Clean results slide: `resultsOnly: true` + `@step N: showResults(id)`

### Not supported / limitations
- Fetching Google Forms results automatically without an embeddable source (Google Forms has no simple public results API)
- Multi-user “shared console” aggregation for live coding (Slidev runs code in each viewer’s browser)
