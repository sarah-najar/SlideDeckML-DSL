#!/usr/bin/env python3
"""
Tiny local HTTP runner for Slidev Monaco code runners (Python).

This is for demo / local usage only. It executes arbitrary Python code.
Do NOT expose it to the internet.
"""

from __future__ import annotations

import argparse
import json
import os
import subprocess
import sys
import tempfile
from http.server import BaseHTTPRequestHandler, ThreadingHTTPServer


def _json_response(handler: BaseHTTPRequestHandler, status: int, payload: dict) -> None:
    body = json.dumps(payload).encode("utf-8")
    handler.send_response(status)
    handler.send_header("Content-Type", "application/json; charset=utf-8")
    handler.send_header("Content-Length", str(len(body)))
    handler.send_header("Access-Control-Allow-Origin", "*")
    handler.end_headers()
    handler.wfile.write(body)


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
                )
                _json_response(
                    self,
                    200,
                    {
                        "stdout": completed.stdout,
                        "stderr": completed.stderr,
                        "exitCode": completed.returncode,
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

