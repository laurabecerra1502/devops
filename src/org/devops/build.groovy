package org.devops

def clone(Map parameters){
    sh "git url: '${parameters.scmUrl}'"
}

def install(){
    sh 'npm install'
}

