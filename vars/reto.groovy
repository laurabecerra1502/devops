def call(Map params){

    pipeline {
        agent any

        stages {
            stage('Hello') {
                steps {
                    script {
                        def z = new com.devops.build()
                        z.build(params.msn)
                    }
                }
                
            }
        }
        
            /*stage('Checkout') {
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
        }*/
    }
}