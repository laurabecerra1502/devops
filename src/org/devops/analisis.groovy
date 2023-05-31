package org.devops

def scanner(PROJECT){
    def scannerHome = tool 'sonarqube' 
    withSonarQubeEn('sonarqube'){ 
    sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=${PROJECT} \
        -Dsonar.projectName=${PROJECT} \
        -Dsonar.sources=${env.WORKSPACE} "
    }  
}

