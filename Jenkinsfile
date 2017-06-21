node {

    def _cycle

       try {
          _cycle = "$cycle"
        }catch(Exception e) {
          _cycle = ''
        }

    def branch_Name = (_cycle == 'EndFeature')? branchName : env.BRANCH_NAME

    def featureName
    def urlService
    def commitId
    def environment_port
    def finalVersion

	stage('Clean Workspace') {
		cleanWs()
	}

	stage('Checkout the Code') {
	    echo "Checkout the branch: $branch_Name"

		checkout([$class: 'GitSCM',
				 branches: [[name: "*/$branch_Name"]],
				 doGenerateSubmoduleConfigurations: false,
				 extensions: [],
				 submoduleCfg: [],
				 userRemoteConfigs: [[credentialsId: 'gitlab',
				                      refspec: '+refs/heads/*:refs/remotes/origin/*',
				                      url: 'http://gitlab/root/webinar-bat-desk.git']]
				 ])
	}

	stage('Run Unit Tests') {

		withMaven() {
			sh 'mvn clean test'
		}
	}

	stage('Run Sonar reports') {

      withMaven() {
          sh 'mvn clean verify -Psonar-coverage sonar:sonar'
      }
  }

	stage('Run Integration Tests') {

      withMaven() {
          sh 'mvn clean verify -Pintegration-tests'
      }
  }

  stage('Create Docker Image and Push to Registry') {

      withMaven() {

          featureName = getFeatureName(branch_Name)
          commitId = readCommitId()

          sh "sudo docker login -u admin -p admin localhost:5000"
          sh "sudo docker build -t atsistemas/bat-desk/${featureName}:${commitId} ."
          sh "sudo docker tag atsistemas/bat-desk/${featureName}:${commitId} localhost:5000/atsistemas/bat-desk/${featureName}:${commitId}"
          sh "sudo docker push localhost:5000/atsistemas/bat-desk/${featureName}:${commitId}"
          sh "sudo docker rmi localhost:5000/atsistemas/bat-desk/${featureName}:${commitId}"
          sh "sudo docker rmi atsistemas/bat-desk/${featureName}:${commitId}"
      }

  }

	if(_cycle == 'EndFeature') {

            stage("Merge, set version and push to: $to") {

                withMaven() {
                    def output = sh(returnStdout: true, script: 'mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version')
                    finalVersion = getFinalVersion(output)
                }

                withCredentials([usernamePassword(credentialsId: 'gitlab', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                    sh 'git config --global user.email "atSistemas@atsistemas.com"'
                    sh 'git config --global user.name "atSistemas"'
                    sh "git fetch http://${GIT_USERNAME}:${GIT_PASSWORD}@gitlab/root/webinar-bat-desk.git"
                    sh "git checkout ${branch_Name}"
                    sh "git checkout ${to}"
                    sh "git merge --no-ff --strategy-option theirs ${branch_Name}"
                    sh "mvn versions:set -DnewVersion=${finalVersion} && mvn versions:commit"
                    sh 'git add -A && git commit -m "Set final version ${finalVersion}" '
                    sh "git tag bat-desk-${finalVersion}"
                    sh "git push http://${GIT_USERNAME}:${GIT_PASSWORD}@gitlab/root/webinar-bat-desk.git ${to}"
                    sh "git push http://${GIT_USERNAME}:${GIT_PASSWORD}@gitlab/root/webinar-bat-desk.git --tags ${to}"
                    //housekeeping
                    sh "git branch -d ${branch_Name}"
                    //sh "git push http://${GIT_USERNAME}:${GIT_PASSWORD}@gitlab/root/webinar-bat-desk.git origin --delete ${branch_Name}"

                }
            }

    }

    stage('Deploy to Nexus') {
            withMaven() {
                sh 'mvn clean deploy -Dmaven.test.skip=true'
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

String getFeatureName(String branch) {

    return branch.toLowerCase().substring(branch.lastIndexOf("/") + 1)
}

String readCommitId() {
    return sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
}