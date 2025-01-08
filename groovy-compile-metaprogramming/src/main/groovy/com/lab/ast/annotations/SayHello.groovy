package com.lab.ast.annotations

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.METHOD])
@GroovyASTTransformationClass(["com.lab.ast.transformations.SayHelloASTTransformation"])
@interface SayHello {
    String name() default "Ignace"
}