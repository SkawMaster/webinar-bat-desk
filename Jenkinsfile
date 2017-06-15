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
   		
	stage('unit-tests') {
		
		withMaven() {
			sh 'mvn clean test'
		}
		
	}

	stage('sonar reports') {

		withMaven() {
			sh 'mvn clean verify -Psonar-coverage sonar:sonar'
		}

	}

	stage('integration-tests') {

		withMaven() {
			sh 'mvn clean verify -Pintegration-tests'
		}

	}


	if(branchName != 'master') {
	
		stage('deploy-integration-environment') {
		
		
		}
		
		stage('e2e-test') {
			
			withMaven() {
				sh 'mvn clean verify -P2e2-tests'
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