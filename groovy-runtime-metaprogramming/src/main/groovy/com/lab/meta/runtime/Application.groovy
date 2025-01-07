/** From https://groovy-lang.org/metaprogramming.html#_runtime_metaprogramming
 With runtime metaprogramming we can postpone to runtime the
 decision to intercept, inject and even synthesize methods
 of classes and interfaces.
 **/
package com.lab.meta.runtime


import com.lab.meta.runtime.db.Repository
import com.lab.meta.runtime.models.*

Person james = new Person()
println james.majority()
println james.genom()

/*
    james.setProperty("age", "101001001111001")
    println "Age : ${james.getAge()}"
    println "getProperty ${james.getProperty("adn")}"
*/

/*
    def b = new Book(title:"The Stand")
    def b2 = new Book(title: "The blood")
    println  b.titleInUpperCase()
    println  b2.titleInUpperCase()
*/


def personRepository = new Repository<>(Person.class)
def r1 = personRepository.findByFirstName("John")
println r1

def r2 = personRepository.findByLastName("Pan")
println r2

def bookRepository = new Repository<>(Book.class)
def b1 = bookRepository.findByTitle("Design Patterns")
print b1


