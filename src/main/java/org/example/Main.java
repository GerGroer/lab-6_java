package org.example;

public class Main
{

    public static void main(String[] args) throws Exception {

        System.out.println("=== Reflection ===");

        MyClass object = new MyClass();

        Invoker.invokeAnnotatedMethods(object);

        System.out.println("\n=== File System ===");

        FileManager.manageFiles();
    }
}