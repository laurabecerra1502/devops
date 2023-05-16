def jenkins(Map params){

    pipeline {
        agent any

        stages {
            stage('Hello') {
                steps {
                    script {
                        build.good()
                    }
                }
                
            }
        
            stage('Checkout') {
                steps {
                    script {
                        sonarqube.checkout()
                    }
                }
            }

            stage('scan') {
                steps {
                    script {
                        sonarqube.scan()
                    }  
                }        
            }
        }
    }
}