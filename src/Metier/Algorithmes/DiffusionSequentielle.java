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
            for (ObservateurGenerateurAsync o : generateur.getCanauxObservers())
            {
                Future<Void> future = o.update(generateur);
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getCopyValue(){
        return this.value;
    }

    public void removeCanaux(ObservateurGenerateurAsync canal){
        this.canaux.remove(canal);
    }
}
