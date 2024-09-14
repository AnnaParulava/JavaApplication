package com.example.javaapplication.hero.ui;

import androidx.lifecycle.ViewModel;

import com.example.javaapplication.hero.FlyStrategy;
import com.example.javaapplication.hero.Hero;
import com.example.javaapplication.hero.HorseRideStrategy;
import com.example.javaapplication.hero.WalkStrategy;


public class HeroViewModel extends ViewModel {

    private final Hero hero = new Hero();

    public String chooseMoveStrategy(MoveState state) {
        if (state instanceof MoveState.Walking) {
            hero.setMoveStrategy(new WalkStrategy());
        } else if (state instanceof MoveState.Horse) {
            hero.setMoveStrategy(new HorseRideStrategy());
        } else if (state instanceof MoveState.Flying) {
            hero.setMoveStrategy(new FlyStrategy());
        } else {
            return  "Выберите способ передвижения.";
        }

        return hero.move();
    }
}


