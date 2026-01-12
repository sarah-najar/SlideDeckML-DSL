param(
  [Parameter(Mandatory=$true)][string]$AntlrJar,
  [string]$Grammar = "grammar/SlideDeckML.g4",
  [string]$OutDir = "java/generated",
  [string]$Package = "slidedeckml.grammar"
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

if (!(Test-Path $AntlrJar)) { throw "ANTLR jar not found: $AntlrJar" }
if (!(Test-Path $Grammar)) { throw "Grammar not found: $Grammar" }

New-Item -ItemType Directory -Force $OutDir | Out-Null

Write-Host "Generating ANTLR parser..."
java -jar $AntlrJar -Dlanguage=Java -visitor -no-listener -package $Package -o $OutDir $Grammar
Write-Host "Done. Output: $OutDir"
