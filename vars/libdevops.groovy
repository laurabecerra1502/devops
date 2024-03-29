def call(Map params){

    pipeline {
        agent any

        tools {
            nodejs 'NodeJS'
        }
        
        environment {
            PROJECT = "${env.GIT_URL}".replaceAll('.+/(.+)\\.git', '$1')toLowerCase()
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

           stage('Construccion App') {
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
                        def scannerapp = new org.devops.analisis()
                        scannerapp("${PROJECT}")
                    }  
                }        
            }

            stage('Build Imagen') {
                steps {
                    script {
                        def buildimage = new org.devops.image()
                        buildimage("${PROJECT}")
                    }
                }
            }
    
            /*stage('Push Imagen') {
                steps {
                    script {
                        def pushimage = new org.devops.push()
                        pushimage("${PROJECT}")
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

            stage('Escaneo de la aplicación') {
                steps {
                    script{
                         def owaspscan = new org.devops.owasp()
                        owaspscan("${PROJECT}")
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
