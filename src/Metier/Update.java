package Metier;

import java.util.concurrent.Callable;

/**
 * La classe Update est une tâche exécutée de manière asynchrone.
 * Celle-ci permet de mettre à jour un afficheur.
 * GenerateurImpl implemente Callable de la bibliothèque java.util.concurrent
 *
 * @see Callable
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public class Update implements Callable{

    /**
     * Le generateur d'où la valeur sera récupérée
     */
    private ObservateurGenerateur observateurAfficheur;

    /**
     * Le canal relié à l'afficheur
     */
    private GenerateurAsync canal;

    /**
     * Constructeur Update
     * @param observateurAfficheur L'afficheur
     * @param canal Le canal
     */
    public Update(ObservateurGenerateur observateurAfficheur, GenerateurAsync canal){
        this.observateurAfficheur = observateurAfficheur;
        this.canal = canal;

    }

    /**
     * Cette fonction retourne une valeur de type Void.
     * Ceci s'explique car la mise à jour ne renvoie jamais de valeur dans notre application.
     * @return null
     */
    public Void call(){
        this.observateurAfficheur.update(this.canal);
        return null;
    }
}
