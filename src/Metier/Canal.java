package Metier;

import java.util.Random;
import java.util.concurrent.*;

public class Canal implements ObservateurGenerateurAsync, GenerateurAsync{

    // Afficheur
    private ObservateurGenerateur observerAfficheur;

    //Proxy
    private GenerateurImpl generateur;

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    public Canal(GenerateurImpl generateur){
        this.generateur = generateur;
    }

    public Future<Void> update(Generateur generateur){

        // this.observerAfficheur.update(generateur);
        Callable methodeInvocation = new Update(observerAfficheur, this);
        // ExecutorService scheduler = Executors.newFixedThreadPool(1);

        int delaiAleatoire = (int) Math.random() * 5;
        return this.scheduler.schedule(methodeInvocation, delaiAleatoire ,TimeUnit.SECONDS);
    }

    public Future<String> getValue(){

        Callable methodeInvocation = new GetValue(this.generateur, this);
        int delaiAleatoire = (int) Math.random() * 5;
        return scheduler.schedule(methodeInvocation,delaiAleatoire,TimeUnit.SECONDS);
        //return this.generateur.getValue(this);
    }

    public void attach(ObservateurGenerateur o){
        this.observerAfficheur = o;
    }

    public void detach(ObservateurGenerateur o){
        this.observerAfficheur = null;
    }
}