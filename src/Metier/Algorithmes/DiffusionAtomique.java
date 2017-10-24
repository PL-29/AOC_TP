package Metier.Algorithmes;

import Metier.Algorithmes.AlgoDiffusion;
import Metier.GenerateurImpl;
import Metier.ObservateurGenerateur;
import Metier.ObservateurGenerateurAsync;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DiffusionAtomique implements AlgoDiffusion {

    private GenerateurImpl generateur;
    private LinkedList<ObservateurGenerateurAsync> canaux;

    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
        this.canaux = new LinkedList<>();
    }

    @Override
    public void execute() {
        this.generateur.stop();
        this.canaux = this.generateur.getCanauxObservers();
        System.out.println("Diffusion execute ");
        for (ObservateurGenerateurAsync o : this.canaux)
        {
            o.update(this.generateur);
        }
    }

    @Override
    public String gestionValue(ObservateurGenerateurAsync oCanal){
        this.removeCanaux(oCanal);
        if(this.canaux.isEmpty()){
            this.generateur.start();
        }
        return this.generateur.getValue();
    }

    private void removeCanaux(ObservateurGenerateurAsync canal){
        this.canaux.remove(canal);
    }
}
