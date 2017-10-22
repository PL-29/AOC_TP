package Metier.Algorithmes;

import Metier.Algorithmes.AlgoDiffusion;
import Metier.GenerateurImpl;
import Metier.ObservateurGenerateur;

import java.util.LinkedList;

public class DiffusionAtomique implements AlgoDiffusion {

    private GenerateurImpl generateur;
    private LinkedList<ObservateurGenerateur> canaux;

    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
        this.canaux = new LinkedList<>();
    }

    @Override
    public void execute() {
        this.canaux = generateur.getCanauxObservers();
        System.out.println("Diffusion execute ");
        for (ObservateurGenerateur o : generateur.getCanauxObservers())
        {
            o.update(generateur);
        }
    }

    public boolean allReading (){
        return canaux.isEmpty();
    }

    public void removeCanaux(ObservateurGenerateur canal){
        this.canaux.remove(canal);
    }
}