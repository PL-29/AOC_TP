package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateurAsync;
import java.util.LinkedList;

/**
 * DiffusionSequentielle est l'implémentation de l'interface AlgoDiffusion.
 * Dans le design pattern Strategy, DiffusionSequentielle correspond au rôle 'concrete strategy'.
 * Elle permet donc d'éxecuter un algorithme de diffusion séquentielle, c'est-à-dire
 * un algorithme qui diffuse une valeur selon un des problèmes de lecteurs-rédacteur.
 * Le rédacteur (générateur) change la valeur sans se soucier des lecteurs.
 * Les lecteurs ont accès à une copie de la valeur, l'original peut être modifiée par le rédacteur.
 * Lors de la lecture de la copie, le rédacteur peut continuer à travailler sur l'original.
 * Il est à noter qu'il faut que tous les lecteurs aient lu la même valeur avant de lire une autre valeur.
 *
 * @see AlgoDiffusion
 *
 * @author Pierre-Louis OLLIVIER
 * @author Elina LEPETIT
 * @version 1.0
 *
 */
public class DiffusionSequentielle implements AlgoDiffusion {

    /**
     * Objet GenerateurImpl
     * @see AlgoDiffusion#configure(GenerateurImpl)
     */
    private GenerateurImpl generateur;

    /**
     * Liste d'ObservateurGenerateurAsync
     * En d'autre terme la liste contient les canaux.
     */
    private LinkedList<ObservateurGenerateurAsync> canaux;

    /**
     * Copie de la valeur du générateur (redacteur)
     * qui doit être lu par les canaux (lecteurs)
     */
    String copieValue;

    /**
     * @see AlgoDiffusion#configure(GenerateurImpl)
     * @param generateur
     * Dans le cas de diffusion séquentielle, lors de l'appel à configure,
     * l'algorithme initialise une liste (LinkedList) d'ObservateurGenerateurAsync
     */
    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
        this.canaux = new LinkedList<>();
    }

    /**
     * @see AlgoDiffusion#execute()
     * Si la liste des canaux (lecteurs) est vide on récupère la valeur courrante pour l'affecter à l'attribut 'copieValue'
     * et les canaux (lecteurs) sont notifiés que l'algorithme a été executé.
     */
    @Override
    public void execute() {
        // Si la liste des canaux est vide - c'est-à-dire si tout les canaux ont lu la valeur
        if(this.canaux.isEmpty()){
            // Récupération de la valeur courrante
            this.copieValue = this.generateur.getValue();
            this.canaux = generateur.getCanauxObservers();
            for (ObservateurGenerateurAsync o : this.canaux) {
                o.update(generateur);
            }
        }
    }

    /**
     * @see AlgoDiffusion#getValue(ObservateurGenerateurAsync)
     * @param oCanal Le canal qui a fait l'appel à getValue du générateur
     * @return la copie de la valeur du générateur sous la forme d'une chaine de caractère.
     */
    @Override
    public String getValue(ObservateurGenerateurAsync oCanal){
        // Suppression du canal dans la liste des canaux
        // Marque l'action 'Le lecteur a lu la valeur'
        this.canaux.remove(oCanal);
        return this.getCopyValue();
    }

    /**
     * La copie de la valeur du générateur sous la forme d'une chaine de caractère.
     * @return copieValue
     */
    public String getCopyValue(){
        return this.copieValue;
    }
}
