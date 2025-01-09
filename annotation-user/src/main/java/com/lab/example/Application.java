package com.lab.example;

public class Application {

    public static void main(String[] args) {

        Person p = new PersonBuilder().name("John").age(20).build();

        System.out.println(p);
    }
}
