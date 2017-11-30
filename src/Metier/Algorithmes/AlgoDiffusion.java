package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateurAsync;

/**
 * L'interface AlgoDiffusion, correspond au rôle "strategy" du design pattern Strategy.
 * L'implémentation de cette interface permet la mise en place d'un ou plusieurs algorithme(s)
 * via une configuration et une execution.
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public interface AlgoDiffusion {

    /**
     * Configuration / Paramétrage de l'algorithme
     * Dans le cas de AlgoDiffusion la configuration doit au minimum
     * affecter le paramètre de type GenerateurImpl à un attribut de la classe.
     * @param generateur Le générateur associé
     */
    void configure(GenerateurImpl generateur);

    /**
     * Execute est la méthode qui nous permet d'appliquer notre stratégie,
     * donc d'executer notre algorithme.
     * l'appel à execute doit notifier à l'ensemble des canaux que l'algorithme a été executé.
     */
    void execute();

    /**
     * Renvoie la valeur créée par le générateur.
     * Des actions tierses peuvent être implémentées en fonction de l'algorithme défini.
     * Des modifications peuvent être effectuée sur la valeur du générateur en fonction de l'algorithme défini.
     * @param oCanal Le canal qui demande la récupération de la valeur du générateur.
     * @return la valeur sous la forme d'une chaine de caractère.
     */
    String getValue(ObservateurGenerateurAsync oCanal);
}
