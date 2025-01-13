package com.lab.quickstart

class FeatureProperties {
//    String state
//    String attrs
    String version

    FeatureProperties version(String value){
        println "Version: $value"
        this.version = value
        return this
    }

//    FeatureProperties name(String n){
//        println "TacticType: $n"
//        this.n = n
//        return this
//    }
}