FROM openjdk:8-jdk-alpine AS build-env

WORKDIR /work
COPY . .
RUN ./gradlew assemble

FROM openjdk:8-jdk-alpine

WORKDIR /work
COPY --from=build-env /work/build/libs /work

COPY --from=build-env /work/run.sh /work/run.sh
RUN chmod +x run.sh
CMD ./run.sh