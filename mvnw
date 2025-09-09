#!/bin/sh
# Maven Wrapper launcher
WRAPPER_JAR=".mvn/wrapper/maven-wrapper-3.2.0.jar"
PROPS_FILE=".mvn/wrapper/maven-wrapper.properties"
if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Downloading Maven Wrapper..." >&2
  mkdir -p .mvn/wrapper
  MVNW_URL=$(grep -E '^wrapperUrl=' "$PROPS_FILE" | cut -d= -f2-)
  curl -fsSL "$MVNW_URL" -o "$WRAPPER_JAR"
fi
exec java -jar "$WRAPPER_JAR" -Dmaven.multiModuleProjectDirectory="$PWD" -Dmaven.wrapper.disableScripts=false "$@"
