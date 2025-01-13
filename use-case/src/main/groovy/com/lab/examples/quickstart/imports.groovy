package com.lab.examples.quickstart

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

// Configuration des imports
def importCustomizer = new ImportCustomizer()
//importCustomizer.addImport('FeatureDsl', 'com.lab.quickstart.FeatureDsl')

Binding bindings = new Binding([
        versionName: new String("0.0.2")
])

// Configuration du compilateur
def configuration = new CompilerConfiguration()
configuration.addCompilationCustomizers(importCustomizer)

configuration.scriptBaseClass = 'com.lab.quickstart.FeatureDsl'

// Création du GroovyShell avec la configuration
def shell = new GroovyShell(this.class.classLoader, bindings, configuration)

shell.evaluate(new File('settings.groovy'))
