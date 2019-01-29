pipeline {
  agent any
  stages {
    stage('Preparation') {
      steps {
        sh '''
      git \'https://github.com/MSPR-JAVA-EPSI/rest-api.git\'

'''
        sh 'mvnHome = tool \'M3\''
      }
    }
    stage('Build') {
      steps {
        sh '''sh "\'${mvnHome}/bin/mvn\' -Dmaven.test.failure.ignore clean package install"
     '''
      }
    }
    stage('Results') {
      steps {
        sh '''
      junit \'**/target/surefire-reports/TEST-*.xml\''''
        sh '''
      archive \'target/*.jar\''''
        sh '''
      def slackResponse = slackSend(channel: "build", message: "New build done (${BUILD_URL})")'''
      }
    }
  }
}