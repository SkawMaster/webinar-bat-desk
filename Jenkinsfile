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