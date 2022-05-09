package com.example.tp4_v2;;

public abstract class SpellCaster extends Hero {

    protected int baseManaPoints = 90;
    protected int manaPoints;
    protected int diminuerMana;

    public SpellCaster() {
        this.manaPoints = baseManaPoints;
        this.diminuerMana = 12;
    }

    public int getMana() {
        return manaPoints;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    public abstract boolean attack(Enemy enemy);


    @Override
    public void increaseArrowOrMana() {
        baseManaPoints+=20;
    }

    public void resetMana() {
        manaPoints = baseManaPoints;
    }
}
