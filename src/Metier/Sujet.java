package Metier;

public interface Sujet {

    void attach(ObservateurGenerateurAsync o);
    void detach(ObservateurGenerateurAsync o);

}
