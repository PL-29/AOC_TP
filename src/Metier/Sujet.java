package Metier;

public interface Sujet {

    void attach(ObservateurGenerateur o);
    void detach(ObservateurGenerateur o);

}
