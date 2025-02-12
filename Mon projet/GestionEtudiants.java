package Mon projet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionEtudiants {
    private List<Etudiant> listeEtudiants;

    public GestionEtudiants() {
        this.listeEtudiants = new ArrayList<>();
    }

    public void ajouterEtudiant(String nom, String prenom, String classe) {
        listeEtudiants.add(new Etudiant(nom, prenom, classe));
        System.out.println("Étudiant ajouté avec succès !");
    }

    public void afficherEtudiants() {
        if (listeEtudiants.isEmpty()) {
            System.out.println("Aucun étudiant dans la liste.");
        } else {
            System.out.println("Liste des étudiants :");
            for (Etudiant etudiant : listeEtudiants) {
                System.out.println(etudiant);
            }
        }
    }

    public void supprimerEtudiant(String nom) {
        Iterator<Etudiant> it = listeEtudiants.iterator();
        while (it.hasNext()) {
            if (it.next().getNom().equalsIgnoreCase(nom)) {
                it.remove();
                System.out.println("Étudiant supprimé avec succès !");
                return;
            }
        }
        System.out.println("Aucun étudiant trouvé avec ce nom.");
    }
}