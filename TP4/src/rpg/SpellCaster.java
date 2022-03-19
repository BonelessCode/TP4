package rpg;

public class SpellCaster extends Hero {


    protected int manaPoints;
    protected int diminuerMana;

    public SpellCaster() {
//        this.weaponDamage = 12;
    }


    @Override
    public int attack() {

        if(manaPoints>diminuerMana){
            manaPoints = manaPoints - diminuerMana;
            return weaponDamage;
        }
        else{
            System.out.println("Vous n'avez pas assez de mana pour attaquer, Veuillez réessayer");
            return -1;
        }

    }

    @Override
    public void defend() {

    }

    @Override
    public void useConsumable(Consumable consumable) {

    }
}
