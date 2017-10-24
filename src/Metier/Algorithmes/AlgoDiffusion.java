package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateurAsync;

public interface AlgoDiffusion {
    void configure(GenerateurImpl generateur);
    void execute();
    String gestionValue(ObservateurGenerateurAsync oCanal);
}
