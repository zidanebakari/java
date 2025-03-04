package com.monprojet;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUtilisateur {

    // Ajouter un utilisateur
    public void add(Connexion connect, Scanner sc) {
        sc.nextLine(); // Pour consommer la nouvelle ligne après nextInt()
        System.out.print("Nom de l'utilisateur : ");
        String lastName = sc.nextLine();

        System.out.print("Prénom de l'utilisateur : ");
        String firstName = sc.nextLine();

        System.out.print("Email de l'utilisateur : ");
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
            System.err.println("Erreur SQL lors de l'ajout : " + e.getMessage());
        }
    }

    // Afficher la liste des utilisateurs
    public void displayUsers(Connexion connect) {
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
                System.out.println("ID : " + id + ", Nom : " + nom + ", Prénom : " + prenom + ", Email : " + email);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération des utilisateurs : " + e.getMessage());
        }
    }

    // Éditer un utilisateur par son ID
    public void editUserById(Connexion connect, Scanner sc) {
        System.out.print("Entrez l'ID de l'utilisateur à éditer : ");
        int userId = sc.nextInt();
        sc.nextLine(); // Pour consommer la nouvelle ligne après nextInt()

        System.out.print("Nouveau nom de l'utilisateur : ");
        String newLastName = sc.nextLine();

        System.out.print("Nouveau prénom de l'utilisateur : ");
        String newFirstName = sc.nextLine();

        System.out.print("Nouvel email de l'utilisateur : ");
        String newEmail = sc.nextLine();

        try {
            String sqlUpdate = "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ? WHERE id = ?";
            PreparedStatement pstmtUpdate = connect.connexion.prepareStatement(sqlUpdate);
            pstmtUpdate.setString(1, newLastName);
            pstmtUpdate.setString(2, newFirstName);
            pstmtUpdate.setString(3, newEmail);
            pstmtUpdate.setInt(4, userId);

            int rowsAffected = pstmtUpdate.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Utilisateur édité avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'édition : " + e.getMessage());
        }
    }

    // Supprimer un utilisateur par son ID
    public void deleteUserById(Connexion connect, Scanner sc) {
        System.out.print("Entrez l'ID de l'utilisateur à supprimer : ");
        int userId = sc.nextInt();

        try {
            String sqlDelete = "DELETE FROM utilisateurs WHERE id = ?";
            PreparedStatement pstmtDelete = connect.connexion.prepareStatement(sqlDelete);
            pstmtDelete.setInt(1, userId);

            int rowsAffected = pstmtDelete.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la suppression : " + e.getMessage());
        }
    }

    // Rechercher un utilisateur par email ou prénom
    public void searchUser(Connexion connect, Scanner sc) {
        sc.nextLine(); // Pour consommer la nouvelle ligne après nextInt()
        System.out.print("Entrez l'email ou le prénom de l'utilisateur à rechercher : ");
        String searchTerm = sc.nextLine();

        try {
            String sqlSearch = "SELECT id, nom, prenom, email FROM utilisateurs WHERE email = ? OR prenom = ?";
            PreparedStatement pstmtSearch = connect.connexion.prepareStatement(sqlSearch);
            pstmtSearch.setString(1, searchTerm);
            pstmtSearch.setString(2, searchTerm);

            ResultSet rs = pstmtSearch.executeQuery();

            System.out.println("\nRésultats de la recherche :");
            boolean found = false;
            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                System.out.println("ID : " + id + ", Nom : " + nom + ", Prénom : " + prenom + ", Email : " + email);
            }

            if (!found) {
                System.out.println("Aucun utilisateur trouvé avec cet email ou prénom.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la recherche : " + e.getMessage());
        }
    }

    // Exporter la liste des utilisateurs en CSV
    public void exportToCSV(Connexion connect, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // En-tête du fichier CSV
            writer.append("ID,Nom,Prénom,Email\n");

            // Récupérer la liste des utilisateurs
            String sqlSelect = "SELECT id, nom, prenom, email FROM utilisateurs";
            PreparedStatement pstmtSelect = connect.connexion.prepareStatement(sqlSelect);
            ResultSet rs = pstmtSelect.executeQuery();

            // Écrire chaque utilisateur dans le fichier CSV
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");

                writer.append(String.valueOf(id)).append(",");
                writer.append(nom).append(",");
                writer.append(prenom).append(",");
                writer.append(email).append("\n");
            }

            System.out.println("Exportation réussie vers : " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier CSV : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de la récupération des utilisateurs : " + e.getMessage());
        }
    }
}