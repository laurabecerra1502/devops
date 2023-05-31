package org.devops

def buildimage(PROJECT){
    sh "docker build -t laurabecerra/${PROJECT}:${env.BUILD_ID} ."  
}

