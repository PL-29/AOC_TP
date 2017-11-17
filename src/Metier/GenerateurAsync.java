package Metier;

import java.util.concurrent.Future;

/**
 * L'interface GenerateurAsync joue le rôle de service dans le design pattern Active Object.
 *
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public interface GenerateurAsync {
    /**
     * Retourne la valeur sous la forme d'un objet de type Future contenant une chaine de caractères
     * @return value
     */
    Future<String> getValue();
}
