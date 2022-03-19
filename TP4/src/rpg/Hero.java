package rpg;

import java.util.List;

public abstract class Hero {

    protected int lifePoints = 200;
    private int armor;

    protected int weaponDamage = 200;
    int maxLife = 100;

    private List<Potion> potions;
    private List<Food> lembas;


    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints>maxLife ? maxLife : lifePoints;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void reduceLifePoints(int n){
        lifePoints = lifePoints - n < 0 ? 0 : lifePoints -n ;
    }

    public void addLifePoints(int n){
        lifePoints = lifePoints + n > maxLife ? maxLife : lifePoints + n ;
    }



    public abstract int attack();

    public abstract void defend();

    public void useConsumable(Consumable consumable){
        setLifePoints(lifePoints+consumable.getValue()>maxLife ? maxLife:lifePoints+consumable.getValue());
    }

}
