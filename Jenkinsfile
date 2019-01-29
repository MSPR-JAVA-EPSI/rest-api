pipeline {
  agent any
  stages {
    stage('Preparation') {
      steps {
        sh '''
      git clone https://github.com/MSPR-JAVA-EPSI/rest-api.git

'''
        sh 'mvnHome = tool \'M3\''
        sh 'def slackResponse = slackSend(channel: "build", message: "Preparing to build rest-api")'
      }
    }
    stage('Build') {
      steps {
        sh '''sh "\'${mvnHome}/bin/mvn\' -Dmaven.test.failure.ignore clean package install"
     '''
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