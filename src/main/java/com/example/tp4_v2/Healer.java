package com.example.tp4_v2;;

import java.util.List;

public class Healer extends SpellCaster {

    @Override
    public boolean attack(Enemy enemy) {
        return false;
    }

    public boolean healattack(List<Hero> heroes,int aimedHero) {
        if(manaPoints>diminuerMana){
            manaPoints = manaPoints - diminuerMana;
            heroes.get(aimedHero).addLifePoints(weaponDamage);
            return true;
        }
        else{
            System.out.println("Vous n'avez pas assez de mana pour soigner, Veuillez réessayer");
            return false;
        }
    }
}
