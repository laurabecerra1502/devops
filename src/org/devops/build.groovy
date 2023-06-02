package org.devops

def clone(Map params){
    git branch: "${branch_name}", url: "${params.scmUrl}"
}

def install(){
    sh 'npm install'
}

