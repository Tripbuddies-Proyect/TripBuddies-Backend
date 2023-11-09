pipeline {
    agent any
    tools {
        maven 'Maven_3_9_5'
        jdk 'JDK_17'
    }
    stages {
        stage('Compile Stage') {
            steps {
                withMaven(maven: 'Maven_3_9_5') {
                    bat 'mvn clean compile'
                }
            }
        }
        stage('Testing Stage') {
            steps {
                withMaven(maven: 'Maven_3_9_5') {
                    // Aquí usamos el comando para ejecutar todos los tests excepto AdquisicionsControllerTest
                    bat 'mvn test -Dtest=!AdquisicionsControllerTest'
                }
            }
        }
        stage('Package Stage') {
            steps {
                withMaven(maven: 'Maven_3_9_5') {
                    // Asegúrate de también excluir el test al empaquetar si es necesario
                    bat 'mvn package -Dmaven.test.skip=true'
                }
            }
        }
    }
}
