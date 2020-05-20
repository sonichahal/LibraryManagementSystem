def workspace;

node {

    def tomcatWeb = 'C:\\Users\\Ajay Rathore\\Desktop\\Soni Chahal\\apache-tomcat-8.5.55\\webapps'
    def tomcatBin = 'C:\\Users\\Ajay Rathore\\Desktop\\Soni Chahal\\apache-tomcat-8.5.55\\bin'

    stage('checkout') {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '4c238428-14e3-4599-b80e-204c473e5c11', url: 'https://github.com/sonichahal/LibraryManagementSystem.git']]])
        workspace = pwd()
    }
    stage('Build') {
        dir('eureka'){
            bat 'mvnw clean install -DskipTests=true'
        }
        dir('student') {
            bat 'mvnw clean install -DskipTests=true'
        }
        dir('library') {
            bat 'mvnw clean install -DskipTests=true'
        }
        dir('order') {
            bat 'mvnw clean install -DskipTests=true'
        }
    }
   stage ('Deploy') {
               dir('eureka') {
                   archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
                   dir('target'){
                   workspace = pwd()
                   }
               }

               bat 'copy eureka-0.0.1-SNAPSHOT.war \"${tomcatWeb}\\eureka-0.0.1-SNAPSHOT.war\"'
               bat "curl -v -u tomcat:tomcat -T eureka-0.0.1-SNAPSHOT.war 'http://localhost:9090//manager/text/deploy?path=/eureka-0.0.1-SNAPSHOT.war' "
           }

           stage
}
