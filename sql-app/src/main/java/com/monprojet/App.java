package com.monprojet;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World !");

        Connexion connexion = new Connexion();
        int choix = 0;
        Scanner sc = new Scanner(System.in);
        GestionUtilisateur gu = new GestionUtilisateur();

        do {
            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("1 - Ajouter un utilisateur");
            System.out.println("2 - Lister les utilisateurs");
            System.out.println("0 - Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    gu.add(connexion, sc);
                    break;

                case 2:
                    gu.list(connexion);
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("L'action demandée n'existe pas !");
                    break;
            }
        } while (choix != 0);

        connexion.close();
        sc.close();
        System.exit(0);
    }
}