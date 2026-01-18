## Java compiler (supported subset — BNF)

Input syntax: `docs/BNF.pdf` (implemented in `java/src/main/java/slidedeckml/compiler/bnf/BnfCompiler.java`)

### Supported
- Deck headmatter: `@deck ... @enddeck` with `key: value` lines
- Slides: `@slide ... @endslide` + Markdown content
- Click reveals: `@N ...` (wraps the line/block in `<v-click at="N"> ... </v-click>`)
- Slots/variants: `:::slot name ... :::` with `@N` entries
- Code:
  - Regular fenced code blocks (passed through)
  - Line-by-line highlight: ` ```code lang {revealLines=true} ... ``` ` (emits Slidev highlight-steps meta)
  - Live coding: ` ```live lang {...} ... ``` ` (emits `{monaco-run}` + `runnerOptions`)
- Interactive blocks:
  - `:::interactive QUIZ|POLL id ... :::`
  - QR code + Google Forms iframe when `googleForm:` is a `docs.google.com/forms/...` URL
  - Optional results embed: `resultsEmbed:` + auto-refresh via `AutoRefreshFrame`

### Not supported / limitations
- Fetching Google Forms results automatically without an embeddable source (Google Forms has no simple public results API)
- Multi-user “shared console” aggregation for live coding (Slidev runs code in each viewer’s browser)

