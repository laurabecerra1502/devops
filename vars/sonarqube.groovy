def checkout(Map params){
    git branch: "${params.branch}", url: "${params.gitrepo}" 
}

def scan(Map params){
    environment {
        scannerHome = tool "${params.scannerhome}"
    }
}

def sonarqubescan(Map params) {
    withSonarQubeEnv("${params.scannertool}") {
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
