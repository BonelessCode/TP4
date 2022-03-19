package rpg;

public class Hunter extends Hero {

    private int arrow;


    public Hunter(int arrow) {
        this.arrow = arrow;
    }

    @Override
    public int attack() {
        if(arrow>0){
            arrow-=1;
            return weaponDamage;
        }
        else{
            System.out.println("Vous n'avez pas assez de flèches pour attaquer, Veuillez réessayer");
            return -1;
        }

    }

    @Override
    public void defend() {

    }

    @Override
    public void useConsumable(Consumable consumable) {

    }


    public int getArrow() {
        return arrow;
    }
}
