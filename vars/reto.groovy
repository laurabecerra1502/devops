def call(Map params){

    pipeline {
        agent any

        stages {
            stage('Hello') {
                steps {
                    script {
                        def z = new com.devops.build()
                        z.hello(mensaje: params.mensaje, variable: params.variable)
                    }
                }
                
            }
        
            stage('scan') {
                steps {
                    script {
                        def x = new com.devops.sonarqube()
                        z.scan(scannerHome: params.home)
                    }  
                }        
            }
        }
    }
}