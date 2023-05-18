def call(Map params){

    pipeline {
        agent any

        stages {
            stage('Hello') {
                steps {
                    script {
                        def first = new com.devops.build()
                        first.hello(mensaje: params.mensaje, variable: params.variable)
                    }
                }
                
            }
        
            stage('Scanner') {
                steps {
                    script {
                        def second = new com.devops.sonarqube()
                        second.scan(scannerHome: params.home)
                    }  
                }        
            }
        }
    }
}