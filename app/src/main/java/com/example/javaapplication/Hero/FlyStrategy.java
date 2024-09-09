package com.example.javaapplication.Hero;

class FlyStrategy implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("Герой летит.");
    }
}
