## Java compiler (supported subset)
- `head { theme/title/author/mdc/exportFilename; defaults { transition ... } }`
- `slide { layout/class/transition/title/notes; markdown "..."; element ...; step N { reveal ... } }`
- Elements compiled: `TextBlock`, `ListBlock`, `ImageElement`, `VideoElement`, `CodeBlock`, `EquationBlock`
- Steps: `step N { reveal elementId; }` -> wraps that element in `<v-click at="N"> ... </v-click>`

Not yet compiled (parsed only / ignored in output): `CodeReveal`, `SlotSwitch`, positions, animations, zones.

Note:
- If the deck has global headmatter, the compiler currently omits slide frontmatter for the first slide to avoid Slidev showing an empty first slide.
