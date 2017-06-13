node {
    def branchName = env.BRANCH_NAME	
	
	stage('checkout') {
		checkout([$class: 'GitSCM', 
				 branches: [[name: "*/$branchName"]], 
				 doGenerateSubmoduleConfigurations: false, 
				 extensions: [], 
				 submoduleCfg: [], 
				 userRemoteConfigs: [[credentialsId: 'gitlab', url: 'http://gitlab/root/webinar-bat-desk.git']]
				 ])
	}
   		
	stage('build-and-test') {
		
		withMaven() {
			sh 'mvn clean install -Pall-tests'
		}
		
	}

	stage('sonar reports') {

		withMaven() {
			sh 'mvn clean verify -Psonar-coverage sonar:sonar'
		}

	}
	
	if(branchName != 'master') {
	
		stage('deploy-integration-environment') {
		
		
		}
		
		stage('integration-test') {
			
			withMaven() {
				//sh 'mvn clean install -Pintegration-tests'
			}
			
		}			
		
		stage('release-to-master') {
		
		}
	}else {	
		stage('deploy-nexus') {
			
		}
		
		stage('deploy-production') {
		
		}
	}    	
}