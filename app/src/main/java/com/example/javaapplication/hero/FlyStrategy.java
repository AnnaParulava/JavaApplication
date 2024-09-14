package com.example.javaapplication.hero;

public class FlyStrategy implements MoveStrategy {
    @Override
    public String move() {
        System.out.println("Герой летит.");
        return "Герой летит";
    }
}
