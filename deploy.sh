#!/usr/bin/env bash
set -euo pipefail

# Usage:
#   ./deploy.sh

# Pull latest code
git pull

# Build without tests
mvn clean install -DskipTests

# Show running Java processes (for visibility)
ps -ef | grep java | grep -v grep

# Kill only the FieldWise jar process (ignore if not running)
pkill -f 'target/FieldWise-0.0.1-SNAPSHOT.jar' || true

# Start the app in background
nohup java -jar target/FieldWise-0.0.1-SNAPSHOT.jar >/tmp/fieldwise.log 2>&1 &

echo "Started FieldWise. Logs: /tmp/fieldwise.log"
