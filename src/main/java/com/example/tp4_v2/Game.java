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
                 *  TODO : n??cessaire avant mais apr??s ?? reflechir
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
     * V??rifie si les h??ros et ennemis qui jouent sont toujours en vie, s'ils ont 0PV ou moins, les enl??ve de la liste.
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
     * G??n??re une liste de h??ros de taille n al??atoires parmi les 4 choix
     */
    public static void generateHeroes(int nbHealer,int nbHunter, int nbWarrior, int nbMage) {
        heroes = new ArrayList<>();

        List<Integer> listNumbers = List.of(nbHealer,nbHunter,nbWarrior,nbMage);

        for(int i=0;i<nbHealer;i++){
            heroes.add(new Healer());
        }

        for(int i=0;i<nbHunter;i++){
            heroes.add(new Hunter());
        }

        for(int i=0;i<nbWarrior;i++){
            heroes.add(new Warrior());
        }

        for(int i=0;i<nbMage;i++){
            heroes.add(new Mage());
        }

        Collections.shuffle(heroes);
    }


    /**
     * generateCombat sans entrer de taille d??finie, donc al??atoire entre la taille du h??ros -1 et +1
     */
    public static void generateEnemies() {
        generateEnemies(-1,0);
    }


    /**
     * G??n??re une liste d'ennemis en prenant en compte les impr??cisions en terme de nombre et les probabilite que le boss spawn, et la difficult?? en fonction du round actuel.
     * @param taille param??tre optionnel pour directement definir la taille de la liste
     * @param round nombre de vague d'ennemis battus, ce qui augmente le nombre d'ennemis reguli??rement afin d'augmenter la difficult??
     */
    public static void generateEnemies(int taille,int round){
        List<Enemy> enemyArrayList  = new ArrayList<>();

        if(taille==-1){
            taille = heroes.size()<=1 ? (int) probabilite(List.of(heroes.size()+round,heroes.size()+1+round),List.of(90,10)) : (int) probabilite(List.of(heroes.size()-1+round,heroes.size()+round,heroes.size()+1+round),List.of(10,80,10));
        }


        for(int i=0;i<taille;i++){
            enemyArrayList.add((Enemy) probabilite(List.of(new BasicEnemy(),new Boss()),List.of(100-probaBoss,probaBoss)));
        }

        enemies = enemyArrayList;
    }


    /**
     * Cr???? une forme de probabilit??: chaque ??l??ment (entier) de contenu est li?? ?? une probabilit?? dans la liste probabilites, lorsqu'on tombe dans la range pr??vue, ??a renvoie l'entier correspondant.
     * Il faut ??videmment que la somme de la liste probabilites fasse 100
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

//      Probl??me si on arrive jusqu'ici
        return -1;
    }
}
