FROM maven:3.5.3-jdk-8-alpine

WORKDIR /framework

COPY ./. /framework

