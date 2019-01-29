pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package install'
        sh 'slackSend(channel: "build", message: "New build done (${BUILD_URL})")'
      }
    }
    stage('Results') {
      steps {
        sh '''
      junit \'**/target/surefire-reports/TEST-*.xml\''''
        sh '''
      archive \'target/*.jar\''''
        sh '''
      def slackResponse = slackSend(channel: "build", message: "Build passed")'''
      }
    }
  }
  environment {
    MSPR_DB_BASE = 'epsi-mspr-java'
    MSPR_DB_HOST = ' shyndard.eu'
    MSPR_DB_PASSWORD = 'WcH7wkUkXuTuyWtE'
    MSPR_DB_PORT = '3306'
    MSPR_DB_USERNAME = 'epsi-mspr-java'
  }
}