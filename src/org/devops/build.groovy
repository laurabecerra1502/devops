package org.devops

def clone(Map params){
    "echo ${GIT_BRANCH_NAME}".
    /*git branch: "${env.GIT_BRANCH}", url: "${params.scmUrl}"*/
}

def install(){
    sh 'npm install'
}

