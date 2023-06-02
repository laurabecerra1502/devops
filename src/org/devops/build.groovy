package org.devops

def clone(scmUrl){
    sh "git url: '${scmUrl}'"
}

def install(){
    sh 'npm install'
}

