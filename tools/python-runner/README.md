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

And make sure your Slidev project has a code runner setup file at:
- `setup/code-runners.ts`

This repo includes an example at:
- `slidev-test/demo-project/-demo/setup/code-runners.ts`
