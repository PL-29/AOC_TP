package Metier;

import java.util.concurrent.Future;

public interface ObservateurGenerateurAsync {
    Future<Void> update(Generateur generateur);
}
