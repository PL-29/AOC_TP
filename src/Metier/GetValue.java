package Metier;

import java.util.concurrent.Callable;

public class GetValue implements Callable {
    private GenerateurImpl generateur;
    private Canal canal;

    public GetValue(GenerateurImpl generateur, Canal canal){
        this.generateur = generateur;
        this.canal = canal;
    }

    public String call() {
        return this.generateur.getValue(this.canal);
    }
}