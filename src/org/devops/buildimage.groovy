package org.devops

def image(Map params){
    sh "docker build -t ${params.image} ."  
}

