<script setup lang="ts">
import { onMounted, onUnmounted, ref, watch } from 'vue'

const props = withDefaults(defineProps<{
  sheetUrl: string
  gid?: string
  query?: string
  refreshMs?: number
}>(), {
  gid: '',
  query: '',
  refreshMs: 5000,
})

const el = ref<HTMLDivElement | null>(null)
const status = ref('Loading...')
let timer: number | undefined

function extractSheetId(url: string): string | null {
  const m = url.match(/\/spreadsheets\/d\/([a-zA-Z0-9-_]+)/)
  return m?.[1] || null
}

function loadScript(src: string) {
  return new Promise<void>((resolve, reject) => {
    const s = document.createElement('script')
    s.src = src
    s.async = true
    s.onload = () => {
      s.remove()
      resolve()
    }
    s.onerror = () => {
      s.remove()
      reject(new Error(`Failed to load script: ${src}`))
    }
    document.head.appendChild(s)
  })
}

async function ensureGoogleCharts() {
  // @ts-ignore
  if (window.google?.charts?.load)
    return
  await loadScript('https://www.gstatic.com/charts/loader.js')
  // @ts-ignore
  window.google.charts.load('current', { packages: ['corechart'] })
  await new Promise<void>((resolve) => {
    // @ts-ignore
    window.google.charts.setOnLoadCallback(() => resolve())
  })
}

async function draw() {
  try {
    status.value = 'Loading results...'

    const id = extractSheetId(props.sheetUrl || '')
    if (!id) {
      status.value = 'Invalid sheet URL'
      return
    }

    await ensureGoogleCharts()

    const handler = `__sdeck_gviz_${Math.random().toString(36).slice(2)}`
    const gid = props.gid ? String(props.gid).trim() : ''
    const query = (props.query || '').trim()

    const params = new URLSearchParams()
    if (gid) params.set('gid', gid)
    if (query) params.set('tq', query)
    params.set('tqx', `out:json;responseHandler:${handler}`)

    const url = `https://docs.google.com/spreadsheets/d/${id}/gviz/tq?${params.toString()}&__t=${Date.now()}`

    const promise = new Promise<any>((resolve, reject) => {
      // @ts-ignore
      const timeout = window.setTimeout(() => {
        // @ts-ignore
        try { delete window[handler] } catch {}
        reject(new Error('Timed out loading results'))
      }, 12000)
      // @ts-ignore
      window[handler] = (resp: any) => {
        window.clearTimeout(timeout)
        resolve(resp)
      }
    })

    await loadScript(url)
    const response = await promise
    // @ts-ignore
    try { delete window[handler] } catch {}

    // @ts-ignore
    const data = new window.google.visualization.DataTable(response.table)

    if (!el.value) return
    // @ts-ignore
    const chart = new window.google.visualization.ColumnChart(el.value)
    // @ts-ignore
    chart.draw(data, {
      backgroundColor: 'transparent',
      legend: { position: 'none' },
      title: '',
      chartArea: { width: '85%', height: '70%' },
      colors: ['#3b82f6'],
      hAxis: { textStyle: { color: '#e2e8f0' } },
      vAxis: { textStyle: { color: '#e2e8f0' }, gridlines: { color: 'rgba(148,163,184,0.25)' } },
    })

    status.value = ''
  } catch (e: any) {
    status.value = `Cannot load chart. Make sure the sheet is shared publicly.\n${String(e)}`
  }
}

onMounted(() => {
  draw()
  timer = window.setInterval(draw, Math.max(1000, props.refreshMs))
})

onUnmounted(() => {
  if (timer !== undefined) window.clearInterval(timer)
})

watch(() => [props.sheetUrl, props.gid, props.query, props.refreshMs], () => {
  draw()
})
</script>

<template>
  <div class="sdeck-sheet-chart">
    <div v-if="status" class="sdeck-sheet-status">
      {{ status }}
    </div>
    <div ref="el" class="sdeck-sheet-canvas" />
  </div>
</template>

<style scoped>
.sdeck-sheet-chart {
  border: 1px solid rgba(148, 163, 184, 0.22);
  border-radius: 12px;
  padding: 10px;
  background: rgba(15, 23, 42, 0.55);
}
.sdeck-sheet-canvas {
  width: 100%;
  height: 380px;
}
.sdeck-sheet-status {
  white-space: pre-wrap;
  font-size: 0.9em;
  opacity: 0.85;
  padding: 6px 4px 10px;
}
</style>
