#!/bin/bash

cd /root/.jenkins/workspace/smartCanteen_deploy/docker

docker stop smartcanteen && docker rm smartcanteen

docker rmi smartcanteen:v1

docker build -t smartcanteen:v1 .

docker run -d --name="smartcanteen" --restart=always --net=host -v /data/dockers/smartCanteen/logs:/usr/local/logs smartcanteen:v1
