package utils;

import rpg.Hero;

import java.util.List;
import java.util.Scanner;

public class InputParser {

    public String askTurnChoice(int n){
        int choix;
        Scanner sc = new Scanner(System.in);
        String choixString;

        do {
            System.out.println("Que voulez vous faire ce tour avec le hero n°"+n+" ? [A (attaquer)/D (défendre)/C (consommable]");
            choixString = sc.nextLine();
        }
        while (!choixString.equals("A") && !choixString.equals("D") && !choixString.equals("C"));




        return choixString;
    }

    public int askNumberInList(List<Object> list) {
        int choix;
        Scanner sc2 = new Scanner(System.in);
        int choixInt=-1;

        do {
            System.out.println("Quel est votre choix ? Numéro entre 0 et "+(list.size()-1));

            choixInt = sc2.nextInt();


        }
        while (choixInt<0 || choixInt>=list.size());


        return choixInt;
    }
}
