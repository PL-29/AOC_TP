package Metier;

import java.util.concurrent.Future;

/**
 * L'interface ObservateurGenerateurAsync fait partie du design pattern Observer.
 * Cette interface est implémentée par toutes les classes qui souhaitent avoir le rôle d'observateur.
 * Ces classes seront notifiées de manière asynchrone à chaque changement d'état de la classe observée.
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public interface ObservateurGenerateurAsync {
    /**
     * Méthode permettant d'être notifié de manière asynchrone du changement d'état de la classe observée
     * @return
     */
    Future<Void> update();
}
