package com.monprojet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    String url = "jdbc:mysql://localhost:3306/mabase";
    String utilisateur = "root";
    String motDePasse = "";
    Connection connexion = null;

    public Connexion() {
        try {
            // Établir la connexion
            this.connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public void close() {
        if (this.connexion != null) {
            try {
                this.connexion.close();
                System.out.println("Connexion fermée avec succès.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}