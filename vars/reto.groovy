def call(Map params){

    pipeline {
        agent any

        stages {
            stage('Hello') {
                steps {
                    script {
                        def z = new com.devops.build()
                        z.hello(params.mensaje)
                        z.hello(params.variables)
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