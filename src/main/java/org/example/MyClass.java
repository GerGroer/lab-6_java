package org.example;

public class MyClass {

    // Публичные методы
    public void publicMethod1() {
        System.out.println("Public method 1");
    }

    public void publicMethod2() {
        System.out.println("Public method 2");
    }

    // Защищённые методы
    @Repeat(times = 2)
    protected void protectedMethod1() {
        System.out.println("Protected method 1");
    }

    @Repeat(times = 3)
    protected void protectedMethod2(int number) {
        System.out.println("Protected method 2: " + number);
    }

    protected void protectedMethod3() {
        System.out.println("Protected method 3");
    }

    // Приватные методы
    @Repeat(times = 4)
    private void privateMethod1() {
        System.out.println("Private method 1");
    }

    @Repeat(times = 2)
    private void privateMethod2(String text) {
        System.out.println("Private method 2: " + text);
    }

    @Repeat(times = 3)
    private void privateMethod3(int number, String text) {
        System.out.println("Private method 3: " + number + ", " + text);
    }
}