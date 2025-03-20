package lab.javapoet.demo;

import java.lang.String;
import java.lang.System;

public final class HelloWorld {
  private static volatile HelloWorld instance = null;

  private HelloWorld(String name) {
    System.out.println(String.format("Hello, %s !", name));
  }

  public static HelloWorld getInstance(String name) {
    if (instance == null) {
        synchronized (HelloWorld.class) {
            if (instance == null) {
                System.out.println("First time !!! Never again");
                instance = new HelloWorld(name);
            }
        }
    }
    return instance;
  }
}
