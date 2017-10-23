package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateur;
import Metier.ObservateurGenerateurAsync;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DiffusionEpoque implements AlgoDiffusion {

    private GenerateurImpl generateur;

    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
    }

    @Override
    public void execute() {
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
