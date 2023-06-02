package org.devops

def clone(Map params){
    sh "git url: ${params.scmUrl}"   
}

def install(){
    sh 'npm install'
}

