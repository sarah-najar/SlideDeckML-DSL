param(
  [Parameter(Mandatory=$true)][string]$AntlrJar,
  [string]$GeneratedDir = "java/generated",
  [string]$SrcDir = "java/src/main/java",
  [string]$OutDir = "java/build"
)

Set-StrictMode -Version Latest
$ErrorActionPreference = "Stop"

if (!(Test-Path $AntlrJar)) { throw "ANTLR jar not found: $AntlrJar" }

if (!(Test-Path $GeneratedDir)) {
  throw "Generated dir not found: $GeneratedDir (run tools/gen-antlr.ps1 first)"
}

New-Item -ItemType Directory -Force $OutDir | Out-Null

$javaFiles = @()
$javaFiles += Get-ChildItem -Recurse -File $GeneratedDir -Filter *.java | ForEach-Object { $_.FullName }
$javaFiles += Get-ChildItem -Recurse -File $SrcDir -Filter *.java | ForEach-Object { $_.FullName }

if ($javaFiles.Count -eq 0) { throw "No .java files found under $GeneratedDir and $SrcDir" }

$sourcesList = Join-Path $env:TEMP ("slidedeckml-sources-{0}.txt" -f ([Guid]::NewGuid().ToString("N")))
$javaFiles | Set-Content -Encoding ASCII $sourcesList

Write-Host "Compiling Java sources..."
& javac -encoding UTF-8 -source 1.8 -target 1.8 -cp $AntlrJar -d $OutDir ("@" + $sourcesList)

Remove-Item -Force $sourcesList
Write-Host "Done. Classes: $OutDir"
