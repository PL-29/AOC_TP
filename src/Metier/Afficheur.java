package Metier;

public class Afficheur implements ObservateurGenerateur{

    public void update(Generateur canalGenerateur){

        //TODO: Demander comment virer le this, inutile ici
        int value = canalGenerateur.getValue(this);
        System.out.println("Afficheur " + value);
    }
}
