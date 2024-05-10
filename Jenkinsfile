pipeline {
    agent any

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'master',
                    credentialsId: 'git-cred',
                    url: 'https://github.com/Chedysk/PFE-Backend.git'
            }
        }

        stage('Build Spring Boot App') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test Spring Boot') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Sonarqube Analysis - Backend') {
            steps {
                withSonarQubeEnv('sonarserver') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Build Docker Image - Backend') {
            steps {
                sh 'docker build -t chedysk/back .'
            }
        }

        stage('Push Docker Image - Backend') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'chedysk-dockerhub') {
                        sh 'docker push chedysk/back'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                ansiblePlaybook installation: 'Ansible',
                    inventory: 'hosts',
                    playbook: 'deploy.yaml',
                    vaultCredentialsId: ''
            }
        }

        stage('Notify Slack') {
            steps {
                slackSend channel: '#pfe',
                    color: 'good',
                    message: 'Test and deployment of the backend completed successfully'
            }
        }
    }
}