package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateur;

import java.util.LinkedList;

public class DiffusionSequentielle implements AlgoDiffusion {

    private GenerateurImpl generateur;
    private LinkedList<ObservateurGenerateur> canaux;

    // Copie de la valeur
    int value;

    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
        this.canaux = new LinkedList<>();
    }

    @Override
    public void execute() {
        // Si la liste des canaux est vide - cad tout le monde a lu
        if(this.canaux.isEmpty()){
            this.value = this.generateur.getValue();

            this.canaux = generateur.getCanauxObservers();
            for (ObservateurGenerateur o : generateur.getCanauxObservers())
            {
                o.update(generateur);
            }
        }
    }

    public int getCopyValue(){
        return this.value;
    }

    public void removeCanaux(ObservateurGenerateur canal){
        this.canaux.remove(canal);
    }
}
