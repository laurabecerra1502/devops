package org.devops

def clone(Map params){
    echo " Hola ${env.GIT_BRANCH}"
    /*git branch: "${env.BRANCH_NAME}", url: "${params.scmUrl}"*/
}

def install(){
    sh 'npm install'
}

