package com.example.tp4_v2;;

public class Mage extends SpellCaster {
    public Mage() {
        path="mage";
    }


    @Override
    public boolean attack(Enemy enemy) {
        if(manaPoints>diminuerMana){
            manaPoints = manaPoints - diminuerMana;
            enemy.reduceLifePoints(weaponDamage);
            return true;
        }
        else{
            System.out.println("Vous n'avez pas assez de mana pour attaquer, Veuillez réessayer");
            return false;
        }
    }
}
