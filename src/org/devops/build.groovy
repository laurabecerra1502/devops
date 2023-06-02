package org.devops

def clone(Map params){
    git branch: 'feature', url: "${params.scmUrl}"
}

def install(){
    sh 'npm install'
}

