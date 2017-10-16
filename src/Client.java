import java.util.List;

public class Client {

    public static void main(String[] args) {

        // Choisi l'algo
        AlgoDiffusion algo = new DiffusionAtomique();

        GenerateurImpl g = new GenerateurImpl(algo);

        // Création des canaux
        Canal canal1 = new Canal();
        Canal canal2 = new Canal();

        // Création des afficheurs
        Afficheur afficheur1 = new Afficheur();
        Afficheur afficheur2 = new Afficheur();

        canal1.attach(afficheur1);
        canal2.attach(afficheur2);

        g.attach(canal1);
        g.attach(canal2);

        g.start();
    }
}
