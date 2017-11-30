package Metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * La classe Afficheur permet l'affichage de valeur.
 * Cette classe joue le rôle de lecteur dans le problème de lecteur-rédacteur.
 * L'afficheur a également le rôle de Model dans le patron de conception MVC (où la View est l'interface utilisateur).
 * Afficheur implemente ObservateurGenerateur, la classe est donc un observateur.
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 * @see ObservateurGenerateur
 * 1
 */
public class Afficheur implements ObservateurGenerateur {

    /**
     * La valeur qui sera lue par l'Afficheur
     */
    private StringProperty value;

    /**
     * Constructeur Afficheur
     */
    public Afficheur() {
        this.value = new SimpleStringProperty();
    }

    /**
     * @param canalGenerateur Le canal
     * @see ObservateurGenerateur#update(GenerateurAsync)
     */
    @Override
    public void update(GenerateurAsync canalGenerateur) {
        Future<String> future = canalGenerateur.getValue();
        try {
            this.value.set(future.get());
            Logger.getGlobal().info("Afficheur " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return la valeur sous la forme d'un objet de type StringProperty
     */
    public StringProperty getProperty() {
        return this.value;
    }
}
