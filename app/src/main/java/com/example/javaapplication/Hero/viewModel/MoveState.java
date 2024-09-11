package com.example.javaapplication.Hero.viewModel;

public sealed abstract class MoveState permits MoveState.None, MoveState.Walking, MoveState.Horse, MoveState.Flying {

    // Синглтон экземпляры
    private static final MoveState NONE = new None();
    private static final MoveState WALKING = new Walking();
    private static final MoveState HORSE = new Horse();
    private static final MoveState FLYING = new Flying();

    // Приватный конструктор для предотвращения создания новых экземпляров
    private MoveState() {
    }

    // Методы доступа к синглтон экземплярам
    public static MoveState none() {
        return NONE;
    }

    public static MoveState walking() {
        return WALKING;
    }

    public static MoveState horse() {
        return HORSE;
    }

    public static MoveState flying() {
        return FLYING;
    }

    // Вложенные классы с приватными конструкторами
    public static final class None extends MoveState {
        private None() {
        }
    }

    public static final class Walking extends MoveState {
        private Walking() {
        }
    }

    public static final class Horse extends MoveState {
        private Horse() {
        }
    }

    public static final class Flying extends MoveState {
        private Flying() {
        }
    }
}

