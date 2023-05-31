package org.devops

def application(Map params){
    sh 'node -v'
    sh 'node install'
}

