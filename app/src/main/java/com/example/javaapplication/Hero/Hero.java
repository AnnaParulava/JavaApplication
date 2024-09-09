package com.example.javaapplication.Hero;

class Hero {
    private MoveStrategy moveStrategy;

    void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    void move() {
        if (moveStrategy == null) {
            System.out.println("Стратегия перемещения не выбрана.");
            return;
        }
        moveStrategy.move();

    }
}

