package org.devops

def clone(Map params){
    git branch: "${env.BRANCH_NAME}", url: "${params.scmUrl}"
}

def install(){
    sh 'npm install'
}

