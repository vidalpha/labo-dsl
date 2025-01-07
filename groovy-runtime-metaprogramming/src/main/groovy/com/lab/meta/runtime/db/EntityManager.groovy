package com.lab.meta.runtime.db

import com.fasterxml.jackson.databind.ObjectMapper

class EntityManager {
    static def createQuery(def T){
        return DataSource.instance.fetchData()."${T.simpleName.toLowerCase()}s"
            .collect { object ->
                // Mapper chaque élément JSON avec la class correspondante
                new ObjectMapper().convertValue(object, T)
            }
    }

}
