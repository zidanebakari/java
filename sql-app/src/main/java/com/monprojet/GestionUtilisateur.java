package com.monprojet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionUtilisateur {
    ArrayList<String> listUser = new ArrayList<>();

    // Méthode pour ajouter un utilisateur
    public void add(Connexion connect, Scanner sc) {
        sc.nextLine();
        System.out.println("Nom de l'utilisateur :");
        String lastName = sc.nextLine();

        System.out.println("Prénom de l'utilisateur :");
        String firstName = sc.nextLine();

        System.out.println("Email de l'utilisateur :");
        String email = sc.nextLine();

        try {
            String sqlInsert = "INSERT INTO utilisateurs (prenom, nom, email) VALUES (?, ?, ?)";
            PreparedStatement pstmtInsert = connect.connexion.prepareStatement(sqlInsert);
            pstmtInsert.setString(1, firstName);
            pstmtInsert.setString(2, lastName);
            pstmtInsert.setString(3, email);

            int rowsAffected = pstmtInsert.executeUpdate();
            System.out.println("Insertion réussie, lignes affectées : " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }

    // Méthode pour lister les utilisateurs
    public void list(Connexion connect) {
        listUser.clear(); // Vider la liste avant de la remplir à nouveau

        try {
            String sqlSelect = "SELECT id, nom, prenom, email FROM utilisateurs";
            PreparedStatement pstmtSelect = connect.connexion.prepareStatement(sqlSelect);
            ResultSet rs = pstmtSelect.executeQuery();

            System.out.println("\nListe des utilisateurs :");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");

                // Ajouter les informations de l'utilisateur à l'ArrayList
                String userInfo = "ID : " + id + ", Nom : " + nom + ", Prénom : " + prenom + ", Email : " + email;
                listUser.add(userInfo);

                // Afficher l'utilisateur
                System.out.println(userInfo);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }
}