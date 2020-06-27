FROM ubuntu
MAINTAINER Karan Chaudhary

#Install git
RUN apt-get update \
apt-get install -y git
RUN mkdir /testFolder

ADD . /testFolder

RUN useradd -ms /bin/bash admin
RUN chown -R admin:admin /testFolder/DockerTest/

FROM openjdk:8-jre-slim
EXPOSE 8080


ENTRYPOINT java -cp "/opt/testng-6.8.jar:bin" org.testng.TestNG /testFolder/DockerTest/src/test/suite.xml