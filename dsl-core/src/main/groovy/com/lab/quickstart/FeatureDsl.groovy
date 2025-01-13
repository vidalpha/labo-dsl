package com.lab.quickstart



abstract class FeatureDsl extends Script{

//    abstract void scriptBody()

    void serveur(String nom) {
        println "Serveur: $nom"
    }

    void baseDeDonnees(String nom) {
        println "Base de données: $nom"
    }

    static FeatureProperties configure(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=FeatureProperties.class) closure) {
        final FeatureProperties props = new FeatureProperties()
        closure.delegate = props
        return closure.call()
    }

    def run(){
        println("Bingo")
    }

}