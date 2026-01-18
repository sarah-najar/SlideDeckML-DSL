# Java compiler project structure (BNF-only)

The Java module is now focused on the **BNF-style syntax** defined in `docs/BNF.pdf`.
It reads a `.deck` file and generates a Slidev `slides.md`.

## What’s in the repo

### Java compiler module
- Maven module: `java/pom.xml`
- Java sources:
  - Entry point: `java/src/main/java/slidedeckml/compiler/Main.java`
  - BNF compiler: `java/src/main/java/slidedeckml/compiler/bnf/BnfCompiler.java`

### Slidev demo project (for your presentation)
- `slidev-test/demo-project/-demo`
  - Components:
    - `slidev-test/demo-project/-demo/components/AutoRefreshFrame.vue` (auto-refresh iframe for live results embeds)
  - Code runner setup:
    - `slidev-test/demo-project/-demo/setup/code-runners.ts` (adds Python runner via HTTP endpoint)

### Examples
- Main demo deck: `examples/bnf-basic-scenarios.deck`

## Build & run (Maven)

From the repo root:
- Build: `mvn -f java/pom.xml package`
- Run: `mvn -f java/pom.xml -q exec:java`
- Run custom input/output:
  - `mvn -f java/pom.xml -q exec:java -Dslidedeckml.input=../examples/bnf-basic-scenarios.deck -Dslidedeckml.out=../slides.md`

## Extending the DSL (practical recipe)

1) Add a new “happy path” example under `examples/`
2) Extend the line-oriented parser in `java/src/main/java/slidedeckml/compiler/bnf/BnfCompiler.java`
3) Re-run the compiler and open the generated `slides.md` in Slidev

## Live coding (Python)

Slidev only executes JS/TS out-of-the-box. For Python you need:
- A code runner setup in the Slidev project:
  - `slidev-test/demo-project/-demo/setup/code-runners.ts`
- A local HTTP runner:
  - `tools/python-runner/server.py`

Run the Python runner:
- `python tools/python-runner/server.py --host 127.0.0.1 --port 8787`

