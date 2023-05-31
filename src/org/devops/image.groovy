package org.devops

def buildimage(PROJECT){
    sh "docker build -t ${PROJECT}:${env.BUILD_ID} ."  
}

