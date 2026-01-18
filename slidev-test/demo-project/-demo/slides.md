---
title: "BNF Basic Scenarios Demo"
author: "Student"
offlineExport: "true"
template: "company-template"
cssPath: "./style.css"
assetsDir: "./public"
---

# Apprenticeship Slides
Project: **My Project**
Status: _In progress_
<v-click at="1">
<img src="https://sli.dev/logo.svg" alt="Company logo" style="max-width:100%;height:auto;object-fit:contain;" />
</v-click>

---
transition: "fade"
---

# Outline
- Context
- Work done
- Demo
- Conclusion

---
transition: "fade"
---

# Code highlight (line by line)
```java {1|2|3|4|5|6|7}
public class Hello {
  public static void main(String[] args) {
    int a = 1;
    int b = 2;
    System.out.println(a + b);
  }
}
```

---
transition: "fade"
---

# LaTeX math (KaTeX in Slidev)
Inline math: $e^{i\\pi} + 1 = 0$.
Block math:
$$
\\sum_{k=1}^{n} k = \\frac{n(n+1)}{2}
$$
And a matrix:
$$
\\begin{bmatrix}
1 & 2 \\\\
3 & 4
\\end{bmatrix}
$$

---
transition: "slide-left"
---

# Live coding (Monaco)
```ts {monaco-run} { height: '420px', outputHeight: '320px', editorOptions: { readOnly: false, wordWrap: 'on' }, runnerOptions: {runtime: 'LOCAL' } }
const x = 1
const y = x + 2
console.log('y =', y)
```

---
transition: "slide-left"
---

# Live coding (Python)
```py {monaco-run} { height: '420px', outputHeight: '320px', editorOptions: { readOnly: false, wordWrap: 'on' }, runnerOptions: {runtime: 'REMOTE', endpoint: 'http://127.0.0.1:8787/run/python', timeoutMs: 10000 } }
print("Hello from Python")
for i in range(3):
    print("i =", i)
```
> Start the local runner: `python tools/python-runner/server.py`

---
transition: "fade"
---

# Quiz (ON_DEMAND results)
## QUIZ

**What is the output of `print(1+2)`?**

<div style="display:flex;gap:16px;align-items:center;">
  <img src="https://api.qrserver.com/v1/create-qr-code/?size=200x200&amp;data=https%3A%2F%2Fdocs.google.com%2Fforms%2Fd%2Fe%2FREPLACE_WITH_YOUR_GOOGLE_FORM%2Fviewform" alt="QR code" width="200" height="200" />
  <div>
    <div><strong>Scan to participate</strong></div>
    <div><a href="https://docs.google.com/forms/d/e/REPLACE_WITH_YOUR_GOOGLE_FORM/viewform" target="_blank" rel="noreferrer">https://docs.google.com/forms/d/e/REPLACE\_WITH\_YOUR\_GOOGLE\_FORM/viewform</a></div>
  </div>
</div>

<div>
  <div style="font-size:0.9em;opacity:0.8;margin-bottom:8px;">Fill the form directly (Google Forms)</div>
  <iframe src="https://docs.google.com/forms/d/e/REPLACE_WITH_YOUR_GOOGLE_FORM/viewform?embedded=true" width="100%" height="520" frameborder="0" marginheight="0" marginwidth="0">Loading…</iframe>
</div>

### Choices

- 12
- 3
- 1+2

_Results: on demand_

<v-click at="2">
### Answer

- ✅ 3

</v-click>

---
transition: "fade"
---

# Poll (ALWAYS results placeholder)
## POLL

**Which feature is most useful?**

<div style="display:flex;gap:16px;align-items:center;">
  <img src="https://api.qrserver.com/v1/create-qr-code/?size=200x200&amp;data=https%3A%2F%2Fdocs.google.com%2Fforms%2Fd%2Fe%2FREPLACE_WITH_YOUR_GOOGLE_FORM%2Fviewform" alt="QR code" width="200" height="200" />
  <div>
    <div><strong>Scan to participate</strong></div>
    <div><a href="https://docs.google.com/forms/d/e/REPLACE_WITH_YOUR_GOOGLE_FORM/viewform" target="_blank" rel="noreferrer">https://docs.google.com/forms/d/e/REPLACE\_WITH\_YOUR\_GOOGLE\_FORM/viewform</a></div>
  </div>
</div>

<div>
  <div style="font-size:0.9em;opacity:0.8;margin-bottom:8px;">Fill the form directly (Google Forms)</div>
  <iframe src="https://docs.google.com/forms/d/e/REPLACE_WITH_YOUR_GOOGLE_FORM/viewform?embedded=true" width="100%" height="520" frameborder="0" marginheight="0" marginwidth="0">Loading…</iframe>
</div>

### Choices

- Live coding
- Quiz
- Poll

### Results

_(Poll results are collected at runtime)_


---
transition: "slide-left"
---

# Slot + variants (image evolves with steps)
<v-click at="1">
<v-click at="2" hide>
<img src="https://images.unsplash.com/photo-1515879218367-8466d910aaa4?w=900" alt="State 1" style="max-width:100%;height:auto;object-fit:cover;" />
</v-click>
</v-click>
<v-click at="2">
<img src="https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=900" alt="State 2" style="max-width:100%;height:auto;object-fit:cover;" />
</v-click>

---
transition: "fade"
---

# Video demo
<v-click at="1">
<video src="https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4" controls muted style="max-width:100%;height:auto;"></video>
</v-click>

---
transition: "fade"
---

# Conclusion
Thanks!
