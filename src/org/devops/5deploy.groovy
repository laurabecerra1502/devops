package org.devops

def deploy(Map params){
    sh 'docker run -d --name retofase2 -p 3000:80 retof2' 
}