package com.example.javaapplication.hero;

public class HorseRideStrategy implements MoveStrategy {
    @Override
    public String move() {
        System.out.println("Герой едет на лошади.");
        return "Герой едет на лошади";
    }
}
