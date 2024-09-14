package com.example.javaapplication.hero;

public class Hero {
    private MoveStrategy moveStrategy;

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public String move() {
        if (moveStrategy == null) {
            System.out.println("Стратегия перемещения не выбрана.");
            return "";
        }
        return moveStrategy.move();

    }
}

