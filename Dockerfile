FROM openjdk:8

LABEL maintainer="aiden.liu1997@outlook.com"

ARG BASE_DIR=/app
ARG PROJECT_DIR=.
ARG JAR_FILE_PATH=${BASE_DIR}/ddns-jar-with-dependencies.jar
ARG CONFIG_DIR=${BASE_DIR}/config
ARG LOG_DIR=${BASE_DIR}/log
#ARG EXPOSE_PORT=10088
ARG DATA_DIR=${BASE_DIR}/data

ENV TZ=Asia/Shanghai \
    BASE_DIR=${BASE_DIR} \
    JAR_FILE_PATH=${JAR_FILE_PATH} \
    #EXPOSE_PORT=${EXPOSE_PORT} \
    CONFIG_DIR=${CONFIG_DIR} \
    LOG_DIR=${LOG_DIR} \
    EXPORT_DATA_DIR=${DATA_DIR} \
    JAVA_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${LOG_DIR}"

# 创建数据目录并赋予读写权限
RUN mkdir -p ${DATA_DIR} && chmod -R 777 ${DATA_DIR} \
    && mkdir -p ${LOG_DIR}

WORKDIR ${BASE_DIR}

COPY ${PROJECT_DIR}/target/ddns-jar-with-dependencies.jar ${JAR_FILE_PATH}
COPY ${PROJECT_DIR}/src/main/resources/config.json ${CONFIG_DIR}/config.json

COPY ${PROJECT_DIR}/script/bin/entrypoint.sh ${BASE_DIR}/entrypoint.sh
RUN chmod +x ${BASE_DIR}/entrypoint.sh

#EXPOSE ${EXPOSE_PORT}

ENTRYPOINT ["sh", "-c", "${BASE_DIR}/entrypoint.sh"]