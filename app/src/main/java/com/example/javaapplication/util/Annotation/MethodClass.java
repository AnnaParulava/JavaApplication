package com.example.javaapplication.util.Annotation;

public class MethodClass {

    @Repeat(repeatValue = 3)
    protected void protectedMethod(int a) {
        System.out.println("Protected method called with param: int " + a);
    }

    @Repeat(repeatValue = 2)
    private void privateMethod(String message) {
        System.out.println("Private method called with param: String " + message);
    }

    @Repeat(repeatValue = 5)
    public void publicMethod(boolean check) {
        System.out.println("Public method called with param: boolean " + check);
    }

    @Repeat(repeatValue = 1)
    protected void anotherProtectedMethod(int a, int b) {
        final int sum = a + b;
        System.out.println("Another protected method called. int " + a + " + " + b + " = " + sum);
    }

    @Repeat(repeatValue = 6)
    private void anotherPrivateMethod(float floatingNumber) {
        System.out.println("Another private method called with float" + floatingNumber);
    }

    public void anotherPublicMethod(Integer param){
        System.out.println("Another public method called with Integer " + param.hashCode());
    }
}
