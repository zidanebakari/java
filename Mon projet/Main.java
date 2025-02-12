package Mon projet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestionEtudiants gestion = new GestionEtudiants();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\nMenu :");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Afficher la liste des étudiants");
            System.out.println("3. Supprimer un étudiant");
            System.out.println("4. Quitter");
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); 

            switch (choix) {
                case 1:
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Classe : ");
                    String classe = scanner.nextLine();
                    gestion.ajouterEtudiant(nom, prenom, classe);
                    break;
                case 2:
                    gestion.afficherEtudiants();
                    break;
                case 3:
                    System.out.print("Nom de l'étudiant à supprimer : ");
                    String nomSuppr = scanner.nextLine();
                    gestion.supprimerEtudiant(nomSuppr);
                    break;
                case 4:
                    System.out.println("Programme terminé.");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 4);

        scanner.close();
    }
}