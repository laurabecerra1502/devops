package org.devops

def clone(Map params){
    def branchName = "${env.GIT_BRANCH}".split('/')[1]
    echo "Hello ${branchName}"
    git branch: "${branchName}", url: "${params.scmUrl}"
}

def install(){
    sh 'npm install'
}

