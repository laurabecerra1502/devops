def jenkins(Map params){

    pipeline {
        agent any

        stages {
            stage('Hello') {
                steps {
                    script {
                        def z = new org.foo.build()
                        z.build.good(params.msn)
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