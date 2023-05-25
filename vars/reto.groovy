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
                    withDockerRegistry([ credentialsId: "retofase2", url: "https://index.docker.io/v1/" ]) {
                        sh 'docker tag reto2 laurabecerra/reto2:latest'
                        sh 'docker push laurabecerra/reto2:latest'
                    }
                } 
            } 

            stage('Run') {
                steps {
                        sh 'docker run -d --name retoff -p 8084:80 reto2'
                    }
            }

            stage('Escaneo de la aplicación') {
                steps {
                    script {
                        echo "escaneo de la aplicacion"
                            sh 'docker exec owasp zap-full-scan.py -t http://localhost:8084/#slide=1 -r report.html -I'
                    }
                }
            }

            stage('Copia en el wrk de jenkins') {
                steps {
                    script {
                        echo "escaneo"
                            sh 'docker cp owasp:/zap/wrk/report.html report.html'
                            sh 'docker cp report.html jenkins:/var/jenkins_home/workspace/Owasp/'
                    }
                }
            }
        }
    }       
}
   
