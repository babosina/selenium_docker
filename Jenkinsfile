pipeline {
    agent any
    stages {
        stage("Build Jar") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage("Build Image") {
            steps {
                sh "docker build -t=babosina/selenium-java:latest ."
            }
        }

        stage("Push Image") {
            environment {
                DOCKER_HUB = credentials('docker_creds')
            }
            steps {
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh "docker push babosina/selenium-java:latest"
                sh "docker tag babosina/selenium-java:latest babosina/selenium-java:${env.BUILD_NUMBER}"
                sh "docker push babosina/selenium-java:${env.BUILD_NUMBER}"
            }
        }

    }
    post {
        always {
            sh "docker logout"
        }
    }
}