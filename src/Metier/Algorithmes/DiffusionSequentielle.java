package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateur;
import Metier.ObservateurGenerateurAsync;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DiffusionSequentielle implements AlgoDiffusion {

    private GenerateurImpl generateur;
    private LinkedList<ObservateurGenerateurAsync> canaux;

    // Copie de la valeur
    String copieValue;

    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
        this.canaux = new LinkedList<>();
    }

    @Override
    public void execute() {
        // Si la liste des canaux est vide - cad tout le monde a lu
        if(this.canaux.isEmpty()){
            this.copieValue = this.generateur.getValue();

            this.canaux = generateur.getCanauxObservers();
            for (ObservateurGenerateurAsync o : generateur.getCanauxObservers())
            {
                o.update(generateur);
            }
        }
    }

    @Override
    public String gestionValue(ObservateurGenerateurAsync oCanal){
        this.removeCanaux(oCanal);
        return this.getCopyValue();
    }

    public String getCopyValue(){
        return this.copieValue;
    }

    public void removeCanaux(ObservateurGenerateurAsync canal){
        this.canaux.remove(canal);
    }
}
