package com.example.tp4_v2;;

public class Warrior extends Hero {

    public Warrior() {
//        this.weaponDamage = 12;
    }

    @Override
    public boolean attack(Enemy enemy) {
        enemy.reduceLifePoints(weaponDamage);
        return true;
    }


    @Override
    public void increaseArrowOrMana() {
        System.out.println("Votre personnage est un Warrior, ses dégâts se veront donc augmenter.");
        increaseDamage();
    }
}
