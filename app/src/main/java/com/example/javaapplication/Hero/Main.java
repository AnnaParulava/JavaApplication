package com.example.javaapplication.Hero;

// Тестирование работы программы
public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero();

        // Герой начинает идти пешком
        hero.setMoveStrategy(new WalkStrategy());
        hero.move();

        // Герой пересаживается на лошадь
        hero.setMoveStrategy(new HorseRideStrategy());
        hero.move();

        // Герой решает лететь
        hero.setMoveStrategy(new FlyStrategy());
        hero.move();
    }
}
