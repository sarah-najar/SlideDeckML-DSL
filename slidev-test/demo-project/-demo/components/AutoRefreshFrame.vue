<script setup lang="ts">
import { computed, onMounted, onUnmounted, ref } from 'vue'

const props = withDefaults(defineProps<{
  src: string
  intervalMs?: number
  height?: string | number
}>(), {
  intervalMs: 5000,
  height: 420,
})

const tick = ref(0)
let timer: number | undefined

onMounted(() => {
  timer = window.setInterval(() => {
    tick.value++
  }, Math.max(1000, props.intervalMs))
})

onUnmounted(() => {
  if (timer !== undefined)
    window.clearInterval(timer)
})

const url = computed(() => {
  const base = props.src || ''
  const sep = base.includes('?') ? '&' : '?'
  return `${base}${sep}__t=${tick.value}`
})
</script>

<template>
  <iframe
    :src="url"
    width="100%"
    :height="String(props.height)"
    frameborder="0"
    style="border:1px solid rgba(255,255,255,0.15); border-radius:8px;"
  />
</template>

