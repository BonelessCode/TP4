package com.example.tp4_v2;

import java.util.*;

public class Game {

    public static List<Hero> heroes;
    public static List<Enemy> enemies;
    private static int playerTurn;

    public List<Hero> getHeroes() {
        return heroes;
    }

    public Game() {

    }

    public Game(List<Hero> heroes) {
        this.heroes = heroes;
    }

    Enemy enemy;
    static int probaBoss = 10;

    public static void main(String[] args) {
        playGame();
    }


    public static void playGame(){

        heroes = new ArrayList<>();
        heroes.add(new Hunter(10));
        heroes.add(new Healer());
        heroes.add(new Warrior());



        while (!heroes.isEmpty()){
            generateCombat();

//          Ordre aléatoire des joueurs
            Collections.shuffle(heroes);
            Collections.shuffle(enemies);

            playerTurn = (int) probabilite(List.of(0,1),List.of(50,50));

            while(!heroes.isEmpty() && !enemies.isEmpty()){
                fight();
            }
        }
        System.out.println("Vous avez PERDU !");
    }

    public static void remiseRecompenses(Hero hero, String choice) {
        int i=0;

            hero.resetLifePoints();

            switch (choice) {
                case "A":
                    hero.increaseArmor();

                case "B":
                    hero.increaseDamage();

                case "C":
                    hero.increaseConsumableEffect();

                case "D":
                    hero.increaseConsumableNumber();

                case "E":
                    hero.increaseArrowOrMana();
            }

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

    public static int actionChoix(Hero heroActuel,String choice,int aimed) {

            heroActuel.resetDefence();

            switch (choice) {
                case "A":

                    if (heroActuel.getClass() == Healer.class) {
                        ((Healer) heroActuel).healattack(heroes,aimed);
                    }
                    else {
                        heroActuel.attack(enemies.get(aimed));
                    }

                    /**
                     *  TODO : necessaire avant mais après à reflechir
                     */
                    verifyHealth();
//                    if(enemies.isEmpty()){
//                        RemiseRecompenses();
//                        return 0;
//                    }
                    break;


                case "D":
                    heroActuel.defend();
                    break;

                case "C":
                    heroActuel.useConsumable(aimed);
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
    }

    /**
     * generateCombat sans entrer de taille définie, donc aléatoire entre la taille du héros -1 et +1
     */
    public static void generateCombat() {
        generateCombat(-1);
    }

    /**
     *
     * Génère une liste d'ennemis en prenant en compte les imprécisions en terme de nombre et les probabilite que le boss spawn.
     *
     * @param taille la taile de la liste
     */
    public static void generateCombat(int taille){
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
