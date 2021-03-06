package com.example.tp4_v2;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;



public class AllTests {



    /**
     * Vérifie que la liste générée pour les ennemis est bien de taille n-1, n ou n+1 où n est la taille de la liste de héros.
     */
    @Test
    public void testListEnemy() {
        List<Hero> heroesList = new ArrayList<>();
        heroesList.add(new Hunter());
        heroesList.add(new Healer());
        heroesList.add(new Warrior());

        int enemiesSize = (int) Game.probabilite(List.of(heroesList.size() - 1, heroesList.size(), heroesList.size() + 1), List.of(10, 80, 10));

        Assertions.assertTrue(enemiesSize == 2 || enemiesSize == 3 || enemiesSize == 4);
    }

    /**
     * Vérifie que la fonction probabilite, qui renvoie un des elements de liste en fonction des probabilites ne renvoie pas -1, qui serait signe d'erreur.
     */
    @Test
    public void testProbabilites () {

        List<Object> liste = new ArrayList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);

        List<Integer> listeProbabilites = new ArrayList<>();
        listeProbabilites.add(33);
        listeProbabilites.add(34);
        listeProbabilites.add(35);

        Assertions.assertTrue((int) Game.probabilite(liste, listeProbabilites) != -1);
    }


    /**
     * TODO : Tests Jeu en lui-même - long terme pour ennemis definis.
     *
     */


    static List<Hero> renvoyerListeHeros() {
        List<Hero> heroesList = List.of(new Hunter(), new Healer(), new Warrior(), new Mage());
        return heroesList;
    }

    static List<Hero> renvoyerListeHerosSansHealer() {
        List<Hero> heroesList = List.of(new Hunter(), new Warrior(), new Mage());
        return heroesList;
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    void verificationRecompensesDegats(Hero hero) {

        int dmgBefore = hero.getWeaponDamage();

        Game.remiseRecompenses(hero,"B");

        Assertions.assertTrue(hero.getWeaponDamage()>dmgBefore);
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    void verificationRecompensesArmure(Hero hero) {

        int AttributBefore = hero.getArmor();

        Game.remiseRecompenses(hero,"A");

        Assertions.assertTrue(hero.getArmor()>AttributBefore);
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    void verificationRecompensesConsumableEffect(Hero hero) {

        int AttributBefore = hero.getBonusConsumable();

        Game.remiseRecompenses(hero,"C");

        Assertions.assertTrue(hero.getBonusConsumable()>AttributBefore);
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    void verificationRecompensesConsumableNumber(Hero hero) {

        int AttributBefore = hero.getPotions().size();
        int AttributBefore2 = hero.getFood().size();

        Game.remiseRecompenses(hero,"D");

        Assertions.assertTrue(hero.getPotions().size()>AttributBefore);
        Assertions.assertTrue(hero.getFood().size()>AttributBefore2);
    }





    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    public void SiOnPrendPlusDeDegatsQueDePVOnMeurt(Hero hero){


        Game.heroes = new ArrayList<>();
        Game.heroes.add(hero);

        Game.generateEnemies();

        Game.heroes.get(0).reduceLifePoints(300);

        Game.verifyHealth();

        Assertions.assertTrue(Game.heroes.isEmpty());
    }


    @ParameterizedTest
    @MethodSource("renvoyerListeHerosSansHealer")
    public void attaquerEnnemi(Hero hero){

        Game.heroes = List.of(hero);

        hero.setWeaponDamage(2147483647);


        Game.generateEnemies(1,0);

        Hero heroActuel = Game.heroes.get(0);


        Game.actionChoix(heroActuel,"Attaquer",0);

        Assertions.assertTrue(Game.enemies.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    public void defendre(Hero hero){
        Game.heroes = List.of(hero);

        Game.generateEnemies(1,0);

        Hero heroActuel = Game.heroes.get(0);

        Game.actionChoix(heroActuel,"Defendre",0);

        Assertions.assertTrue(heroActuel.isDefence());
    }

    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    public void consommerTousHeros(Hero hero){
        Game.heroes = List.of(hero);

        hero.reduceLifePoints(50);
        int previousLifePoints = hero.getLifePoints();


        Game.generateEnemies(1,0);

        Hero heroActuel = Game.heroes.get(0);

        Game.actionChoix(heroActuel,"Potion",1);

        Assertions.assertTrue(heroActuel.getLifePoints()==previousLifePoints+ new Potion().getValue());
    }



    @Test
    public void HealHeros(){
        Healer healer = new Healer();
        Game.heroes = List.of(healer);


        int hpDebut = Game.heroes.get(0).getLifePoints();
        System.out.println(hpDebut);
        healer.reduceLifePoints(1);

        healer.healattack(Game.heroes,0);
        System.out.println(healer.getLifePoints());


        Assertions.assertTrue(hpDebut<healer.getLifePoints());

    }




    @ParameterizedTest
    @MethodSource("renvoyerListeHeros")
    public void EnnemiAttaqueHeros(Hero hero){

        Game.heroes = new ArrayList<>();
        Game.heroes.add(hero);


        Game.generateEnemies(1,0);


        for (int i=0;i<7;i++){
            Game.randomAttack();
            System.out.println("Game heroes health : " +Game.heroes.get(0).getLifePoints());
        }
        Game.verifyHealth();

        Assertions.assertTrue(Game.heroes.isEmpty());
    }

    @Test
    public void ManaManquantAucuneAttaque(){
        Mage mage = new Mage();
        mage.setManaPoints(0);
        Assertions.assertTrue(!mage.attack(new Enemy()));
    }

    @Test
    public void FlecheManquanteAucuneAttaque(){
        Hunter hunter = new Hunter();
        hunter.setArrow(0);
        Assertions.assertTrue(!hunter.attack(new Enemy()));
    }

    @Test
    public void FlecheDiminue(){

        Hunter hunter = new Hunter();

        int flecheDebut = hunter.getArrow();
        hunter.attack(new Enemy());
        Assertions.assertTrue(hunter.getArrow()==flecheDebut-1);
    }

    @Test
    public void PotionDonneDeLaVieHunter(){

        Hunter hunter = new Hunter();
        int vieAvant = hunter.getLifePoints();
        System.out.println(vieAvant);

        hunter.useConsumable(1);
        System.out.println(hunter.getLifePoints());

        Assertions.assertTrue(vieAvant<hunter.getLifePoints());
    }

    @Test
    public void PotionDonneDeLaVieCaster(){

        Healer healer = new Healer();
        int vieAvant = healer.getLifePoints();
        System.out.println(vieAvant);

        healer.useConsumable(1);
        System.out.println(healer.getLifePoints());

        Assertions.assertTrue(vieAvant<healer.getLifePoints());
    }

    @Test
    public void PotionDonneDeLaVieWarrior(){
        Warrior warrior = new Warrior();
        warrior.setLifePoints(20);
        int vieAvant = warrior.getLifePoints();
        System.out.println(vieAvant);

        warrior.useConsumable(1);
        System.out.println(warrior.getLifePoints());

        Assertions.assertTrue(vieAvant<warrior.getLifePoints());
    }
}
