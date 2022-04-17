#!/usr/bin/env groovy

def call(){

    stage('Hi from external'){
        def s = checkout scm;
        print s.GIT_URL
    }

}
