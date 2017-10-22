package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateur;

import java.util.LinkedList;

public class DiffusionEpoque implements AlgoDiffusion {

    private GenerateurImpl generateur;
    private LinkedList<ObservateurGenerateur> canaux;

    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
    }

    @Override
    public void execute() {
        this.canaux = generateur.getCanauxObservers();
        for (ObservateurGenerateur o : generateur.getCanauxObservers())
        {
            o.update(generateur);
        }
    }
}
