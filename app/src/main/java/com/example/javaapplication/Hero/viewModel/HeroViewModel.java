package com.example.javaapplication.Hero.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.javaapplication.Hero.FlyStrategy;
import com.example.javaapplication.Hero.Hero;
import com.example.javaapplication.Hero.HorseRideStrategy;
import com.example.javaapplication.Hero.WalkStrategy;


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


