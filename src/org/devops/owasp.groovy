package org.devops

def call(PROJECT){
        sh "docker network create ${env.JOB_NAME}"
        sh "docker network connect ${env.JOB_NAME} ${PROJECT}"
        sh "docker run -d --name owasp --user root --network=${env.JOB_NAME} -v owasp_data:/zap/reports -t owasp/zap2docker-stable /bin/bash"
        sh 'docker exec owasp mkdir /zap/wrk'
        sh "docker exec owasp zap-full-scan.py -t http://${PROJECT}:80${env.BUILD_ID}/ -r reportapp.html -I"
        sh 'docker cp owasp:/zap/wrk/reportapp.html reportapp.html'
        sh "docker cp reportapp.html jenkins:/var/jenkins_home/workspace/${env.JOB_NAME}/" 
}