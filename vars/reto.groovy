def call(Map params){

    pipeline {
        agent any

        stages {
            stage('Hello') {
                steps {
                    script {
                        def first = new org.devops.build()
                        first.application(message:params.message, 
                                        application:params.application)
                    }
                }
                
            }
        
            stage('Scanner') {
                steps {
                    script {
                        def second = new org.devops.sonarqube()
                        second.scan(scannerHome:params.scannerHome, 
                                    key:params.projectkey, 
                                    name:params.projectname, 
                                    version:params.version,
                                    sources:params.sources,
                                    url:params.hosturl, 
                                    login:params.login,
                                    password:params.password)
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

            stage('Build') {
                steps {
                    sh 'docker build -t reto2 .'
                }
            }
    
            stage('Push') {
                steps {
                    withDockerRegistry([url: 'https://registry-1.docker.io/', credentialsId: 'retofase2']) {
                        sh 'docker push laurabecerra/reto2:latest'
                    }               
                }
            }        
        }
    }   
}
