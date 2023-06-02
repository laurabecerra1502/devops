package org.devops

def clone(paramscmUrl){
    sh "git url: '${paramscmUrl}'"
}

def install(){
    sh 'npm install'
}

