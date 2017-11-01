package Metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Joue le rôle de model dans MVC avec l'interface utilisateur
 */
public class Afficheur implements ObservateurGenerateur{

    private StringProperty value;

    public Afficheur(){
        this.value = new SimpleStringProperty();
    }

    public void update(GenerateurAsync canalGenerateur){
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

    public StringProperty getProperty(){
        return this.value;

    }
}
