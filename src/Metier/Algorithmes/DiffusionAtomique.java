package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateurAsync;
import java.util.LinkedList;

/**
 * Cette classe permet de générer un algorithme de diffusion atomique.
 * Cet algorithme simule le problème de lecteurs-rédacteur.
 *
 * @author Pierre-Louis OLLIVIER
 * @author Elina LEPETIT
 * @version 1
 */
public class DiffusionAtomique implements AlgoDiffusion {

    /**
     *
     */
    private GenerateurImpl generateur;

    /**
     *
     */
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
