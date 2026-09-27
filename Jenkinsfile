pipeline {
  agent any
  stages {
    stage('Etape 1 - initialisation') {
      steps {
        sh 'cd backend && mvn clean package'
      }
    }

    stage('Etape 2 - Vérification systeme') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Etape 3 - Test script') {
      steps {
        sh 'mvn package -DskipTests'
      }
    }

  }
}