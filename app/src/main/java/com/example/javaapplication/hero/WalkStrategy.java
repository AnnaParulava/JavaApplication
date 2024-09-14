package com.example.javaapplication.hero;

public class WalkStrategy implements MoveStrategy {
    @Override
    public String move()
    {
        System.out.println("Герой идет пешком.");
        return "Герой идет пешком";
    }
}
