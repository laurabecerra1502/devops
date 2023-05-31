package org.devops

def pushimage(PROJECT){
    withDockerRegistry([ credentialsId: "retofase2", url: "https://index.docker.io/v1/" ]) {
        sh "docker tag ${PROJECT} laurabecerra/:${env.BUILD_ID}"
        sh "docker push laurabecerra/retof2:latest"
        }
}