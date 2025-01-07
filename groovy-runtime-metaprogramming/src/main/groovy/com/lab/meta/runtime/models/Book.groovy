package com.lab.meta.runtime.models

import groovy.transform.EqualsAndHashCode
import groovy.transform.TupleConstructor

@TupleConstructor
@EqualsAndHashCode(includes=['id'])
class Book {
    Integer id
    String title

    @Override
    String toString() {
        return "Book-$id: $title"
    }

    /*
    def methodMissing(String methodName, def args) {

        // Si la méthode existe dans la métaclasse alors invoquer la méthode sur la métaclasse
        if (Book.metaClass.respondsTo(this, methodName)) {
            return Book.metaClass.invokeMethod(this, methodName, args)
        }

        // Si la méthode n'existe pas, la définir
        def method = {-> title.toUpperCase() }
        Book.metaClass.titleInUpperCase << method

        // Retourner le résultat de la méthode
        return method.call()
    }
*/
}
