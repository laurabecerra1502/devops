package org.devops

def clone(Map params){
    echo "Hello ${env.BRANCH_NAME}"
    /*git branch: "${env.GIT_BRANCH}", url: "${params.scmUrl}"*/
}

def install(){
    sh 'npm install'
}

