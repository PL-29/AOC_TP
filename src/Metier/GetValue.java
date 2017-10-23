package Metier;

import java.util.concurrent.Callable;

public class GetValue implements Callable {
    private GenerateurImpl generateur;
    private ObservateurGenerateurAsync canal;

    public GetValue(GenerateurImpl generateur, ObservateurGenerateurAsync canal){
        this.generateur = generateur;
        this.canal = canal;
    }

    public String call() {
        String value = this.generateur.getValue(this.canal);
        return value;
    }
}