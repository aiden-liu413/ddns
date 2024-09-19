#!/bin/bash

exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom  -jar ${JAR_FILE_PATH}
