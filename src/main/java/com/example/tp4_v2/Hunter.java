package com.example.tp4_v2;;

public class Hunter extends Hero {


    private int baseArrow = 10;
    private int arrow;


    public Hunter() {
        this.arrow = baseArrow;
        path="hunter";
    }

    public void setArrow(int arrow) {
        this.arrow = arrow;
    }

    public void resetArrow(){
        this.arrow = baseArrow;
    }



    @Override
    public boolean attack(Enemy enemy) {
        if(arrow>0){
            arrow-=1;

            enemy.reduceLifePoints(weaponDamage);
            return true;
        }
        else{
            System.out.println("Vous n'avez pas assez de flèches pour attaquer, Veuillez réessayer");
            return false;
        }
    }



    public int getArrow() {
        return arrow;
    }

    @Override
    public void increaseArrowOrMana() {
        baseArrow+=10;
    }
}
