package com.example.tp4_v2;

import java.util.*;

public class Game {

    public static List<Hero> heroes = new ArrayList<>();
    public static List<Enemy> enemies;

    public static List<Hero> heroesLeftRound;



    Enemy enemy;
    static int probaBoss = 10;

    public static void main(String[] args) {
        HelloApplication.main(args);
    }



    public static void updateHeroesLeft(Hero hero) {
        heroesLeftRound.remove(hero);
    }




    public static void remiseRecompenses(Hero hero, String choice) {
        int i=0;

        hero.resetLifePoints();

        switch (choice) {
            case "A":
                hero.increaseArmor();
                break;

            case "B":
                hero.increaseDamage();
                break;

            case "C":
                hero.increaseConsumableEffect();
                break;
            case "D":
                hero.increaseConsumableNumber();
                break;
            case "E":
                hero.increaseArrowOrMana();
                break;
        }

        if(hero.getClass()==Hunter.class){
            Hunter hunter = (Hunter) hero;
            hunter.resetArrow();
        }
        if(SpellCaster.class.isAssignableFrom(hero.getClass())){
            SpellCaster spellCaster = (SpellCaster) hero;
            spellCaster.resetMana();
        }
        hero.resetConsumable();
    }

    public static void resetHeroesLeftRound(){
        heroesLeftRound = new ArrayList<>(heroes);
    }


    private static void fight() {

        verifyHealth();
//        actionChoix();
        verifyHealth();

        /**
         * Si la liste des ennemis n'est pas vide, alors ils attaquent TODO : (que attaque pour l'instant)
         */
        if(!enemies.isEmpty()){
            verifyHealth();
            randomAttack();
        }
    }

    public static void randomAttack() {
        int aimed;
        Random random = new Random();

        for(int i=0; i<enemies.size();i++){

            aimed = random.nextInt(heroes.size());


            enemies.get(i).attack(heroes.get(aimed));
        }
    }

    public static int actionChoix(Hero heroActuel, String choice) {
        return actionChoix(heroActuel, choice, -1);
    }

    public static int actionChoix(Hero heroActuel, String choice, int aimed) {

        heroActuel.resetDefence();

        switch (choice) {
            case "Attaquer":

                if (heroActuel.getClass() == Healer.class) {
                    ((Healer) heroActuel).healattack(heroes,aimed);
                }
                else {
                    heroActuel.attack(enemies.get(aimed));
                }

                /**
                 *  TODO : nécessaire avant mais après à reflechir
                 */
                verifyHealth();
                break;


            case "Defendre":
                heroActuel.defend();
                break;

            case "Potion":
                heroActuel.useConsumable(1);
                break;
            case "Bouffe":
                heroActuel.useConsumable(2);
                break;
        }
        return 0;
    }

    /**
     * Vérifie si les héros et ennemis qui jouent sont toujours en vie, s'ils ont 0PV ou moins, les enlève de la liste.
     */
    public static void verifyHealth() {

        for (int i=0;i<heroes.size();i++){
            if (heroes.get(i).getLifePoints()==0){
                heroes.remove(i);
            }
        }

        for (int j=0;j<enemies.size();j++){
            if (enemies.get(j).getLifePoints()==0){
                enemies.remove(j);
            }
        }

        if(heroes.isEmpty()){

        }
        else if(enemies.isEmpty()){

        }

    }



    /**
     * Génère une liste de héros de taille n aléatoires parmi les 4 choix
     */
    public static void generateHeroes(int n) {
        heroes = new ArrayList<>();

        for(int i=0;i<n;i++){
            heroes.add((Hero) probabilite(List.of(new Warrior(),new Hunter(),new Healer(),new Mage()), List.of(25,25,25,25)));
        }
    }


    /**
     * generateCombat sans entrer de taille définie, donc aléatoire entre la taille du héros -1 et +1
     */
    public static void generateEnemies() {
        generateEnemies(-1);
    }

    /**
     *
     * Génère une liste d'ennemis en prenant en compte les imprécisions en terme de nombre et les probabilite que le boss spawn.
     *
     * @param taille la taile de la liste
     */
    public static void generateEnemies(int taille){
        List<Enemy> enemyArrayList  = new ArrayList<>();

        if(taille==-1){
            taille =  heroes.size()<=1 ? (int) probabilite(List.of(heroes.size(),heroes.size()+1),List.of(90,10)):(int) probabilite(List.of(heroes.size()-1,heroes.size(),heroes.size()+1),List.of(10,80,10));
        }


        for(int i=0;i<taille;i++){
            enemyArrayList.add((Enemy) probabilite(List.of(new BasicEnemy(),new Boss()),List.of(100-probaBoss,probaBoss)));
        }

        enemies = enemyArrayList;
    }


    /**
     * Créé une forme de probabilité: chaque élément (entier) de contenu est lié à une probabilité dans la liste probabilites, lorsqu'on tombe dans la range prévue, ça renvoie l'entier correspondant.
     * Il faut évidemment que la somme de la liste probabilites fasse 100
     * @param contenu
     * @param probabilites
     * @return
     */
    public static Object probabilite(List<Object> contenu, List<Integer> probabilites) {

        Random random = new Random();
        int somme = 0;
        int borneDeFin;
        int randInt = random.nextInt(100);

        for(int i=0;i< contenu.size();i++){

            borneDeFin = somme+probabilites.get(i);

            if(randInt>=somme && randInt<=borneDeFin){
                return contenu.get(i);
            }
            somme=borneDeFin;
        }

//      Problème si on arrive jusqu'ici
        return -1;
    }
}
