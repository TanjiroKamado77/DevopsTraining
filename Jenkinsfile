pipeline{
    agent any 
    
    tools{
        maven "maven1"
        jdk "jdk17"
    }
    
    environment{
        sonarHome = tool "sonarqube"
    }
    
    stages{
        // pull code from github
        stage("pull source code"){
            steps{
                git url:  'https://github.com/TanjiroKamado77/DevopsTraining.git',
                branch: 'main'
            }
        }
        stage("SonarQube Analyze"){
            steps{
                withSonarQubeEnv("sonarqube"){
                        bat "${sonarHome}/bin/sonar-scanner"
                    }
            }
        }
        stage("Sonar QualityGate"){
                steps {
                    
                    script {
                        def qualityGate = waitForQualityGate();
                        echo "${qualityGate.status}"
                        
                        if(qualityGate.status != "OK"){
                           timeout(time: 2, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true }
            }
                        }
                    }
            
        }
        stage("Build Jar"){
            steps{
                bat 'mvn clean package'
            }
        }
        stage("build docker image"){
            steps{
                bat "docker build -t devopsapp ."
            }
        }
        stage("run docker image"){
            steps{
                bat "docker rm -f devopsapp"
                bat "docker run -itd --name devopsapp -p 9080:9080 devopsapp"
            }
        }
    }
}