def call(Map params){

    pipeline {
        agent any

        tools {
            nodejs 'NodeJS'
        }
        
        environment {
            git_name = "${env.GIT_URL}".replaceAll('.+/(.+)\\.git', '$1')toLowerCase()
        }

        stages {
            stage('Clone App') {
                steps {
                    script {
                        def cloneapp = new org.devops.build()
                        cloneapp.clone(scmUrl:params.scmUrl)
                    }
                }
                
            }

           /*stage('Construccion App') {
                steps {
                    script {
                        def buildapp = new org.devops.build()
                        buildapp.install()
                    }
                }
                
            }
        
            stage('Escaneo SonarQube') {
                steps {
                    script {
                        def scanner = new org.devops.analisis()
                        scanner("${PROJECT}")
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
            }

            stage('Deploy Imagen') {
                steps {
                    script{
                        sh "docker build -t aplicacion_reactapp ."
                        sh "docker run --name app -p 8040:3000 -d aplicacion_reactapp"
                    }    
                }                                        
            }
       
            stage('Escaneo de la aplicación') {
                steps {
                    script{
                        /*sh 'docker run -dt --name owasp -v owasp_data:/zap/reports --user root -t owasp/zap2docker-stable /bin/bash'
                        sh 'docker exec owasp mkdir /zap/wrk'
                        sh 'docker exec owasp zap-full-scan.py -t http://www.example.com -r report.html -I'
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