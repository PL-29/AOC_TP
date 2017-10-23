package Metier;

import java.util.concurrent.Callable;

public class Update implements Callable{

    private ObservateurGenerateur observateurAfficheur;
    private GenerateurAsync canal;

    public Update(ObservateurGenerateur observateurAfficheur, GenerateurAsync canal){
        this.observateurAfficheur = observateurAfficheur;
        this.canal = canal;

    }
    public Void call(){
        this.observateurAfficheur.update(this.canal);
        return null;
    }
}
