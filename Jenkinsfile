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

            def featureName = getFeatureName(branchName)
            def commitId = readCommitId()

            sh "sudo docker login -u admin -p admin localhost:5000"
            sh "sudo docker build -t atsistemas/bat-desk/${featureName}:${commitId} ."
            sh "sudo docker tag atsistemas/bat-desk/${featureName}:${commitId} localhost:5000/atsistemas/bat-desk/${featureName}:${commitId}"
            sh "sudo docker push localhost:5000/atsistemas/bat-desk/${featureName}:${commitId}"
            sh "sudo docker rmi localhost:5000/atsistemas/bat-desk/${featureName}:${commitId}"
            sh "sudo docker rmi atsistemas/bat-desk/${featureName}:${commitId}"
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
        //we need to modify this to point to openshift deployed url and port
        //server.port
        //application.endpoint.url
            sh 'mvn clean verify -Pe2e-tests -Dserver.port=3000'
        }
    }

	if(isMergeRequest(branchName)) {

            stage('setting up pom release version') {

                //read the version and sets to release
                withMaven() {
                    def output = sh(returnStdout: true, script: 'mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version')

                    def finalVersion = getFinalVersion(output)
                    echo finalVersion

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

            if(isMaster(branchName)) {

                stage('deploy-release-nexus') {
                    withMaven() {
                        sh 'mvn clean deploy -Dmaven.test.skip=true'
                    }
                }
            }

    } else {
        stage('deploy-nexus') {
        		withMaven() {
        			sh 'mvn clean deploy -Dmaven.test.skip=true'
        		}
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
    String version

    output.eachLine { line ->
        def m = line =~ /(\d+\.\d+\.\d+)-SNAPSHOT/

        if(m.find()) {
            version = "${m[0][1]}"
        }
    }

    return version
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
String getFeatureName(String branch) {

    return branch.toLowerCase().substring(branch.lastIndexOf("/") + 1)
}

String readCommitId() {
    return sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
}