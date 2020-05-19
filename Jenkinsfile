def workspace;

node {
    stage('checkout') {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '4c238428-14e3-4599-b80e-204c473e5c11', url: 'https://github.com/sonichahal/LibraryManagementSystem.git']]])
        workspace = pwd()
    }
    stage('Build') {
        dir('eureka'){
            bat 'mvnw clean install -DskipTests=true'
        }
    }
    stage ('Deploy') {
            dir('eureka') {
                archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
            }
        }
}
