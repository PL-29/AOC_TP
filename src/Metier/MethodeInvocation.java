package Metier;

import java.util.concurrent.Callable;

public interface MethodeInvocation extends Callable {
    public String call();
}
