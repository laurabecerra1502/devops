package org.devops

def pushimage(PROJECT){
    withDockerRegistry([ credentialsId: "retofase2", url: "https://index.docker.io/v1/" ]) {
        sh "docker push laurabecerra/${PROJECT}:${env.BUILD_ID}"
        }
}