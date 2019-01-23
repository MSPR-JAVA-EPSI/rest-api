node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/MSPR-JAVA-EPSI/rest-api.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
      def slackResponse = slackSend(channel: "ci", message: "Started build")
      slackSend(channel: slackResponse.threadId, message: "Build still in progress")
      slackSend(
        channel: slackResponse.threadId,
        replyBroadcast: true,
        message: "Build failed. Broadcast to channel for better visibility."
      )
   }
}