# Python Code Runner (Slidev `monaco-run`)

This is a tiny local HTTP server that executes Python snippets for Slidev's Monaco live-coding blocks.

It is meant for **local demos only**. It executes arbitrary code: do not expose it to the internet.

## Run the server

From the repo root:

```powershell
python tools/python-runner/server.py --host 127.0.0.1 --port 8787
```

It will serve:
- `POST http://127.0.0.1:8787/run/python`

## Use it from the DSL (BNF live blocks)

In your deck:

````text
```live py { runtime=REMOTE endpoint="http://127.0.0.1:8787/run/python" timeoutMs=10000 showOutput=true }
print("Hello from Python")
```
````

To return a plot image, save a PNG and pass `plot="plot.png"`:

````text
```live py { runtime=REMOTE endpoint="http://127.0.0.1:8787/run/python" timeoutMs=12000 showOutput=true plot="plot.png" }
import matplotlib.pyplot as plt
plt.plot([1, 2, 3])
plt.savefig("plot.png")
```
````

Note: you need `matplotlib` installed in the Python environment running the server.

You can display the latest plot on a separate slide by loading:
- `http://127.0.0.1:8787/plot.png`

The runner also saves each plot in a temp folder and returns the path in the live output as:
`Plot saved at: ...`

And make sure your Slidev project has a code runner setup file at:
- `setup/code-runners.ts`

This repo includes an example at:
- `slidev-test/demo-project/-demo/setup/code-runners.ts`
