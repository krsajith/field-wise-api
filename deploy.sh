#!/usr/bin/env bash
set -euo pipefail

# Usage:
#   ./deploy.sh [PID]
# If PID is provided, it will be killed before starting the app.

# Pull latest code
git pull

# Build without tests
mvn clean install -DskipTests

# Show running Java processes (for visibility)
ps -ef | grep java | grep -v grep

# Kill provided PID if given
if [[ "${1:-}" != "" ]]; then
  kill -9 "$1"
fi

# Start the app in background
nohup java -jar target/FieldWise-0.0.1-SNAPSHOT.jar >/tmp/fieldwise.log 2>&1 &

echo "Started FieldWise. Logs: /tmp/fieldwise.log"