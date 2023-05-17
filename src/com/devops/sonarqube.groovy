/*def git(Map params){
    git branch: "${params.branch}", url: "${params.gitrepo}" 
}
*/
package com.devops

def scan(Map params){
    def scannerHome = tool "${params.scannerHome}" 
    sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=retico \
        -Dsonar.projectName=retico \
        -Dsonar.projectVersion=1.0 \
        -Dsonar.sources=/var/jenkins_home/workspace/aplicacion \
        -Dsonar.host.url=http://sonarqube:9000 \
        -Dsonar.login=admin \
        -Dsonar.password=1234 "
}
