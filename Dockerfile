FROM ubuntu
MAINTAINER Karan Chaudhary

# Update aptitude with new repo
RUN apt-get update
# Install git
RUN apt-get install -y git
# make new directory
RUN mkdir /home/DockerTest
# navigate to new directory
RUN cd /home/DockerTest
# clone the git repo to the created directory
RUN git clone https://github.com/ItsKaranzzz/DockerTest.git
#Set working directory
WORKDIR /home/DockerTest

FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY /build/libs/*.jar /app/google_test.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/google_test.jar"]