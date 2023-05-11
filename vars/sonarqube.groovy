def checkout(Map params){
    git branch: "${params.branch}", url: "${params.gitrepo}" 
}

def scan(Map params){
    environment {
        scannerHome = tool "${params.scannerHome}"
    }
  
    withSonarQubeEnv("${params.scannerHome}") {
        sh "${params.scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=${params.projectKey} \
            -Dsonar.projectName=${params.projectName} \
            -Dsonar.projectVersion=${params.projectVersion} \
            -Dsonar.sources=${params.sources} \
            -Dsonar.host.url=${params.sonarHostUrl} \
            -Dsonar.login=${params.sonarLogin} \
            -Dsonar.password=${params.sonarPassword} "
    }
}
