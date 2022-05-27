package com.example.tp4_v2;

public class Enemy {
    protected int lifePoints;
    protected int weaponDamage;
    protected String path;


    public int getLifePoints() {
        return lifePoints;
    }

    public void reduceLifePoints(int n) {
        lifePoints = lifePoints - n < 0 ? 0 : lifePoints - n;
    }

    public String getPath() {
        return path;
    }

    public boolean attack(Hero hero) {
        int damage = weaponDamage * (1-(hero.getArmor()/100));

        if(hero.isDefence()){
            damage = (int) (weaponDamage*0.7);
        }

        hero.reduceLifePoints(damage);
        return true;
    }
}