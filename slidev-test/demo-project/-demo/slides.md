---
theme: "default"
title: "SlideDeckML - Basic Scenarios Demo"
author: "Student"
mdc: true
transition: "slide-left"
---

# Apprenticeship Slides

**Project:** My Project
**Status:** In progress
<v-click at="1">
<img src="https://images.unsplash.com/photo-1521791136064-7986c2920216?w=900" alt="Work site" style="max-width:100%;height:auto;" />
</v-click>

---

# Outline

1. Context
2. Work done
3. Demo
4. Conclusion

---
transition: "fade"
---

# Click animations

We reveal text and images step-by-step.
<v-click at="1">
Step 1: show a first point
</v-click>
<v-click at="2">
Step 2: show a second point
</v-click>
<v-click at="3">
<div style="position:absolute;left:90%;top:12%;width:10%;height:10%;z-index:1;transform:translate(-50%, 0);">
<img src="https://sli.dev/logo.svg" alt="Slidev logo" style="width:100%;height:100%;object-fit:contain;" />
</div>
</v-click>

---

# Video demo

The video element is emitted as HTML `<video>`.
<v-click at="1">
<video src="https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4" muted controls></video>
</v-click>

---
class: "text-center"
transition: "fade-out"
---

# Conclusion

Thanks!

---

# Python intro

Next slide: code appears line-by-line, and an image evolves with it.

---
transition: "slide-up"
---

# Code line-by-line + evolving image

We use:
- `codeReveal` to show progressively more lines
- `slot` + `switch` to change the image at the same steps
<v-click at="1">
<v-click at="2" hide>
<div style="position:absolute;left:75%;top:55%;width:40%;height:40%;z-index:1;transform:translate(-50%, -50%);">
<img src="https://images.unsplash.com/photo-1515879218367-8466d910aaa4?w=900" alt="State 1" style="width:100%;height:100%;object-fit:contain;" />
</div>
</v-click>
</v-click>
<v-click at="2">
<div style="position:absolute;left:75%;top:55%;width:40%;height:40%;z-index:1;transform:translate(-50%, -50%);">
<img src="https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=900" alt="State 2" style="width:100%;height:100%;object-fit:contain;" />
</div>
</v-click>
```py {1|1-2|1-3}
x = 1
y = x + 2
print(y)

```

---

# Mix text + image with positioning

Absolute positioning (percent and px) on the same slide.
<v-click at="1">
<div style="position:absolute;left:24px;top:24px;z-index:2;">
<div>Left label (px)</div>
</div>
</v-click>
<v-click at="2">
<div style="position:absolute;left:80%;top:50%;width:30%;height:30%;z-index:1;transform:translate(-50%, -50%);">
<img src="https://sli.dev/logo.svg" alt="Right logo" style="width:100%;height:100%;object-fit:contain;" />
</div>
</v-click>
