import { defineCodeRunnersSetup } from '@slidev/types'

type RunnerOptions = {
  runtime?: string
  endpoint?: string
  timeoutMs?: number
  plot?: string
}

export default defineCodeRunnersSetup(() => {
  return {
    py: runPythonRemote,
    python: runPythonRemote,
  }
})

async function runPythonRemote(code: string, ctx: any) {
  const options = (ctx?.options ?? {}) as RunnerOptions
  const endpoint = options.endpoint || 'http://127.0.0.1:8787/run/python'
  const timeoutMs = typeof options.timeoutMs === 'number' ? options.timeoutMs : 10000
  const plot = options.plot

  if ((options.runtime || '').toUpperCase() === 'LOCAL') {
    return [{ error: 'Python runner does not support runtime=LOCAL (browser). Use runtime=REMOTE with an endpoint.' }]
  }

  const controller = new AbortController()
  const timeout = setTimeout(() => controller.abort(), timeoutMs)
  try {
    if (typeof fetch === 'undefined') {
      return [{ error: 'Python runner: fetch() is not available in this environment.' }]
    }

    const res = await fetch(endpoint, {
      method: 'POST',
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify({ code, timeoutMs, plot }),
      signal: controller.signal,
    })

    const text = await res.text()
    let json: any
    try {
      json = JSON.parse(text)
    } catch {
      json = { error: `Invalid JSON from runner (${res.status}): ${text.slice(0, 300)}` }
    }

    if (!res.ok) {
      return [{ error: json?.error || `Runner error (${res.status})` }]
    }

    const out: any[] = []
    if (json?.stdout) out.push({ text: String(json.stdout) })
    if (json?.stderr) out.push({ text: String(json.stderr), class: 'text-red-500' })
    if (json?.imagePng) {
      out.push({
        html: `<img src="data:image/png;base64,${json.imagePng}" alt="Plot" style="max-width:100%;height:auto;" />`,
      })
    }
    if (json?.plotPath) {
      out.push({ text: `Plot saved at: ${String(json.plotPath)}`, class: 'opacity-70 text-sm' })
    }
    if (json?.error) out.push({ error: String(json.error) })
    if (out.length === 0) out.push({ text: '(no output)', class: 'opacity-70' })
    return out
  } catch (e: any) {
    const msg = e?.name === 'AbortError' ? `Timeout after ${timeoutMs}ms` : String(e)
    return [{
      error:
        `Python runner failed: ${msg}\n`
        + `- Is the server running? python tools/python-runner/server.py\n`
        + `- Endpoint: ${endpoint}\n`
        + `- Note: if you use https for Slidev, the browser may block http endpoints (mixed content).`,
    }]
  } finally {
    clearTimeout(timeout)
  }
}
