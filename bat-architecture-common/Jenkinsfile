pipeline {
    agent any
	
    stages { 		
        stage('build-and-deploy') {
            steps {
                withMaven() {
					sh 'mvn clean deploy'
				}
            }
        }
    }
}