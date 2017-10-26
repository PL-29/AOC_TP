package Metier;

import java.util.concurrent.Future;

public interface GenerateurAsync {
    Future<String> getValue();
}
