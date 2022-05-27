package com.example.tp4_v2;;

import java.util.List;

public class Healer extends SpellCaster {

    public Healer() {
        path="healer";
    }

    @Override
    public boolean attack(Enemy enemy) {
        return false;
    }

    public boolean healattack(List<Hero> heroes,int numberOfAimedHero) {


        System.out.println(manaPoints);
        if(manaPoints>diminuerMana){
            manaPoints = manaPoints - diminuerMana;
            heroes.get(numberOfAimedHero).addLifePoints(weaponDamage);
            return true;
        }
        else{
            System.out.println("Vous n'avez pas assez de mana pour soigner, Veuillez réessayer");
            return false;
        }
    }
}
