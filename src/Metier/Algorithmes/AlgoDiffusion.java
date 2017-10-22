package Metier.Algorithmes;

import Metier.GenerateurImpl;

public interface AlgoDiffusion {
    void configure(GenerateurImpl generateur);
    void execute();
}
