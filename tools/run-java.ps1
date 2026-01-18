param(
  [Parameter(Mandatory=$true)][string]$AntlrJar,
  [Parameter(Mandatory=$true)][string]$Input,
  [Parameter(Mandatory=$true)][string]$Out,
  [string]$ClassesDir = "java/build"
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

if (!(Test-Path $Input)) { throw "Input not found: $Input" }

& powershell -ExecutionPolicy Bypass -File tools/build-java.ps1 -AntlrJar $AntlrJar

Write-Host "Running compiler..."
$cp = "$ClassesDir;$AntlrJar"
java -cp $cp slidedeckml.compiler.Main -i $Input -o $Out
Write-Host "Wrote: $Out"
