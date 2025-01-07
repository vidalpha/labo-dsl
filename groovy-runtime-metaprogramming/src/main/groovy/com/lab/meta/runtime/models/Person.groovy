package com.lab.meta.runtime.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.EqualsAndHashCode

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(includes=['id'])
class Person {
    Integer id
    String lastName
    String firstName
    def age = 5 // en année


    def methodMissing(String name, def args) {
        return "called methodMissing '$name' with params $args"
    }

    String majority() {
        if (age >= 18)
            return "Majeur(e)"
        else
            return "Majeur(e) dans ${18-age} ans"
    }

    void setProperty(String pop, Object value) {
        this.@"$pop" = value
    }

    def getProperty(String pop) {
        return metaClass.getProperty(this, pop)
    }

    @Override
    String toString() {
        return "Person-$id: $lastName $firstName"
    }

}
