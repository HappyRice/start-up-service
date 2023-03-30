#! /bin/sh

docker build -t start-up-jar:latest --build-arg SPRING_PROFILES_ACTIVE=${1:-localdocker} .