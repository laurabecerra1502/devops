package org.devops

def application(Map params){
    echo "Hello World ${params.message}"
    echo "La aplicacion que se va a analizar es ${params.application}"
}

def results(Map params){
    echo "Los resultados del analisis fueron exitosos"
}

