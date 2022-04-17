#!/usr/bin/env groovy

def call (){

    podTemplate(yaml: '''
        apiVersion: v1
        kind: Pod
        spec:
          containers:
          - name: node
            image: node:alpine
            command:
            - cat
            tty: true
          - name: kubeclient
            image: szecsikr/kubeclient
            command:
            - cat	
            tty: true
          - name: docker
            image: docker
            command:
            - cat
            tty: true
            volumeMounts:
              - name: dockersock
                mountPath: /var/run/docker.sock
          volumes:
          - name: dockersock
            hostPath:
              path: /var/run/docker.sock
'''){

        node(POD_LABEL){
            
             stage('GIT Pull'){
                
                   checkout scm;
                
            }

            stage('Build react project'){
                container('node'){
                    sh 'npm install'
                    sh 'CI=false npm run build'
                }
            }

            stage('Build docker image'){
                container('docker'){
                    image = docker.build("szecsikr/workout-frontend")
                }
            }


            stage('Publish image'){
                container('docker'){
                    withCredentials([usernameColonPassword(credentialsId: 'szecsikr-docker', variable: 'DOCKER')]) {
                        docker.withRegistry('', 'szecsikr-docker'){
                            image.push();
                        }
                    }
                }
            }

        }
    }

}
