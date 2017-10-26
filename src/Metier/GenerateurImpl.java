package Metier;

import Metier.Algorithmes.AlgoDiffusion;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GenerateurImpl implements Generateur {

    private AlgoDiffusion algo;

    private Integer value;

    private boolean statut;

    private ScheduledExecutorService scheduler;

    // Liste des canaux (Observeur) Observe la modification du générateur
    private List<ObservateurGenerateurAsync> canauxObservers = new LinkedList<>();

    public GenerateurImpl(ScheduledExecutorService scheduler){
        this.scheduler = scheduler;
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
        return algo.gestionValue(oCanal);
    }

    public String getValue(){
        return Integer.toString(this.value);
    }

    // Simule le changement de valeur
    public void setValue(){
        if(statut){
            this.value++;
            algo.execute();
        }
    }

    public void lancement(){
        this.statut = true;
        this.value = 0;
        algo.configure(this);

        this.scheduler.scheduleAtFixedRate(()-> {
            this.setValue();
        }, 1000,1000, TimeUnit.MILLISECONDS);
    }

    public void start() { this.statut = true; }

    public void stop(){
        this.statut = false;
    }
}
