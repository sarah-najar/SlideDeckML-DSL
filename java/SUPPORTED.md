## Java compiler (supported subset)
- `head { theme/title/author/mdc/exportFilename; defaults { transition ... } }`
- `slide { layout/class/transition/title/notes; markdown "..."; element ...; step N { reveal ... } }`
- Elements compiled: `TextBlock`, `ListBlock`, `ImageElement`, `VideoElement`, `CodeBlock`, `EquationBlock`
- Steps: `step N { reveal elementId; }` -> wraps that element in `<v-click at="N"> ... </v-click>`
- Absolute positioning: `position absolute { x ...; y ...; width ...; height ...; unit PX|PERCENT; anchor ...; }` -> emitted as an absolutely positioned `<div style="...">`
- Slots/switch (for evolving visuals): place `slot name;` then define variant elements with `slotOf name;`, and use `step N { switch name show variantId; }`
- Code line-by-line (scenario-style): `step N { codeReveal codeId 1..K; }` emits a sequence of exclusive code states per step (using nested `<v-click>`).

Not yet compiled (parsed only / ignored in output): rich animations (`animation { ... }`), zones/layout structures, template import pipeline, validation.

Note:
- If the deck has global headmatter, the compiler currently omits slide frontmatter for the first slide to avoid Slidev showing an empty first slide.
