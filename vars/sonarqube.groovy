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
        sh "sonarqube/bin/sonar-scanner \
            -Dsonar.projectKey=labreto1 \
            -Dsonar.projectName=labreto1 \
            -Dsonar.projectVersion=1.0 \
            -Dsonar.sources=/var/jenkins_home/workspace/estasies \
            -Dsonar.host.url=http://sonarqube:9000 \
            -Dsonar.login=admin \
            -Dsonar.password=1234 "
    }
}
