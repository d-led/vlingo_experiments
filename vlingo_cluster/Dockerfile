FROM openjdk:8-jdk

ARG  GRADLE_VERSION=6.0.1
ARG start
ENV start ${start}

RUN apt-get update && apt-get install -y wget unzip

RUN wget --quiet https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip -q gradle-${GRADLE_VERSION}-bin.zip

COPY . .

RUN gradle-${GRADLE_VERSION}/bin/gradle --stacktrace --no-daemon shadowJar

CMD echo "--> ${start}" && java -jar build/libs/vlingo_cluster_experiment.jar ${start}
