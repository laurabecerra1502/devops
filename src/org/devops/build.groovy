package org.devops

def clone(Map params){
    echo " Hola ${env.BRANCH_NAME}"
    /*git branch: "${env.BRANCH_NAME}", url: "${params.scmUrl}"*/
}

def install(){
    sh 'npm install'
}

