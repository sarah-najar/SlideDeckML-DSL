---
title: "DSL Project - Demo"
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
<img src="/institution-logo.svg" alt="Institution logo" style="max-width:100%;height:auto;object-fit:contain;" />
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
\begin{bmatrix}
1 & 2 \\\\
3 & 4
\end{bmatrix}
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

# Live coding (Python + plot)
```py {monaco-run} { height: '420px', outputHeight: '320px', editorOptions: { readOnly: false, wordWrap: 'on' }, runnerOptions: {runtime: 'REMOTE', endpoint: 'http://127.0.0.1:8787/run/python', timeoutMs: 12000, plot: 'plot.png' } }
import matplotlib.pyplot as plt

points = [1, 3, 2, 5, 4]
print("points =", points)

plt.plot(points, marker="o")
plt.title("API Monitoring Samples")
plt.savefig("plot.png")
```
> After you click Run, you should see “Compiled successfully”. Go to the next slide for better visualization.
> Start the local runner: `python tools/python-runner/server.py`

---
transition: "slide-left"
---

# Plot (from previous slide)
<img src="http://127.0.0.1:8787/plot.png" alt="Latest plot" style="max-width:100%;height:auto;object-fit:contain;" />
> If the plot is empty, run the previous slide once.

---
transition: "fade"
---

# Quizz DSL
## QUIZ

<div style="display:flex;gap:16px;align-items:center;">
  <img src="https://api.qrserver.com/v1/create-qr-code/?size=200x200&amp;data=https%3A%2F%2Fdocs.google.com%2Fforms%2Fd%2Fe%2F1FAIpQLSeBcyExHOXY6g3IxRFfbfTwdgDBAIYvNoq1WyZmqt_xIjz_Gw%2Fviewform%3Fusp%3Dpublish-editor" alt="QR code" width="200" height="200" />
  <div>
    <div><strong>Scan to participate</strong></div>
    <div><a href="https://docs.google.com/forms/d/e/1FAIpQLSeBcyExHOXY6g3IxRFfbfTwdgDBAIYvNoq1WyZmqt_xIjz_Gw/viewform?usp=publish-editor" target="_blank" rel="noreferrer">https://docs.google.com/forms/d/e/1FAIpQLSeBcyExHOXY6g3IxRFfbfTwdgDBAIYvNoq1WyZmqt\_xIjz\_Gw/viewform?usp=publish-editor</a></div>
  </div>
</div>

<div>
  <div style="font-size:0.9em;opacity:0.8;margin-bottom:8px;">Fill the form directly (Google Forms)</div>
  <iframe src="https://docs.google.com/forms/d/e/1FAIpQLSeBcyExHOXY6g3IxRFfbfTwdgDBAIYvNoq1WyZmqt_xIjz_Gw/viewform?usp=publish-editor&amp;embedded=true" width="100%" height="520" frameborder="0" marginheight="0" marginwidth="0">Loading…</iframe>
</div>

### Answer

_(No correct option marked)_


---
transition: "fade"
---

# Quiz results
## QUIZ

### Choices

- 3

_Results: on demand_

<v-click at="1">
### Answer

- ✅ 3

</v-click>

---
transition: "slide-left"
---

# Slot + variants (overlay + simple animation)
<v-click at="1">
<v-click at="2" hide>
<div class="variant-card"><img src="https://images.unsplash.com/photo-1515879218367-8466d910aaa4?w=1200" alt="API baseline"><div class="variant-overlay">Step 1 · Baseline monitoring</div></div>
</v-click>
</v-click>
<v-click at="2">
<div class="variant-card"><img src="https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=1200" alt="API live"><div class="variant-overlay">Step 2 · Live API metrics</div></div>
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
