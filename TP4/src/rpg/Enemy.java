package rpg;

public class Enemy {
    protected int lifePoints;
    protected int weaponDamage;

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void reduceLifePoints(int n){
        lifePoints = lifePoints - n < 0 ? 0 : lifePoints -n ;
    }
}
