package com.example.javaapplication.Hero;

class WalkStrategy implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("Герой идет пешком.");
    }
}
