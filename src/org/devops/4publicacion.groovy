package org.devops

def push(Map params){
    withDockerRegistry([ credentialsId: "retofase2", url: "https://index.docker.io/v1/" ]) {
        sh 'docker tag retof2 laurabecerra/retof2:latest'
        sh 'docker push laurabecerra/retof2:latest'
        }
}