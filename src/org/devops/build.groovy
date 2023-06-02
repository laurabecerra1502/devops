package org.devops

def clone(Map params){
    sh "git clone ${params.scmUrl}"
}

def install(){
    sh 'npm install'
}

