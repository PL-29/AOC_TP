package Metier;

import java.util.Random;
import java.util.concurrent.*;

public class Canal implements ObservateurGenerateurAsync, GenerateurAsync{

    // Afficheur
    private ObservateurGenerateur observerAfficheur;

    //Proxy
    private GenerateurImpl generateur;

    private ScheduledExecutorService scheduler;

    public Canal(GenerateurImpl generateur, ScheduledExecutorService scheduler){
        this.generateur = generateur;
        this.scheduler = scheduler;
    }

    public Future<Void> update(Generateur generateur){

        // this.observerAfficheur.update(generateur);
        Callable methodeInvocation = new Update(observerAfficheur, this);
        // ExecutorService scheduler = Executors.newFixedThreadPool(1);

        int delaiAleatoire = 900 + (int)Math.random() * 1500;
        return this.scheduler.schedule(methodeInvocation, delaiAleatoire ,TimeUnit.MILLISECONDS);
    }

    public Future<String> getValue(){

        Callable methodeInvocation = new GetValue(this.generateur, this);
        int delaiAleatoire = 900 + (int) Math.random() * 1500;
        return this.scheduler.schedule(methodeInvocation,delaiAleatoire,TimeUnit.MILLISECONDS);
        //return this.generateur.getValue(this);
    }

    public void attach(ObservateurGenerateur o){
        this.observerAfficheur = o;
    }

    public void detach(ObservateurGenerateur o){
        this.observerAfficheur = null;
    }
}