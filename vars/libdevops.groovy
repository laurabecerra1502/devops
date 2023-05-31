def call(Map params){

    pipeline {
        agent any

        tools {
            nodejs 'NodeJS'
        }

        stages {
            stage('Construccion Aplicación') {
                steps {
                    script {
                        def first = new org.devops.build()
                        first.install()
                    }
                }
                
            }
        
            stage('Escaneo SonarQube') {
                steps {
                    script {
                        def giturl = "https://github.com/laurabecerra1502/aplicacion_reactapp.git"
                        def gitname = giturl.replaceAll('.+/(.+)\\.git', '$1')toLowerCase()
                        env.PROJECT = gitname
                        def second = new org.devops.analisis()
                        second.scanner("${env.PROJECT}")
                    }  
                }        
            }

            /*stage('Results') {
                steps {
                    script {
                        def third = new org.devops.build()
                        third.results()
                    }  
                }        
            }

            stage('Build Image') {
                steps {
                    echo "${env.GIT_URL}"
                    sh "docker build -t mariohtml ."

                }
            }
    
            stage('Push Image') {
                steps {
                    script {
                        def fifth = new org.devops.publicacion()
                        fifth.push()
                    }
                    
                } 
            }

            stage('Deploy Image') {
                steps {
                    sh "docker build -t reactapp ."
                    sh 'docker run -p 8003:3000 -d reactapp'
                    
                }
            }

            /*stage('Escaneo de la aplicación') {
                steps {
                    script {
                        echo "escaneo de la aplicacion"
                            sh 'docker exec owasp zap-full-scan.py -t http://localhost:5002 -r report.html -I'
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
                        sh "java -jar ${env.WORKSPACE}/ZAP_2.11.1/zap-2.11.1.jar -cmd -quickurl http://localhost:5002 -quickprogress -quickout ${env.WORKSPACE}/report.html"
                    }
                }
            }*/


        }

        post {
        success {
            echo "Build Success"
            echo "Successfully built ${env.JOB_BASE_NAME} - ${env.BUILD_ID} on ${env.BUILD_URL}"
        }
        failure {
            echo "Build Failed - ${env.JOB_BASE_NAME} - ${env.BUILD_ID} on ${env.BUILD_URL}"
        }
        aborted {
            echo " ${env.JOB_BASE_NAME} Build - ${env.BUILD_ID} Aborted!"
        }
    }
        
    }       
}
   
