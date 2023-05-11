def checkout(Map params){
    git branch: "${params.branch}", url: "${params.gitrepo}" 
}

def scan(Map params){
    environment {
        scannerHome = tool "${params.scannerHome}"
    }
}

def sonarqubescan(Map params) {
    withSonarQubeEnv("${scannerTool}") {
        sh "${$scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=${projectKey} \
            -Dsonar.projectName=${projectName} \
            -Dsonar.projectVersion=${projectVersion} \
            -Dsonar.sources=${sources} \
            -Dsonar.host.url=${sonarHostUrl} \
            -Dsonar.login=${sonarLogin} \
            -Dsonar.password=${sonarPassword} "
    }
}
