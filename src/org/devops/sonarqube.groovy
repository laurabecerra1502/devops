package org.devops

def scan(Map params){
    def scannerHome = tool "${params.home}" 
    sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=${params.hey} \
        -Dsonar.projectName=${params.name} \
        -Dsonar.projectVersion=${params.version} \
        -Dsonar.sources=${params.sources} \
        -Dsonar.host.url=${params.url} \
        -Dsonar.login=${params.login} \
        -Dsonar.password=${params.password} "
}
