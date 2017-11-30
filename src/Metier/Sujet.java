package Metier;

/**
 * L'interface Sujet plus communément appelée Observable, fait partie du design pattern Observer.
 * Cette interface est implémentée par toutes les classes désireuses de posséder des observateurs.
 * Les classes qui implémentent cette interface notifieront leur(s) observateur(s) à chaque changement d'état.
 *
 * @see Sujet
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public interface Sujet {

    /**
     * Ajoute l'observateur passé en paramètre à la liste des observateurs
     * @param o L'observareur générateur async
     */
    void attach(ObservateurGenerateurAsync o);

    /**
     * Supprime l'observateur passé en paramètre de la liste des observateurs
     * @param o L'observareur générateur async
     */
    void detach(ObservateurGenerateurAsync o);

}
