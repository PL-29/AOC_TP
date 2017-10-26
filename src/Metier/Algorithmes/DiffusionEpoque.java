package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateurAsync;

import java.time.LocalDateTime;

public class DiffusionEpoque implements AlgoDiffusion {

    private GenerateurImpl generateur;

    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
    }

    @Override
    public void execute() {
        for (ObservateurGenerateurAsync o : generateur.getCanauxObservers())
        {
            o.update(generateur);
        }
    }

    @Override
    public String gestionValue(ObservateurGenerateurAsync oCanal) {
        String tag = "@" + LocalDateTime.now().getHour() + ":"
                + LocalDateTime.now().getMinute() + ":"
                + LocalDateTime.now().getSecond();

        return this.generateur.getValue() + tag;
    }
}
