#!/usr/bin/env bash

#!/bin/sh

echo "********************************************************"
echo "Waiting for database to start on port $DATABASE_PORT"
echo "********************************************************"
while ! `nc -z feature-toggle-database $DATABASE_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Database has started"

java -agentlib:jdwp=transport=dt_socket,address=5006,server=y,suspend=n \
	-Djava.security.egd=file:/dev/./urandom \
	-Deureka.client.serviceUrl.defaultZone=$EUREKA_SERVER_URI \
	-Dspring.cloud.config.uri=$CONFIG_SERVER_URI \
	-jar /work/*.jar