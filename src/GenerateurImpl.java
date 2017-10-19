import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GenerateurImpl implements Generateur {

    private AlgoDiffusion algo;

    private Integer value;

    // Liste des canaux (Observeur) Observe la modification du générateur
    private List<ObservateurGenerateur> canauxObservers = new LinkedList<>();

    public GenerateurImpl(AlgoDiffusion algo){
        this.algo = algo;
    }

    @Override
    public void attach(ObservateurGenerateur o) {
        this.canauxObservers.add(o);
    }

    @Override
    public void detach(ObservateurGenerateur o) {
        this.canauxObservers.remove(o);
    }

    @Override
    public int getValue(ObservateurGenerateur oCanal) {
        //TODO : Rajouter switch pour chaque algo
//        switch(algo.getClass()){
//            case DiffusionAtomique.class :
//                break;
//        }
//        if(algo.getClass() == DiffusionAtomique.class){
//        }

        ((DiffusionAtomique) algo).removeCanaux(oCanal);
        return this.value;
    }

    public LinkedList<ObservateurGenerateur> getCanauxObservers(){
        return new LinkedList<>(this.canauxObservers);
    }

    // Simule le changement de valeur
    public void setValue(int value){
        this.value = value;
        algo.execute();
    }

    public void start(){
        algo.configure(this);
        for(int i = 0 ; i < 10 ; i++){
            // Set value que si tout le monde a lu
            if(((DiffusionAtomique) algo).allReading()) {
                this.setValue(i);
            }
        }
    }

    public void stop(){
    }
}
