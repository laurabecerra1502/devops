package org.devops

def call(PROJECT){
    sh "docker build -t laurabecerra/${PROJECT}:${env.BUILD_ID} ."  
}

