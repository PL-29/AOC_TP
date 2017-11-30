package Metier;

import Metier.Algorithmes.AlgoDiffusion;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * La classe GenerateurImpl permet la génération de valeur.
 * La génération choisie est celle de l'incrémentation de la valeur.
 * GenerateurImpl implemente Generateur et implicitement sujet, la classe est donc un observable.
 *
 *
 * @see Sujet
 * @see Generateur
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public class GenerateurImpl implements Generateur {

    /**
     * L'algorithme de diffusion choisi pour la génération de valeur
     */
    private AlgoDiffusion algo;

    /**
     * La valeur générée
     */
    private Integer value;

    /**
     * Le statut du générateur.
     * True le générateur est démarré, false le générateur est arrêté.
     */
    private boolean statut;

    /**
     * Le scheduler du générateur
     */
    private ScheduledExecutorService scheduler;

    /**
     * La liste des canaux (Observateur) qui observent la modification du générateur
     */
    private List<ObservateurGenerateurAsync> canauxObservers = new LinkedList<>();

    /**
     * Constructeur GenerateurImpl.
     *
     * @param scheduler Le scheduler
     */
    public GenerateurImpl(ScheduledExecutorService scheduler){
        this.scheduler = scheduler;
    }

    /**
     * Met à jour l'algorithme choisi pour le générateur de valeur
     * @param algo Le nouvel algorithme
     */
    public void setAlgo(AlgoDiffusion algo) {
        this.algo = algo;
    }

    /**
     * @see Sujet#attach(ObservateurGenerateurAsync)
     * @param observateur Le canal
     */
    @Override
    public void attach(ObservateurGenerateurAsync observateur) {
        this.canauxObservers.add(observateur);
    }

    /**
     * @see Sujet#detach(ObservateurGenerateurAsync)
     * @param observateur Le canal
     */
    @Override
    public void detach(ObservateurGenerateurAsync observateur) {
        this.canauxObservers.remove(observateur);
    }

    /**
     * Retourne une copie de la liste des cannaux
     * @return LinkedList de ObservateurGenerateurAsync
     */
    public LinkedList<ObservateurGenerateurAsync> getCanauxObservers() {
        return new LinkedList<>(this.canauxObservers);
    }

    /**
     * @see Generateur#getValue(ObservateurGenerateurAsync)
     * @param oCanal Le canal
     * @return la valeur du générateur traitée par l'algorithme
     */
    @Override
    public String getValue(ObservateurGenerateurAsync oCanal) {
        return algo.getValue(oCanal);
    }

    /**
     * @return la valeur sous la forme d'une chaine de caractère.
     */
    public String getValue(){
        return Integer.toString(this.value);
    }

    /**
     * La méthode setValue simule le changement de valeur du générateur.
     * La génération choisie est celle de l'incrémentation de l'attribut value.
     * A chaque changement de valeur on execute l'algorithme choisi.
     * Condition : Pour que l'ensemble de ces instructions soient executées il faut que le générateur soit démarré.
     */
    public void setValue(){
        // Si le générateur est en fonctionnement
        if(statut){
            this.value++;
            algo.execute();
        }
    }

    /**
     * Lancement du générateur.
     * L'algorithme défini est configuré,
     * et la génération de valeur est lancée selon un temps défini en milliseconds via un scheduler.
     */
    public void lancement(){
        this.statut = true;
        this.value = 0;
        algo.configure(this);

        this.scheduler.scheduleAtFixedRate(()-> {
            this.setValue();
        }, 1000,1000, TimeUnit.MILLISECONDS);
    }

    /**
     * Démarre le générateur
     */
    public void start() {
        this.statut = true;
    }

    /**
     * Arrête le générateur
     */
    public void stop(){
        this.statut = false;
    }
}
