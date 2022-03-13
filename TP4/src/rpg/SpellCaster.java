package rpg;

public class SpellCaster extends Hero {


    private int manaPoints;
    private int diminuerMana;

    public SpellCaster() {
//        this.weaponDamage = 12;
    }


    @Override
    public int attack() {


        manaPoints = manaPoints - diminuerMana < 0 ? 0 : manaPoints - diminuerMana ;
        return weaponDamage;
    }

    @Override
    public void defend() {

    }

    @Override
    public void useConsumable(Consumable consumable) {

    }
}
