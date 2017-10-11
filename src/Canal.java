public class Canal implements ObservateurGenerateur{

    private Afficheur afficheur;

    public Canal(Afficheur afficheur){
        this.afficheur = afficheur;
    }

    public void update(Generateur generateur){
        // TODO spécification

        // TODO appeler la méthode update(subject:Generateur) de la classe Afficheur
        this.afficheur.update(generateur);
    }

    public int getValue(){
        return 0;
    }
}
