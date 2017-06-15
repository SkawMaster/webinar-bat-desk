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
      sh '''
      export PROJECT=workshopjbcn2017-integration
      export PORT=30001
      oc login https://openshift:8443 --insecure-skip-tls-verify=true --username=admin --password=system --config ./config
      oc project $PROJECT --config ./config || oc new-project $PROJECT --config ./config
      oc apply -f openshift-deployment.yml --config ./config
      oc process deployment-app --config ./config -p PORT=$PORT | oc apply --config ./config -f -
      '''

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
      sh '''
      export PROJECT=workshopjbcn2017-production
      export PORT=30000
      oc login https://openshift:8443 --insecure-skip-tls-verify=true --username=admin --password=system --config ./config
      oc project $PROJECT --config ./config || oc new-project $PROJECT --config ./config
      oc apply -f openshift-deployment.yml --config ./config
      oc process deployment-app --config ./config -p PORT=$PORT | oc apply --config ./config -f -
      '''
		}
	}
}
