public class DiffusionAtomique implements AlgoDiffusion {

    private GenerateurImpl generateur;

    @Override
    public void configure(GenerateurImpl generateur) {
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
}
