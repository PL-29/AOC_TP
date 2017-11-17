package Metier;

import java.util.concurrent.Callable;

/**
 * La classe GetValue est une tâche exécutée de manière asynchrone.
 * Celle-ci permet de récupérer la valeur contenue dans le Generateur.
 * GenerateurImpl implemente Callable de la bibliothèque java.util.concurrent
 *
 * @see Callable
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public class GetValue implements Callable {

    /**
     * Le generateur d'où la valeur sera récupérée
     */
    private Generateur generateurImpl;

    /**
     * Le canal relié à l'afficheur
     */
    private ObservateurGenerateurAsync canal;

    /**
     * Constructeur GetValue
     * @param canal
     * @param generateurImpl
     */
    public GetValue(ObservateurGenerateurAsync canal, Generateur generateurImpl){
        this.canal = canal;
        this.generateurImpl = generateurImpl;
    }

    /**
     * @return la value du generateur sous forme de chaîne de caractères
     */
    public String call() {
        String value = this.generateurImpl.getValue(this.canal);
        return value;
    }
}