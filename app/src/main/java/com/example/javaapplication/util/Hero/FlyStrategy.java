package com.example.javaapplication.util.Hero;

class FlyStrategy implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("Герой летит.");
    }
}
