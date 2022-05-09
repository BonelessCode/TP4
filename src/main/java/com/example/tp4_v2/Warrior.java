package com.example.tp4_v2;;

public class Warrior extends Hero {

    public Warrior() {
//        this.weaponDamage = 12;
        path="warrior";
    }

    @Override
    public boolean attack(Enemy enemy) {
        enemy.reduceLifePoints(weaponDamage);
        return true;
    }


    @Override
    public void increaseArrowOrMana() {
        increaseDamage();
    }
}
