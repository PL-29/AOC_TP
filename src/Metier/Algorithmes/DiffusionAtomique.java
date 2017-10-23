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
        this.canaux = generateur.getCanauxObservers();
        System.out.println("Diffusion execute ");
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

    public boolean allReading (){
        return canaux.isEmpty();
    }

    public void removeCanaux(ObservateurGenerateurAsync canal){
        this.canaux.remove(canal);
    }
}
