#!/bin/sh
#
# Gradle Wrapper (POSIX): 재현 가능한 빌드 — 계획 1.4
# Windows에서는 gradlew.bat 사용
#
APP_HOME=$( cd -P "${0%/*}" 2>/dev/null && pwd ) || exit
exec java -Dfile.encoding=UTF-8 -Dorg.gradle.appname=gradlew -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
