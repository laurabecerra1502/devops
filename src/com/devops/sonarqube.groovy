/*def git(Map params){
    git branch: "${params.branch}", url: "${params.gitrepo}" 
}
*/
package com.devops

def scan(Map params){
    def scannerHome = tool "${params.scannerHome}" 
    sh "${scannerHome}/bin/sonar-scanner \
        -Dsonar.projectKey=${params.projectkey} \
        -Dsonar.projectName=${params.projectname} \
        -Dsonar.projectVersion=${params.projectversion} \
        -Dsonar.sources=${params.sources} \
        -Dsonar.host.url=${params.hosturl} \
        -Dsonar.login=${params.login} \
        -Dsonar.password=${params.password} "
}
