def call(Map params){

    pipeline {
        agent any

        tools {
            nodejs 'NodeJS'
        }
        
        stages {
            /*stage('Construccion Aplicación') {
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
            }

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
                    script{
                        DOCKER_EXIST = sh(returnStdout: true, script: 'echo "$(docker ps -q --filter name=${PROJECT})"').trim()
                        
                        if (DOCKER_EXIST != '') {  
                            sh "docker start ${PROJECT}"
                        } else {
                            def deployimage = new org.devops.deploy()
                            deployimage("${PROJECT}")
                        }
                    }    
                }                                        
            }*/

            stage('Owasp-ZAP') {
            steps {
                script {
                        sh "curl -Ls https://github.com/zaproxy/zaproxy/releases/download/v2.11.1/ZAP_2.11.1_Linux.tar.gz -o ${env.WORKSPACE}/owasp.zip"
                        sh "tar -xf ${env.WORKSPACE}/owasp.zip"
                        sh "java -jar ${env.WORKSPACE}/ZAP_2.11.1/zap-2.11.1.jar -cmd -quickurl http://aplicacion_reactapp:8045 -quickprogress -quickout ${env.WORKSPACE}/report.html"
                }
            }
        
            /*stage('Escaneo de la aplicación') {
                steps {
                    script{
                        /*sh 'docker run -dt --name owasp -v owasp_data:/zap/reports --user root -t owasp/zap2docker-stable /bin/bash'
                        sh 'docker exec owasp mkdir /zap/wrk'
                        sh 'docker exec owasp zap-full-scan.py -t http://aplicacion_reactapp:8045 -r report.html -I'
          
                        sh 'docker cp owasp:/zap/wrk/report.html report.html'
                        sh 'docker cp report.html jenkins:/var/jenkins_home/workspace/devops_reto/'  
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
                sh "docker stop ${PROJECT}"
                sh "docker rm ${PROJECT}"
            }
            aborted {
                echo "Build Aborted - ${env.JOB_BASE_NAME} - ${env.BUILD_ID} on ${env.BUILD_URL}"
            }
        }        
    }       
}