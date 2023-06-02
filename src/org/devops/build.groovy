package org.devops

def clone(Map params){
    echo "Hello ${env.GIT_BRANCH##*/}"
    /*git branch: "${env.GIT_BRANCH}", url: "${params.scmUrl}"*/
}

def install(){
    sh 'npm install'
}

