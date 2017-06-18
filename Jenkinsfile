node {
    def branchName = env.BRANCH_NAME
	
	stage('Clean workspace') {
		cleanWs()
	}

	stage('checkout') {
	    echo "Checkout the branch: $branchName"

		checkout([$class: 'GitSCM',
				 branches: [[name: "*/$branchName"]], 
				 doGenerateSubmoduleConfigurations: false, 
				 extensions: [], 
				 submoduleCfg: [], 
				 userRemoteConfigs: [[credentialsId: 'gitlab',
				                      refspec: '+refs/heads/*:refs/remotes/origin/* +refs/merge-requests/*/head:refs/remotes/origin/merge-requests/*',
				                      url: 'http://gitlab/root/webinar-bat-desk.git']]
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
	
    stage('bake-docker-image-and-push-to-registry') {

        withMaven() {
            sh 'env'
            sh 'sudo docker login -u admin -p admin localhost:5000'
            sh 'sudo docker build -t atsistemas/bat-desk/"${BRANCH_NAME}":"${BUILD_ID}" .'
            sh 'sudo docker tag atsistemas/bat-desk/"${BRANCH_NAME}":"${BUILD_ID}" localhost:5000/atsistemas/bat-desk/"${BRANCH_NAME}":"${BUILD_ID}"'
            sh 'sudo docker push localhost:5000/atsistemas/bat-desk/"${BRANCH_NAME}":"${BUILD_ID}"'
            sh 'sudo docker rmi localhost:5000/atsistemas/bat-desk/"${BRANCH_NAME}":"${BUILD_ID}"'
            sh 'sudo docker rmi atsistemas/bat-desk/"${BRANCH_NAME}":"${BUILD_ID}"'
        }

    }

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
            sh 'mvn clean verify -Pe2e-tests '
        }
    }

	stage('deploy-nexus') {
		withMaven() {
			sh 'mvn clean deploy -Dmaven.test.skip=true'
		}
	}

	if(isMergeRequest(branchName)) {

            stage('pepe') {

                //read the version and sets.
                withMaven() {
                    def output = sh(returnStdout: true, script: 'mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version')

                    def finalVersion = getFinalVersion(output)

                    sh "mvn versions:set -DnewVersion=$finalVersion"
                }

                //commit and push.
                withCredentials([usernamePassword(credentialsId: 'gitlab', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                    sh 'git config --global user.email "atSistemas@atsistemas.com"'
                    sh 'git config --global user.name "atSistemas"'
                    sh 'git commit -am "Set final version"'
                    sh "git push http://${GIT_USERNAME}:${GIT_PASSWORD}@gitlab/root/webinar-bat-desk.git $branchName"
                }
            }
            stage('merge') {
                acceptmergerequest('webinar-bat-desk',getMergeRequestId(branchName))
            }
	    }


	if(isMaster(branchName)) {
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

@NonCPS
String getFinalVersion(String output) {
	output.eachLine {
		def m = it =~ /(\\d+\\.\\d+.\\d+)-SNAPSHOT/

		if(m.find()) {
			return "${m[0][1]}"
		}
	}
}

boolean isMaster(String branch) {
    return 'master'.equals(branch)
}

boolean isMergeRequest(String branch) {
    return (branch != null && branch.startsWith('merge-requests/'))
}

int getMergeRequestId(String branch) {
    return branch.substring(branch.lastIndexOf("/") + 1)
}

String readCommitId(String commitOutput) {
    return "dededede"
}