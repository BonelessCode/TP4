package rpg;

import java.util.List;

public abstract class Hero {
    final int basicLifePoints = 200 ;
    boolean defence;
    protected int lifePoints = basicLifePoints;

    private int armor;

    protected int weaponDamage = 200;
    int maxLife = 100;

    private List<Potion> potions;
    private List<Food> lembas;

    final int addConsumableEffect = 20;
    int bonusConsumable;

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



    public abstract boolean attack(Enemy enemy);

    public void defend(){
        setDefence(true);
    }

    public boolean useConsumable(String choix){
        if(!potions.isEmpty() && choix.equals("A")){
            setLifePoints(lifePoints+potions.get(0).getValue()+bonusConsumable>maxLife ? maxLife:lifePoints+potions.get(0).getValue());
            potions.remove(0);
            return true;
        }
        else if (!lembas.isEmpty() && choix.equals("B")){

            setLifePoints(lifePoints+lembas.get(0).getValue()+bonusConsumable>maxLife ? maxLife:lifePoints+lembas.get(0).getValue());
            lembas.remove(0);
            return true;
        }
        else{
            System.out.println("Vous n'avez pas suffisamment du consommable pour l'action souhaitée, veuillez réessayer");
            return false;
        }
    }


    public void increaseArmor() {
        armor+=20;
    }

    public void increaseDamage() {
        weaponDamage+=20;
    }


    public abstract void increaseArrowOrMana();

    public void increaseConsumableNumber() {
        potions.add(new Potion());
        lembas.add(new Food());
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
}
