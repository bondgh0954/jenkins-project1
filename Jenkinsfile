pipeline{

  agent any

  tools{
    maven "maven-3"
  }
  stages{
    stage('build jar'){
      steps{
        script{
          echo 'building jar file from the application'
          sh 'mvn package'
        }
      }
    }
    stage('build image'){
      steps{
        script{
          echo 'building docker image from the application'
          withCredendials([usernamePassword(credentialsId:'dockerhub-credentials',usernameVariable:'USERNAME',passwordVariable:'PASSWORD')]){
            sh 'docker build -t nanaot/java-app:1.9 .'
            sh "echo $PASSWORD |docker login -u $USERNAME --password-stdin"
            sh 'docker push nanaot/java-app:1.9'
          }
        }
      }
    }
  }

}