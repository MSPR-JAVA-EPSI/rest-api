def mvnHome
stage('Preparation') {
   git 'https://github.com/MSPR-JAVA-EPSI/rest-api.git'      
   mvnHome = tool 'M3'
}
stage('Build') {
   if (isUnix()) {
      sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package install"
   } else {
      bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package install/)
   }
}
stage('Results') {
   junit '**/target/surefire-reports/TEST-*.xml'
   archive 'target/*.jar'
   def slackResponse = slackSend(channel: "build", message: "New build done (${BUILD_URL})")
}