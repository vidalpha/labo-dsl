package com.lab.meta.ast

import com.lab.meta.ast.annotations.SayHello

static void main(String[] args) {
    println("Groovy custom AST transformations")
    printHelloWorld()
}

@SayHello(name="ignace")
def static printHelloWorld() {
    println "Hello, World!"
}
