package com.lab.meta.runtime.db

import groovy.json.JsonSlurper

@Singleton
class DataSource {
    def jsonData

    def fetchData(){
        if(!jsonData){
            def url = Thread.currentThread().getContextClassLoader().getResource("data.json")
            def jsonFile = new File(url.toURI())

            jsonData = new JsonSlurper().parse(jsonFile)
        }

        return jsonData
    }
}
