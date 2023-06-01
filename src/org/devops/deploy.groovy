package org.devops

def deployimage(PROJECT){
    sh "docker run --name ${PROJECT}-p 80${env.BUILD_ID}:3000 -d ${PROJECT}"
}