package com.devops

def hello(Map params){
    echo "Hello World ${params.mensaje}"
    echo "La aplicación que se va a analizar es ${params.variable}"
}

