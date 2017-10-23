package Metier;

import java.util.concurrent.Future;

public interface GenerateurAsync {
    public Future<String> getValue();
}
