package com.example.javaapplication.util.Hero;

class WalkStrategy implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("Герой идет пешком.");
    }
}
