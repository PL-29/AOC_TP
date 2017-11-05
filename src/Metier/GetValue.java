package Metier;

import java.util.concurrent.Callable;


public class GetValue implements Callable {
    private Generateur generateurImpl;
    private ObservateurGenerateurAsync canal;

    public GetValue(ObservateurGenerateurAsync canal, Generateur generateurImpl){
        this.canal = canal;
        this.generateurImpl = generateurImpl;
    }

    /**
     *
     * @return
     */
    public String call() {
        String value = this.generateurImpl.getValue(this.canal);
        return value;
    }
}