package Metier;

import java.util.concurrent.*;

public class Canal implements ObservateurGenerateurAsync, GenerateurAsync{

    // Proxy
    // private Afficheur afficheur;

    // Afficheur
    private ObservateurGenerateur observerAfficheur;

    //Proxy
    private GenerateurImpl generateur;


    public Canal(GenerateurImpl generateur){
        this.generateur = generateur;
    }

    public Future<Void> update(Generateur generateur){
//        System.out.println("Canal update");
//        // TODO spécification
//        this.generateur = (GenerateurImpl) generateur;
//
//        // TODO appeler la méthode update(subject:Generateur) de la classe Afficheur
//        this.observerAfficheur.update(generateur);
        Callable methodeInvocation = new Update(observerAfficheur, this);
       ExecutorService scheduler = Executors.newFixedThreadPool(1);
        return scheduler.submit(methodeInvocation);
    }


    public Future<String> getValue(){
        Callable methodeInvocation = new GetValue(this.generateur, this);
        ExecutorService scheduler = Executors.newFixedThreadPool(1);
        //scheduler.schedule(methodeInvocation, 1000, TimeUnit.MILLISECONDS);
        Future<String> future = scheduler.submit(methodeInvocation);
        return future;
        //return this.generateur.getValue(this);
    }

    public void attach(ObservateurGenerateur o){
        this.observerAfficheur = o;
    }

    public void detach(ObservateurGenerateur o){
        this.observerAfficheur = null;
    }
}