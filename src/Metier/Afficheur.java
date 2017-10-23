package Metier;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Afficheur implements ObservateurGenerateur{

    public void update(GenerateurAsync canalGenerateur){

        //TODO: Demander comment virer le this, inutile ici
        Future<String> future = canalGenerateur.getValue();
        try {
            String value = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        System.out.println("Afficheur " + value);
    }
}
