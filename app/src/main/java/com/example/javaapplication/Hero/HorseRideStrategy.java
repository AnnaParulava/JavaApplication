package com.example.javaapplication.Hero;

class HorseRideStrategy implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("Герой едет на лошади.");
    }
}
