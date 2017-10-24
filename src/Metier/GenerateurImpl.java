package Metier;

import Metier.Algorithmes.AlgoDiffusion;
import Metier.Algorithmes.DiffusionAtomique;
import Metier.Algorithmes.DiffusionEpoque;
import Metier.Algorithmes.DiffusionSequentielle;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GenerateurImpl implements Generateur {

    private AlgoDiffusion algo;

    private Integer value;

    private boolean stop;

    // Liste des canaux (Observeur) Observe la modification du générateur
    private List<ObservateurGenerateurAsync> canauxObservers = new LinkedList<>();

    public GenerateurImpl(){}
    public GenerateurImpl(AlgoDiffusion algo){
        this.algo = algo;
    }

    public void setAlgo(AlgoDiffusion algo) {
        this.algo = algo;
    }

    @Override
    public void attach(ObservateurGenerateurAsync o) {
        this.canauxObservers.add(o);
    }

    @Override
    public void detach(ObservateurGenerateurAsync o) {
        this.canauxObservers.remove(o);
    }

    // Retourne une copie de la liste des cannaux
    public LinkedList<ObservateurGenerateurAsync> getCanauxObservers(){ return new LinkedList<>(this.canauxObservers); }

    @Override
    public String getValue(ObservateurGenerateurAsync oCanal) {

        // Diffusion atomique on supprime le canal qui a lu
        if(algo instanceof DiffusionAtomique){
            ((DiffusionAtomique) algo).removeCanaux(oCanal);
            return Integer.toString(this.value);
        }

        // Diffusion séquentielle on supprime le canaux qui a lu et on recupere la copie de la valeur
        else if(algo instanceof DiffusionSequentielle){
            ((DiffusionSequentielle) algo).removeCanaux(oCanal);
            return Integer.toString(((DiffusionSequentielle) algo).getCopyValue());
        }

        // Diffusion Epoque
        else {

            String tag = "@" + LocalDateTime.now().getHour() + ":"
                            + LocalDateTime.now().getMinute() + ":"
                            + LocalDateTime.now().getSecond();

            return this.value + tag;
        }
    }

    public int getValue(){
        return this.value;
    }

    // Simule le changement de valeur
    public void setValue(){
        this.value++;
        algo.execute();
    }

    public void start(){
        this.stop = false;
        this.value = 0;
        algo.configure(this);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            scheduler.scheduleAtFixedRate(()-> {

                // DiffusionAtomique - Set value que si tout le monde a lu
                if (algo instanceof DiffusionAtomique && ((DiffusionAtomique) algo).allReading()) {
                    this.setValue();
                }

                // DiffusionSequentielle - On set la value tout le temps
                if (algo instanceof DiffusionSequentielle || algo instanceof DiffusionEpoque) {
                    this.setValue();
                }

            }, 1000,1000, TimeUnit.MILLISECONDS);
    }

    public void stop(){
        this.stop = true;
    }
}
