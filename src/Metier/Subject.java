package Metier;

public interface Subject {

    void attach(ObservateurGenerateur o);
    void detach(ObservateurGenerateur o);

}
