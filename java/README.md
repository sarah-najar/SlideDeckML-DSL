# Java compiler

This compiler parses `*.sdeck` (SlideDeckML DSL) and emits Slidev `slides.md`.

## Maven (recommended)

From the repo root:
- Build: `mvn -f java/pom.xml package`
- Run (default example): `mvn -f java/pom.xml -q exec:java`
- Run (custom): `mvn -f java/pom.xml -q exec:java -Dslidedeckml.input=../examples/demo.sdeck -Dslidedeckml.out=../slides.md`

Notes:
- ANTLR sources are generated automatically by `antlr4-maven-plugin` from `java/src/main/antlr4/SlideDeckML.g4`.
- If IntelliJ shows `package slidedeckml.grammar does not exist`, run `mvn -f java/pom.xml generate-sources` and then reimport the Maven project.



### Notes
- The generated ANTLR sources go to `java/generated/`.
- Compiled class files go to `java/build/`.
- This compiler focuses on: headmatter/frontmatter, markdown blocks, basic elements, and `step { reveal ... }` -> `v-click`.

## Supported subset
- See `java/SUPPORTED.md`.
