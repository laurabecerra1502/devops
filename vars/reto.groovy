def call(Map params){

    pipeline {
        agent any

        stages {
            stage('Hello') {
                steps {
                    script {
                        def one = new org.devops.build()
                        one.application(message: params.message, application: params.application)
                    }
                }
                
            }
        
            stage('Scanner') {
                steps {
                    script {
                        def second = new org.devops.sonarqube()
                        second.scan(home: params.scannerhome, 
                                    key: params.projectkey, 
                                    name: params.projectname, 
                                    version: params.version,
                                    sources: params.sources,
                                    url: params.hosturl, 
                                    login: params.login,
                                    password: params.password)
                    }  
                }        
            }

            stage('Results') {
                steps {
                    script {
                        def third = new org.devops.build()
                        third.results()
                    }  
                }        
            }
        }
    }
}