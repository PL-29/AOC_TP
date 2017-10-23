package Metier;

import java.util.concurrent.Future;

public class Afficheur implements ObservateurGenerateur{

    public void update(Generateur canalGenerateur){

        //TODO: Demander comment virer le this, inutile ici
//        Future<String> value = canalGenerateur.getValue();
//        System.out.println("Afficheur " + value);
    }
}
