package org.devops

def call(PROJECT){
    sh "docker run --name ${PROJECT} -p 80${env.BUILD_ID}:3000 -d laurabecerra/${PROJECT}:${env.BUILD_ID}"
}