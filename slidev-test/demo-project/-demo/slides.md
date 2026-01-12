---
theme: "default"
title: "SlideDeckML -> Slidev (Demo)"
author: "Student"
mdc: true
transition: "slide-left"
---

# SlideDeckML DSL

External DSL (ANTLR) compiled into Slidev `slides.md`.

---

# Agenda

1. What we generate (Slidev Markdown)
2. DSL elements (text/list/code/math)
3. Click steps (`step` -> `v-click`)

---
transition: "fade"
---

# Elements

The compiler emits standard Slidev Markdown + a few Vue wrappers.
<v-click at="1">
- TextBlock -> plain markdown
- ListBlock -> bullet list
- CodeBlock -> fenced code
- EquationBlock -> KaTeX math

</v-click>
<v-click at="2">
$$
\\sum_{i=1}^{n} i = \\frac{n(n+1)}{2}
$$
</v-click>

---
transition: "slide-up"
---

# Steps / clicks

In the DSL:
- `step 1 { reveal X; }`

In Slidev output:
- `<v-click at=\\"1\\"> ... </v-click>`
<v-click at="1">
```java
public static void main(String[] args) {
  System.out.println(\\"Hello Slidev\\");
}

```
</v-click>
<v-click at="2">
This line appears on click 2.
</v-click>

---
class: "text-center"
transition: "fade-out"
---

# Thanks

Questions?
