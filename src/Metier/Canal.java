package Metier;

import java.util.concurrent.*;

/**
 * La classe Canal joue le rôle "proxy" dans le design pattern Proxy.
 * Elle fait le lien entre le générateur et l'afficheur.
 *
 * @see ObservateurGenerateurAsync
 * @see GenerateurAsync
 *
 * @author Pierre-Louis Ollivier
 * @author Elina Lepetit
 * @version 1.0
 *
 */
public class Canal implements ObservateurGenerateurAsync, GenerateurAsync {

    /**
     * L'afficheur qui observe le canal.
     * Cet attribut est présent pour représenter le patron de conception proxy1
     */
    private ObservateurGenerateur observateurAfficheur;

    /**
     * Le générateur observé par le canal.
     * Cet attribut est présent pour représenter le patron de conception proxy2
     */
    private Generateur generateurImpl;

    /**
     * Le scheduler central
     */
    private ScheduledExecutorService scheduler;

    /**
     * Constructeur Canal
     * @param generateur
     * @param scheduler
     */
    public Canal(GenerateurImpl generateur, ScheduledExecutorService scheduler){
        this.generateurImpl = generateur;
        this.scheduler = scheduler;
    }

    /**
     * @see ObservateurGenerateurAsync#update()
     * @return un future de type void. (Il n'est pas utilisé par la suite)
     */
    @Override
    public Future<Void> update(){

        Callable methodeInvocation = new Update(observateurAfficheur, this);
        int delaiAleatoire = 900 + (int)Math.random() * 1500;
        return this.scheduler.schedule(methodeInvocation, delaiAleatoire ,TimeUnit.MILLISECONDS);
    }

    /**
     * @see GenerateurAsync#getValue()
     * @return la valeur produite par le générateur
     */
    @Override
    public Future<String> getValue(){

        Callable methodeInvocation = new GetValue(this, this.generateurImpl);
        int delaiAleatoire = 900 + (int) Math.random() * 1500;
        return this.scheduler.schedule(methodeInvocation,delaiAleatoire,TimeUnit.MILLISECONDS);
    }

    /**
     * Instancie l'attribut observateurAfficheur qui observe la classe canal grâce à l'objet passé en paramètre.
     * Cet attribut correspond à un afficheur.
     * @param o
     */
    public void attach(ObservateurGenerateur o){
        this.observateurAfficheur = o;
    }

    /**
     * Met à null l'attribut observateurAfficheur qui observe la classe canal.
     * @param o
     */
    public void detach(ObservateurGenerateur o){
        this.observateurAfficheur = null;
    }
}