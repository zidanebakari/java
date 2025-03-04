package com.monprojet;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans l'application de gestion des utilisateurs !");

        Connexion connexion = new Connexion();
        int choix = 0;
        Scanner sc = new Scanner(System.in);
        GestionUtilisateur gu = new GestionUtilisateur();

        do {
            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("1 - Ajouter un utilisateur");
            System.out.println("2 - Afficher la liste des utilisateurs");
            System.out.println("3 - Éditer un utilisateur par ID");
            System.out.println("4 - Supprimer un utilisateur par ID");
            System.out.println("5 - Rechercher un utilisateur par email ou prénom");
            System.out.println("6 - Exporter la liste des utilisateurs en CSV");
            System.out.println("0 - Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    gu.add(connexion, sc);
                    break;
                case 2:
                    gu.displayUsers(connexion);
                    break;
                case 3:
                    gu.editUserById(connexion, sc);
                    break;
                case 4:
                    gu.deleteUserById(connexion, sc);
                    break;
               