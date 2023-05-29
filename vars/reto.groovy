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
        
            /*stage('Scanner') {
                steps {
                    script {
                        def second = new org.devops.analisis()
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
            }*/

            stage('Build Image') {
                steps {
                    echo "${env.GIT_URL}"
                    sh "docker build -t mariohtml ."

                }
            }
    
            /*stage('Push Image') {
                steps {
                    script {
                        def fifth = new org.devops.publicacion()
                        fifth.push()
                    }
                    
                } 
            }*/

            stage('Deploy Image') {
                steps {
                    sh 'docker run --name contenedor -p 5002:80 -d mariohtml'
                    
                }
            }

            stage('Escaneo de la aplicación') {
                steps {
                    script {
                        echo "escaneo de la aplicacion"
                            sh 'docker exec owasp zap-full-scan.py -t http://localhost:3000/ -r report.html -I'
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

            stage('Owasp-ZAP') {
                steps {
                    script {
                        sh "curl -Ls https://github.com/zaproxy/zaproxy/releases/download/v2.11.1/ZAP_2.11.1_Linux.tar.gz -o ${env.WORKSPACE}/owasp.zip"
                        sh "tar -xf ${env.WORKSPACE}/owasp.zip"
                        sh "java -jar ${env.WORKSPACE}/ZAP_2.11.1/zap-2.11.1.jar -cmd -quickurl http://localhost:8084/#slide=1 -quickprogress -quickout ${env.WORKSPACE}/report.html"
                    }
                }
            }
        }
        
    }       
}
   
