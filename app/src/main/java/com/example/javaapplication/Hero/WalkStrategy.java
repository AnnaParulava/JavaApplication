package com.example.javaapplication.Hero;

public class WalkStrategy implements MoveStrategy {
    @Override
    public String move()
    {
        System.out.println("Герой идет пешком.");
        return "Герой идет пешком";
    }
}
