import java.util.HashSet;
import java.util.Set;

public class DiffusionAtomique implements AlgoDiffusion {

    private GenerateurImpl generateur;
    private Set<ObservateurGenerateur> afficheurs;

    @Override
    public void configure(GenerateurImpl generateur) {
        this.afficheurs = new HashSet<>();
        this.generateur = generateur;
    }

    @Override
    public void execute() {
        // update
        System.out.println("Diffusion execute ");
        for (ObservateurGenerateur o : generateur.getCanauxObservers())
        {
            o.update(generateur);
        }
    }

    public void addAfficheurs(ObservateurGenerateur oAfficheur){
        afficheurs.add(oAfficheur);
    }
}
