package org.devops

def scanner(PROJECT){
    def scannerHome = tool 'sonarqube' 
    withSonarQubeEnv('sonarqube'){ 
    sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=${PROJECT} \
        -Dsonar.projectName=${PROJECT} \
        -Dsonar.sources=${env.WORKSPACE} "
    }  
}

