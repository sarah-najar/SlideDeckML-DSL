# Java compiler

This compiler parses the **BNF-style deck syntax** (see `docs/BNF.pdf`) and emits Slidev `slides.md`.

# User Acceptance 

Added a simple “user acceptance addon” directly in the generator: a report mode that produces a clean summary + warnings (empty slides, missing headings), plus counts (images, videos, live blocks, interactives). This is a concrete extra service for the DSL.

\section{Évaluation fonctionnelle du langage:Rendus générés}
Le rendu final est assuré par Slidev: le compilateur génère un `slides.md` conforme aux conventions Slidev, ce qui nous a permis de valider rapidement la qualité visuelle et l'ergonomie du DSL sur de vrais decks.

Pour les blocs `live`, nous avons configuré Monaco Editor et ajouté un runner Python: l'exécution renvoie du texte et des images, et nous avons prévu le cas des plots en sauvegardant un PNG côté code (ex: `plot="plot.png"`). Cette intégration a nécessité un ajustement de la configuration `setup/code-runners.ts` afin d'exposer l'endpoint et d'afficher correctement les résultats dans la slide.

Les principales difficultés rencontrées:
- synchroniser le modèle d'exécution asynchrone de Slidev avec nos blocks `live` (retour de sortie + image),
- stabiliser le séparateur entre les slides (`===` dans le DSL, puis `---` dans le rendu Slidev),
- adapter la grammaire pour couvrir les nouveaux attributs (plots, endpoints, options) sans casser la compatibilité.

Nous avons aussi appliqué un "user acceptance" via un mode rapport: un résumé clair et des avertissements (slides vides, titres manquants), ce qui renforce la validation fonctionnelle côté utilisateur.

Pour les formulaires, nous utilisons un QR code pour l'accès rapide et nous exigeons un lien de formulaire ainsi qu'un lien Graph pour relier et exploiter les résultats.

Enfin, nous avons choisi d'utiliser une grammaire ANTLR externe plutôt qu'intégrée au code. Un DSL externe est plus maintenable (spécification centralisée), réutilisable entre outils/compilateurs, plus facile à tester, et il sépare nettement la syntaxe des détails d'implémentation. Cette séparation a aussi facilité l'évolution du langage (modifications de grammaire, nouveaux séparateurs, nouveaux attributs) sans réécrire le moteur. Côté structure, la grammaire est isolée du runtime, les sources générées sont regroupées dans `java/generated/`, et le compilateur consomme ces classes pour produire le `slides.md`.

What’s new:

BnfCompiler.java
Adds compileReportMarkdown(...) and a ReportEmitter (summary + warnings).
Main.java
Adds --report <path> option.
README.md + current-syntax.md
Document the addon

## Maven (recommended)

From the repo root:
- Build: `mvn -f java/pom.xml package`
- Run (default example): `mvn -f java/pom.xml -q clean compile exec:java`
- Run (custom): `mvn -f java/pom.xml -q clean compile exec:java -Dslidedeckml.input=../examples/bnf-basic-scenarios.deck -Dslidedeckml.out=../slidev-test/demo-project/-demo/slides.md`
- Run (custom + report): `mvn -f java/pom.xml -q clean compile exec:java -Dslidedeckml.input=../examples/bnf-basic-scenarios.deck -Dslidedeckml.out=../slidev-test/demo-project/-demo/slides.md -Dexec.args="--report ../slidev-test/demo-project/-demo/report.md"`

Notes:
- This module is now **BNF-only** (legacy ANTLR compiler removed).
- Default input is `../examples/bnf-basic-scenarios.deck` (override with `-Dslidedeckml.input=...`).

Notes on extensions:
- Live code blocks use Slidev Monaco and are emitted as `{monaco-run}` (autorun). JS/TS are built-in; other languages require `setup/code-runners.ts`.
- Python plots: set `plot="plot.png"` in the `live` block and save a PNG in code; the runner will return it.
- Report addon: `--report <path>` generates a quick summary + warnings for the deck.
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
