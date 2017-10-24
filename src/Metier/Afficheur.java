package Metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Afficheur implements ObservateurGenerateur{

    private StringProperty value;

    public Afficheur(){
        this.value = new SimpleStringProperty();
    }

    public void update(GenerateurAsync canalGenerateur){

        //TODO: Demander comment virer le this, inutile ici
        Future<String> future = canalGenerateur.getValue();
        try {
            this.value.set(future.get());
            System.out.println("Afficheur " + value);
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
