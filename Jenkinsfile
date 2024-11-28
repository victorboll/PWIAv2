pipeline {
    agent any
    stages {
        stage('Verificar Repositorio') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], useRemoteConfigs:[[url: 'https://github.com/victorboll/PWIAv2.git']]])
            }
        }
        

        stage('Instalar Dependencias e Build') {
            steps {
                script {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Construir Imagem Docker') {
            steps {
                script {
                    def appName = 'victor.av2'
                    def imageTag = "${appName}:${env.BUILD_ID}"  // Usando o BUILD_ID do Jenkins como tag da imagem Docker
                    bat "docker build -t ${imageTag} -f Dockerfile ."
                }
            }
        }
        stage('Construir Imagem Docker') {
            steps {
                script {
                    def appName = 'victor.av2'
                    def imageTag = "${appName}:${env.BUILD_ID}"  // Usando o BUILD_ID do Jenkins como tag da imagem Docker
                    sh "docker build -t ${imageTag} -f Dockerfile ."
                }
            }
        }
        

        stage('Fazer Deploy') {
            steps {
                script {
                    def appName = 'victor.av2'
                    def imageTag = "${appName}:${env.BUILD_ID}"

                    bat "docker stop ${appName} || exit 0"  
                    bat "docker rm ${appName}  || exit 0"  

                    bat "docker run -d --name ${appName} -p 8090:8090 ${imageTag}"
                }
            }
        }
    }
}
                 
