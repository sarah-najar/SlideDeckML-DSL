import { defineTransformersSetup } from '@slidev/types'

/**
 * Safety net:
 * If a ` ```code <lang> ... ` fence ever appears in slides.md, Shiki will try to load
 * language "code" and crash. Rewrite it to a normal fence (` ```<lang> ... `).
 */
export default defineTransformersSetup(() => {
  return {
    preCodeblock: [
      async (ctx) => {
        const original = ctx.s.toString()
        const rewritten = original.replace(
          /^```(?:\s*)code\s+([^\s{]+)(.*)$/gmi,
          (_m, lang, rest) => `\`\`\`${lang}${rest}`,
        )

        if (rewritten !== original)
          ctx.s.overwrite(0, original.length, rewritten)
      },
    ],
  }
})

