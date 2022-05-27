package com.example.tp4_v2;;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Hero {
    final int basicLifePoints = 70;
    boolean defence;
    protected int lifePoints = basicLifePoints;



    private int armor;
    protected String path;

    protected int weaponDamage = 27;

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    int maxLife = 100;
    final int addConsumableEffect = 20;
    int bonusConsumable;


    private List<Potion> potions = new ArrayList();
    private List<Food> lembas = new ArrayList();
    private int consumableNumber = 3;
    private int maxArmor = 40;


    public List<Potion> getPotions() {
        return potions;
    }

    public List<Food> getFood() {
        return lembas;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = Math.min(lifePoints, maxLife);
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public Hero() {
        resetConsumable();
    }

    public void resetConsumable() {

        potions = new ArrayList<>();
        lembas = new ArrayList<>();

        for(int i=0;i<consumableNumber;i++){
            potions.add(new Potion());
            lembas.add(new Food());
        }
    }

    public void reduceLifePoints(int n){
        lifePoints = Math.max(lifePoints - n, 0);

    }

    public void addLifePoints(int n){
        lifePoints = Math.min(lifePoints + n, maxLife);
    }

    public abstract boolean attack(Enemy enemy);

    public void defend(){
        setDefence(true);
    }

    public boolean useConsumable(int choix){
        if(!potions.isEmpty() && choix==1){
            setLifePoints(lifePoints+potions.get(0).getValue()+bonusConsumable);
            potions.remove(0);
            return true;
        }
        else if (!lembas.isEmpty() && choix==2){
            setLifePoints(lifePoints+lembas.get(0).getValue()+bonusConsumable);
            lembas.remove(0);
            return true;
        }
        else{
            return false;
        }
    }

    public int getBonusConsumable() {
        return bonusConsumable;
    }

    public int getArmor() {
        return armor;
    }

    public void increaseArmor() {
        armor = (armor >= maxArmor ? maxArmor : armor+10);
    }

    public void increaseDamage() {
        weaponDamage+=20;
    }


    public abstract void increaseArrowOrMana();

    public void increaseConsumableNumber() {
        consumableNumber +=1;
    }

    public void increaseConsumableEffect() {
        bonusConsumable+=addConsumableEffect;
    }

    public void resetLifePoints() {
        lifePoints = basicLifePoints;
    }

    public void resetDefence() { defence = false; }

    public void setDefence(boolean defence) {
        this.defence = defence;
    }

    public boolean isDefence() {
        return defence;
    }

    public String getPath() {
        return path;
    }
}
