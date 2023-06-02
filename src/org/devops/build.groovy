package org.devops

def clone(Map params){
    def branchName = "${env.GIT_BRANCH}".split('/')[1]
    echo "Hello ${branchName}"
    /*git branch: "${env.GIT_BRANCH}", url: "${params.scmUrl}"*/
}

def install(){
    sh 'npm install'
}

