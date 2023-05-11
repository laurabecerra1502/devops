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
        sh "${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=reto1 \
            -Dsonar.projectName=reto1 \
            -Dsonar.projectVersion=1.0 \
            -Dsonar.sources=/var/jenkins_home/workspace/retico \
            -Dsonar.host.url=http://sonarqube:9000 \
            -Dsonar.login=admin \
            -Dsonar.password=1234 "
    }
}
