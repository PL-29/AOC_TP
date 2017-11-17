package Metier;

/**
 * L'interface ObservateurGenerateur fait partie du design pattern Observer.
 * Cette interface est implémentée par toutes les classes qui souhaitent avoir le rôle d'observateur.
 * Ces classes seront notifiées à chaque changement d'état de la classe observée.
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public interface ObservateurGenerateur {
    /**
     * Méthode permettant d'être notifié du changement d'état du sujet passé en paramètre
     * @param sujet
     */
    void update(GenerateurAsync sujet);
}
