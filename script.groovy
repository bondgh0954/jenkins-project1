def buildJar(){
    echo 'building jar file from the application'
    sh 'mvn package'
}

def buildImage(){
    echo 'building docker image from the application'
    withCredentials([usernamePassword(credentialsId:'dockerhub-credentials',usernameVariable:'USERNAME',passwordVariable:'PASSWORD')]){
        sh 'docker build -t nanaot/java-app:1.9 .'
        sh "echo $PASSWORD |docker login -u $USERNAME --password-stdin"
        sh 'docker push nanaot/java-app:1.9'
    }
}

return this