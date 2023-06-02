package org.devops

def call(git_name){
    def scannerHome = tool 'sonarqube' 
    withSonarQubeEnv('sonarqube'){ 
    sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=${git_name} \
        -Dsonar.projectName=${PROJECT} \
        -Dsonar.sources=${env.WORKSPACE} "
    }  
}

