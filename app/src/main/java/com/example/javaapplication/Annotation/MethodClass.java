package com.example.javaapplication.Annotation;

public class MethodClass {

    @Repeat(repeatValue = 3)
    protected String protectedMethod(int a) {
        String output = "Protected method called with param: int " + a;
        System.out.println(output);
        return output;
    }

    @Repeat(repeatValue = 2)
    private String privateMethod(String message) {
        String output = "Private method called with param: String " + message;
        System.out.println(output);
        return output;
    }

    @Repeat(repeatValue = 5)
    public String publicMethod(boolean check) {
        String output = "Public method called with param: boolean " + check;
        System.out.println(output);
        return output;
    }

    @Repeat(repeatValue = 1)
    protected String anotherProtectedMethod(int a, int b) {
        final int sum = a + b;
        String output = "Another protected method called. int " + a + " + " + b + " = " + sum;
        System.out.println(output);
        return output;
    }

    @Repeat(repeatValue = 6)
    private String anotherPrivateMethod(float floatingNumber) {
        String output = "Another private method called with float " + floatingNumber;
        System.out.println(output);
        return output;
    }

    public String anotherPublicMethod(Integer param) {
        String output = "Another public method called with Integer " + param.hashCode();
        System.out.println(output);
        return output;
    }
}

