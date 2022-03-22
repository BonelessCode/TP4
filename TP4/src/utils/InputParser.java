package utils;

import rpg.Hero;

import java.util.List;
import java.util.Scanner;

public class InputParser {
    Scanner sc = new Scanner(System.in);

    public String askTurnChoice(int n){
        int choix;

        String choixString;

        do {
            System.out.println("Que voulez-vous faire ce tour avec le hero n°"+n+" ? [A (attaquer)/D (défendre)/C (consommable)");
            choixString = sc.nextLine();
        }
        while (!choixString.equals("A") && !choixString.equals("D") && !choixString.equals("C"));

        return choixString;
    }

    public String askPrice(){
        String choixString;
        do {
            System.out.println("Quelle compétence voulez-vous améliorer pour le héro" +"? [A (Augmenter Armure)/B (Augmenter Dégâts)/C (Augmenter Efficacité Potion et Nourriture)/D (Augmenter le nombre de potion ou nourriture)/E (Augmenter Nombre de Flèches)");
            choixString = sc.nextLine();
        }
        while (!choixString.equals("A") && !choixString.equals("D") && !choixString.equals("C") && !choixString.equals("D") && !choixString.equals("E"));

        return choixString;
    }

    public int askNumberInList(List<Object> list) {
        int choix;

        int choixInt=-1;

        do {
            System.out.println("Quel est votre choix ? Numéro entre 0 et "+(list.size()-1));

            choixInt = sc.nextInt();


        }
        while (choixInt<0 || choixInt>=list.size());


        return choixInt;
    }

    public String askConsumable() {

        String choix;

        do {
            System.out.println("Quel type de consommable voulez-vous prendre ? A (Potions) / B (Nourriture)");

            choix = sc.nextLine();


        }
        while (!choix.equals("A") && !choix.equals("B"));

        return choix;
    }
}
