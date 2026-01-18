# SlideDeckML — current syntax (BNF-style, implemented)

This is the **current working syntax** implemented by:
- `java/src/main/java/slidedeckml/compiler/bnf/BnfCompiler.java`

It is based on the scenario BNF in `docs/BNF.pdf` and is designed to compile into **Slidev** `slides.md`.

## 1) Deck

```text
@deck
title: "My deck"
author: "Me"
theme: "default"
@enddeck
```

Everything inside `@deck` becomes Slidev **headmatter** (YAML at the very top of `slides.md`).

### Company template helper (for the lab scenarios)

If you provide any of these keys, the compiler injects a simple header on every slide:
- `template: "company-template"` *(any value containing `template` works)*
- `companyName: "..."` *(text in header)*
- `institutionLogo: "/institution-logo.svg"` *(left logo)*
- `companyLogo: "/company-logo.svg"` *(right logo)*
- `primaryColor: "#RRGGBB"` *(CSS variable `--sdeck-primary`)*
- `fontFamily: "..."` *(applies to `.slidev-layout`)*

Example:

```text
@deck
title: "Apprenticeship Report"
companyName: "Company Name"
institutionLogo: "/institution-logo.svg"
companyLogo: "/company-logo.svg"
primaryColor: "#3b82f6"
fontFamily: "Inter, Segoe UI, Arial, sans-serif"
@enddeck
```

## 2) Slides

Each slide starts with `@slide` and ends with `@endslide`:

```text
@slide id: intro
layout: cover
transition: SLIDE
@endslide

# Hello
This is normal Markdown.
```

In the DSL, separate slides with `---` (same as Slidev).

## 3) Reveal on click

Prefix a line with `@N` to show it at click step `N`:

```text
@1 This appears at click 1
@2 This appears at click 2
```

This compiles to Slidev `v-click` directives.

## 4) Images and videos

Image:
```text
@1 ![Alt text](/company-logo.svg) {fit=COVER}
```

Video:
```text
@1 !video(./public/demo.mp4) {controls muted loop}
```

### Optional positioning + animation (images/videos)

For `![...](...)` and `!video(...)` lines you can add extra options:
- `x`, `y`, `w`, `h` (numbers)
- `unit=PERCENT|PX` (default: `PERCENT`)
- `anchor=TOP_LEFT|TOP_CENTER|TOP_RIGHT|CENTER_LEFT|CENTER|CENTER_RIGHT|BOTTOM_LEFT|BOTTOM_CENTER|BOTTOM_RIGHT`
- `animate=FADE_IN|SLIDE_UP|ZOOM_IN|NONE`

Example:

```text
@1 ![Work site](/site-photo.svg) {fit=COVER x=50 y=62 w=92 h=58 unit=PERCENT anchor=CENTER animate=ZOOM_IN}
```

## 5) Slots + variants

Use a slot to swap content across steps:

```text
:::slot viz
@1 ![State 1](./public/a.png) {fit=CONTAIN}
@2 ![State 2](./public/b.png) {fit=CONTAIN}
:::
```

## 6) Code blocks

Normal code blocks are kept as-is:

```text
```ts
console.log('hello')
```
```

### Line-by-line highlight (one line per click)

Slidev supports line-by-line focus using “highlight steps” in the fence info string.
Write a normal fenced code block and add `{1|2|3|...}`:

````text
```ts {1|2|3}
const a = 1
const b = 2
console.log(a + b)
```
````

Each click moves the highlight to the next step.

## 7) Live coding (Monaco)

Use a `live` block:

````text
```live ts { editable=true runtime=LOCAL showOutput=true autorun=true }
console.log('runs in the browser for TS/JS')
```
````

For Python, use `runtime=REMOTE` with an endpoint (see `tools/python-runner/README.md`):

````text
```live py { editable=true runtime=REMOTE endpoint="http://127.0.0.1:8787/run/python" timeoutMs=10000 showOutput=true autorun=true }
print("Hello from Python")
```
````

To show a plot in the output, save a PNG and pass `plot="plot.png"`:

````text
```live py { editable=true runtime=REMOTE endpoint="http://127.0.0.1:8787/run/python" timeoutMs=12000 showOutput=true autorun=true plot="plot.png" }
import matplotlib.pyplot as plt
plt.plot([1, 3, 2, 5])
plt.savefig("plot.png")
```
````

Note: the Python runner must have `matplotlib` installed.

To show the plot on a separate slide, embed:
- `![Latest plot](http://127.0.0.1:8787/plot.png)`

## 8) Quiz / Poll (Google Forms)

Interactive block (QR code only):

```text
:::interactive QUIZ q1
qrOnly: true
googleForm: "https://docs.google.com/forms/d/e/REPLACE/viewform"
:::
```

### Live-updating results (embed)

Google Forms does not provide a simple public “live results API”.
If you want **live updating results inside Slidev**, the most reliable approach is to:
- link the form to a Google Sheet
- publish a chart/embed from the sheet
- provide it as `resultsEmbed:`

```text
:::interactive POLL p1
question: Which one?
results: ALWAYS
googleForm: "https://docs.google.com/forms/d/e/REPLACE/viewform"
resultsEmbed: "https://docs.google.com/spreadsheets/d/e/REPLACE/pubchart?oid=...&format=interactive"
resultsRefreshMs: 5000
choices:
- A
- B
:::
```

You can also render a chart directly from a Google Sheet URL (no hardcoded URLs, you provide them):

```text
resultsSheet: "https://docs.google.com/spreadsheets/d/ID/edit#gid=123"
resultsGid: 123
resultsQuery: "select B, count(B) group by B label count(B) 'Votes'"
```

The included Slidev demo project contains:
- `slidev-test/demo-project/-demo/components/AutoRefreshFrame.vue`
- `slidev-test/demo-project/-demo/components/GoogleSheetChart.vue`

### Results-only slide (to avoid showing the form URL)

Use `resultsOnly: true` to keep the slide clean, then reveal results with `@step`:

```text
:::interactive QUIZ q1
resultsOnly: true
results: ON_DEMAND
resultsSheet: "https://docs.google.com/spreadsheets/d/ID/edit#gid=123"
resultsGid: 123
resultsQuery: "select B, count(B) group by B label count(B) 'Votes'"
choices:
- A
- B [correct]
:::

@step 1: showResults(q1)
```

Note: the Google Sheet must be shared publicly (at least “Anyone with the link can view”) for the chart to load.

## 9) Addon: deck report (user acceptance)

Generate a report with slide counts and simple warnings:

- Add `--report <path>` to the compiler command.
- The report includes slide titles, counts, and basic lint checks (empty slide, missing heading).

Example:

```text
mvn -f java/pom.xml -q clean compile exec:java -Dexec.args="-i examples/bnf-basic-scenarios.deck -o slides.md --report report.md"
```
