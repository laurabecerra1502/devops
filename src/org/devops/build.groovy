package org.devops

def clone(Map params){
    sh "git branch: 'feature', url: ${params.scmUrl}"   
}

def install(){
    sh 'npm install'
}

