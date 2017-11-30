package Metier;

/**
 * L'interface Generateur est un observable, il implémente l'interface sujet du design pattern Observer.
 * Elle joue le rôle de component dans le design pattern Proxy.
 *
 * @see Sujet
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public interface Generateur extends Sujet {
    /**
     * Retourne la valeur sous la forme d'une chaine de caractères
     * L'observateurGenerateur qui fait appel à getValue est passé en paramètre
     * @param observateurGenerateur Le canal
     * @return value
     */
    String getValue(ObservateurGenerateurAsync observateurGenerateur);
}
