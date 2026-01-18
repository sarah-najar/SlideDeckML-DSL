#!/usr/bin/env python3
"""
Tiny local HTTP runner for Slidev Monaco code runners (Python).

This is for demo / local usage only. It executes arbitrary Python code.
Do NOT expose it to the internet.
"""

from __future__ import annotations

import argparse
import base64
import json
import os
import shutil
import subprocess
import sys
import tempfile
import time
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer

_LAST_PLOT_PNG: bytes | None = None
_LAST_PLOT_PATH: str | None = None
_PLOT_DIR = tempfile.mkdtemp(prefix="slidev-plots-")


def _json_response(handler: BaseHTTPRequestHandler, status: int, payload: dict) -> None:
    body = json.dumps(payload).encode("utf-8")
    handler.send_response(status)
    handler.send_header("Content-Type", "application/json; charset=utf-8")
    handler.send_header("Content-Length", str(len(body)))
    handler.send_header("Access-Control-Allow-Origin", "*")
    handler.end_headers()
    handler.wfile.write(body)


def _binary_response(handler: BaseHTTPRequestHandler, status: int, content_type: str, data: bytes) -> None:
    handler.send_response(status)
    handler.send_header("Content-Type", content_type)
    handler.send_header("Content-Length", str(len(data)))
    handler.send_header("Access-Control-Allow-Origin", "*")
    handler.send_header("Cache-Control", "no-store, max-age=0")
    handler.end_headers()
    handler.wfile.write(data)


class Handler(BaseHTTPRequestHandler):
    server_version = "SlideDeckMLPythonRunner/0.1"

    def do_OPTIONS(self) -> None:  # noqa: N802
        self.send_response(204)
        self.send_header("Access-Control-Allow-Origin", "*")
        self.send_header("Access-Control-Allow-Methods", "POST, OPTIONS")
        self.send_header("Access-Control-Allow-Headers", "Content-Type")
        self.end_headers()

    def do_GET(self) -> None:  # noqa: N802
        if self.path.rstrip("/") == "":
            _json_response(self, 200, {"ok": True, "endpoints": ["/run/python"]})
            return
        if self.path.split("?", 1)[0].rstrip("/") == "/plot.png":
            global _LAST_PLOT_PNG
            global _LAST_PLOT_PATH
            if _LAST_PLOT_PATH and os.path.isfile(_LAST_PLOT_PATH):
                with open(_LAST_PLOT_PATH, "rb") as img:
                    _binary_response(self, 200, "image/png", img.read())
                return
            if not _LAST_PLOT_PNG:
                _json_response(self, 404, {"error": "No plot available. Run a slide with plot output first."})
                return
            _binary_response(self, 200, "image/png", _LAST_PLOT_PNG)
            return
        if self.path.rstrip("/") == "/health":
            _json_response(self, 200, {"ok": True})
            return
        _json_response(self, 404, {"error": "Not found"})

    def do_POST(self) -> None:  # noqa: N802
        if self.path.rstrip("/") != "/run/python":
            _json_response(self, 404, {"error": "Not found"})
            return

        length = int(self.headers.get("Content-Length") or "0")
        raw = self.rfile.read(length) if length > 0 else b"{}"
        try:
            payload = json.loads(raw.decode("utf-8"))
        except Exception:
            _json_response(self, 400, {"error": "Invalid JSON"})
            return

        code = payload.get("code")
        if not isinstance(code, str):
            _json_response(self, 400, {"error": "Missing 'code' (string)"})
            return

        plot_name = payload.get("plot")
        if isinstance(plot_name, str):
            plot_name = plot_name.strip() or None
        else:
            plot_name = None

        timeout_ms = payload.get("timeoutMs")
        try:
            timeout_s = max(0.1, (int(timeout_ms) / 1000.0)) if timeout_ms is not None else 10.0
        except Exception:
            timeout_s = 10.0

        with tempfile.TemporaryDirectory(prefix="slidev-py-") as tmpdir:
            path = os.path.join(tmpdir, "snippet.py")
            with open(path, "w", encoding="utf-8") as f:
                f.write(code)
                f.write("\n")

            try:
                completed = subprocess.run(
                    [sys.executable, "-u", path],
                    cwd=tmpdir,
                    capture_output=True,
                    text=True,
                    timeout=timeout_s,
                    env={**os.environ, "MPLBACKEND": "Agg"},
                )
                image_b64 = None
                plot_path = None
                if plot_name:
                    plot_path = os.path.join(tmpdir, plot_name)
                    if os.path.isfile(plot_path):
                        with open(plot_path, "rb") as img:
                            data = img.read()
                            image_b64 = base64.b64encode(data).decode("ascii")
                            global _LAST_PLOT_PNG
                            _LAST_PLOT_PNG = data
                            global _LAST_PLOT_PATH
                            ts = int(time.time() * 1000)
                            dest = os.path.join(_PLOT_DIR, f"plot-{ts}.png")
                            shutil.copyfile(plot_path, dest)
                            _LAST_PLOT_PATH = dest
                _json_response(
                    self,
                    200,
                    {
                        "stdout": completed.stdout,
                        "stderr": completed.stderr,
                        "exitCode": completed.returncode,
                        "imagePng": image_b64,
                        "plotPath": _LAST_PLOT_PATH,
                    },
                )
            except subprocess.TimeoutExpired:
                _json_response(self, 408, {"error": f"Timeout after {int(timeout_s * 1000)}ms"})
            except Exception as e:
                _json_response(self, 500, {"error": str(e)})

    def log_message(self, fmt: str, *args) -> None:  # quiet by default
        sys.stderr.write("%s - - [%s] %s\n" % (self.address_string(), self.log_date_time_string(), fmt % args))


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--host", default="127.0.0.1")
    parser.add_argument("--port", type=int, default=8787)
    args = parser.parse_args()

    httpd = ThreadingHTTPServer((args.host, args.port), Handler)
    print(f"Python runner listening on http://{args.host}:{args.port} (POST /run/python)")
    try:
        httpd.serve_forever()
    except KeyboardInterrupt:
        return 0
    finally:
        httpd.server_close()
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
