public class Afficheur implements ObservateurGenerateur{

    public void update(Generateur canalGenerateur){

        int value = canalGenerateur.getValue();
        System.out.println("Afficheur " + value);
    }
}
