package Metier;

import java.util.concurrent.*;

public class Canal implements ObservateurGenerateurAsync, GenerateurAsync{

    // Attribut présent pour représenter le patron de conception proxy1
    private ObservateurGenerateur observateurAfficheur;

    // Attribut présent pour représenter le patron de conception proxy2
    private Generateur generateurImpl;

    // Dans le bus d'avoir un scheduler centraliser dans le main
    private ScheduledExecutorService scheduler;

    public Canal(GenerateurImpl generateur, ScheduledExecutorService scheduler){
        this.generateurImpl = generateur;
        this.scheduler = scheduler;
    }

    public Future<Void> update(Generateur generateur){

        Callable methodeInvocation = new Update(observateurAfficheur, this);
        int delaiAleatoire = 900 + (int)Math.random() * 1500;
        return this.scheduler.schedule(methodeInvocation, delaiAleatoire ,TimeUnit.MILLISECONDS);
    }

    public Future<String> getValue(){

        Callable methodeInvocation = new GetValue(this, this.generateurImpl);
        int delaiAleatoire = 900 + (int) Math.random() * 1500;
        return this.scheduler.schedule(methodeInvocation,delaiAleatoire,TimeUnit.MILLISECONDS);
    }

    public void attach(ObservateurGenerateur o){
        this.observateurAfficheur = o;
    }

    public void detach(ObservateurGenerateur o){
        this.observateurAfficheur = null;
    }
}