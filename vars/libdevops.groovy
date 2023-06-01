def call(Map params){

    pipeline {
        agent any

        tools {
            nodejs 'NodeJS'
        }

        /*stages {
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
                        PROJECT = gitname
                        def second = new org.devops.analisis()
                        second.scanner("${PROJECT}")
                    }  
                }        
            }*/

            stage('Build Imagen') {
                steps {
                    script {
                        def third = new org.devops.image()
                        third.buildimage("${PROJECT}")
                    }
                }
            }
    
            stage('Push Imagen') {
                steps {
                    script {
                        def fifth = new org.devops.push()
                        fifth.pushimage("${PROJECT}")
                    }
                    
                } 
            }

            stage('Deploy Imagen') {
                steps {
                    script {
                        def sixth = new org.devops.deploy()
                        sixth.deployimage("${PROJECT}")
                    }                  
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
            }*/

            
        }

        post {
            success {
                echo "Build Success - ${env.JOB_BASE_NAME} - ${env.BUILD_ID} on ${env.BUILD_URL}"
            }
            failure {
                echo "Build Failed - ${env.JOB_BASE_NAME} - ${env.BUILD_ID} on ${env.BUILD_URL}"
            }
            aborted {
                echo "Build Aborted - ${env.JOB_BASE_NAME} - ${env.BUILD_ID} on ${env.BUILD_URL}"
            }
        }
        
    }       
}
   
