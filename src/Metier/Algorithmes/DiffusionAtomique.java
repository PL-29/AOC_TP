package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateurAsync;
import java.util.LinkedList;
import java.util.List;

/**
 * DiffusionAtomique est l'implémentation de l'interface AlgoDiffusion.
 * Dans le design pattern Strategy, DiffusionAtomique correspond au rôle 'concrete strategy'.
 * Elle permet donc d'éxecuter un algorithme de diffusion atomique, c'est-à-dire
 * un algorithme qui diffuse une valeur selon un des problèmes de lecteurs-rédacteur.
 * Le redacteur (générateur) change la valeur, et il doit attendre que tous les lecteurs
 * (canaux) aient lu la valeur avant de changer de valeur
 *
 * @see AlgoDiffusion
 *
 * @author Pierre-Louis OLLIVIER
 * @author Elina LEPETIT
 * @version 1.0
 *
 */
public class DiffusionAtomique implements AlgoDiffusion {

    /**
     * Objet GenerateurImpl
     * @see AlgoDiffusion#configure(GenerateurImpl)
     */
    private GenerateurImpl generateur;

    /**
     * Liste d'ObservateurGenerateurAsync
     * En d'autre terme la liste contient les canaux.
     */
    private List<ObservateurGenerateurAsync> canaux;

    /**
     * @see AlgoDiffusion#configure(GenerateurImpl)
     * @param generateur
     * Dans le cas de diffusion atomique, lors de l'appel à configure,
     * l'algorithme initialise une liste (LinkedList) d'ObservateurGenerateurAsync
     */
    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
        this.canaux = new LinkedList<>();
    }

    /**
     * @see AlgoDiffusion#execute()
     * L'appel à execute arrête la création de valeur au niveau du générateur (rédacteur)
     * et les canaux (lecteurs) sont notifiés que l'algorithme a été executé.
     */
    @Override
    public void execute() {
        this.generateur.stop();
        // Récupération des canaux du générateur afin de pouvoir les notifier et avoir la liste des 'lecteurs'
        this.canaux = this.generateur.getCanauxObservers();
        // Notification des canaux
        for (ObservateurGenerateurAsync o : this.canaux) {
            o.update(this.generateur);
        }
    }

    /**
     * @see AlgoDiffusion#getValue(ObservateurGenerateurAsync)
     * Si la liste des canaux est vide le générateur redemarre afin de crée une nouvelle valeur.
     * @param oCanal
     * @return la valeur du générateur sous la forme d'une chaine de caractère.
     */
    @Override
    public String getValue(ObservateurGenerateurAsync oCanal) {
        // Suppression du canal dans la liste des canaux
        // Marque l'action 'Le lecteur a lu la valeur'
        this.canaux.remove(oCanal);

        if (this.canaux.isEmpty()) {
            this.generateur.start();
        }
        return this.generateur.getValue();
    }
}
