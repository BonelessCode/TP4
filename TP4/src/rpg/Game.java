package rpg;

import utils.InputParser;

import java.util.*;

public class Game {
    public static List<Hero> heroes;
    public static List<Enemy> enemies;
    public static InputParser inputParser = new InputParser();
    private static int playerTurn;

    Enemy enemy;
    static int probaBoss = 10;

    public static void main(String[] args) {
        playGame();
    }


    public static void playGame(){

        heroes = new ArrayList<>();
        heroes.add(new Hunter());
        heroes.add(new Healer());
        heroes.add(new Warrior());



        while (heroes!=null){
            enemies = generateCombat(heroes);

//          Ordre aléatoire des joueurs
            Collections.shuffle(heroes);
            Collections.shuffle(enemies);

            playerTurn = (int) probabilite(List.of(0,1),List.of(50,50));

            while(heroes!=null && enemies!=null){
                fight();
            }
        }
    }

    private static void fight() {

        String choice;
        int aimed;
        Hero heroActuel;



        List<Integer> heroesHealth = new ArrayList<>();

        for(Hero hero : heroes){
            heroesHealth.add(hero.getLifePoints());
        }

        System.out.println("Heros: "+heroes);

        System.out.println("PDV alliés: "+heroesHealth);
        List<Integer> enemyHealth = new ArrayList<>();

        for(Enemy enemy : enemies){
            enemyHealth.add(enemy.getLifePoints());
        }

        System.out.println("PDV ennemis: "+enemyHealth);
        System.out.println("Ennemis: "+enemies+"\n\n");




        if(playerTurn==0) {
            verifyHealth();
            actionChoix();
        }

        else{
            verifyHealth();
            randomAttack();
        }

        playerTurn = Math.abs(1-playerTurn);


    }

    private static void randomAttack() {
        int aimed;
        Random random = new Random();

        for(int i=0; i<enemies.size();i++){
            aimed = random.nextInt(heroes.size());
            heroes.get(aimed).reduceLifePoints(enemies.get(i).getWeaponDamage());
        }
    }

    private static void actionChoix() {
        Hero heroActuel;
        String choice;
        int aimed;
        for (int i = 0; i < heroes.size(); i++) {
            heroActuel = heroes.get(i);


            System.out.println("Numero du hero actuel: "+i);
            System.out.println("Type de hero actuel: "+heroActuel.getClass());
            System.out.println("PV: "+heroActuel.getLifePoints()+"       Weapon DMG: "+heroActuel.getWeaponDamage());

            choice = inputParser.askTurnChoice(i);

            switch (choice) {
                case "A":
                    System.out.println("Vous avez choisi d'attaquer !");

                    if(heroActuel.getClass()==Healer.class){
                        System.out.println("Quel allié voulez-vous soigner ? ");
                        aimed = inputParser.askNumberInList(new ArrayList<Object>(heroes));
                        heroes.get(aimed).addLifePoints(heroActuel.attack());
                    }
                    else{
                        System.out.println("Quel ennemi voulez-vous attaquer ? ");
                        aimed = inputParser.askNumberInList(new ArrayList<Object>(enemies));

                        enemies.get(aimed).reduceLifePoints(heroActuel.attack());
                    }
                    verifyHealth();
                    break;



                case "D":
                    System.out.println("Vous avez choisi de défendre !");
                    heroActuel.defend();
                    break;

                case "C":
                    System.out.println("Vous avez choisi de consommer !");
                    //                    if (Consommable existe)
                    heroActuel.useConsumable(new Potion());
                    break;
            }
        }
    }

    /**
     * Vérifie si les héros qui jouent sont toujours en vie, s'ils ont 0PV ou moins, les enlève de la liste.
     */
    private static void verifyHealth() {

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
     * Génère une liste d'ennemis en prenant en compte les imprécisions en terme de nombre et les probabilite que le boss spawn.
     * @param heroes
     * @return
     */
    public static List<Enemy> generateCombat(List<Hero> heroes){
        List<Enemy> enemies  = new ArrayList<>();


        int taille =  heroes.size()<=1 ? (int) probabilite(List.of(heroes.size(),heroes.size()+1),List.of(90,10)):(int) probabilite(List.of(heroes.size()-1,heroes.size(),heroes.size()+1),List.of(10,80,10));
        Random random = new Random();

        for(int i=0;i<taille;i++){
            enemies.add((Enemy) probabilite(List.of(new BasicEnemy(),new Boss()),List.of(100-probaBoss,probaBoss)));
        }
        return enemies;
    }


    /**
     * Créé une forme de probabilité: chaque élément (entier) de contenu est lié à une probabilité dans la liste probabilites, lorsqu'on tombe dans la range prévue, ça renvoie l'entier correspondant.
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
