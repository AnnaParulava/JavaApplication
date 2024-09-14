package com.example.javaapplication.hero;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hero hero = new Hero();

        while (true) {
            System.out.println("Выберите стратегию перемещения:");
            System.out.println("1. Идти пешком");
            System.out.println("2. Ехать на лошади");
            System.out.println("3. Лететь");
            System.out.println("0. Выйти");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        hero.setMoveStrategy(new WalkStrategy());
                        break;
                    case 2:
                        hero.setMoveStrategy(new HorseRideStrategy());
                        break;
                    case 3:
                        hero.setMoveStrategy(new FlyStrategy());
                        break;
                    case 0:
                        System.out.println("Выход из программы.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                        break;
                }

                hero.move();
            } else {
                System.out.println("Ошибка ввода. Пожалуйста, введите число.");
                scanner.next();
            }
        }
    }
}
