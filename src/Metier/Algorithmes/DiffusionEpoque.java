package Metier.Algorithmes;

import Metier.GenerateurImpl;
import Metier.ObservateurGenerateurAsync;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DiffusionEpoque est l'implémentation de l'interface AlgoDiffusion.
 * Dans le design pattern Strategy, DiffusionEpoque correspond au rôle 'concrete strategy'.
 * Elle permet donc de d'éxecuter un algorithme de diffusion epoque, c'est-à-dire
 * un algorithme diffuse une valeur estampillée, la valeur générée suivie d'un tag (l'heure)
 * afin de pouvoir identifier la valeur la plus récente.
 * On ne connait pas la valeur courrante mais on regarde celle qui à le temps le plus récent.
 *
 * @see AlgoDiffusion
 *
 * @author Pierre-Louis OLLIVIER
 * @author Elina LEPETIT
 * @version 1.0
 *
 */
public class DiffusionEpoque implements AlgoDiffusion {

    /**
     * Objet GenerateurImpl
     * @see AlgoDiffusion#configure(GenerateurImpl)
     */
    private GenerateurImpl generateur;

    /**
     * @see AlgoDiffusion#configure(GenerateurImpl)
     * @param generateur Le générateur associé
     */
    @Override
    public void configure(GenerateurImpl generateur) {
        this.generateur = generateur;
    }

    /**
     * @see AlgoDiffusion#execute()
     * L'appel à execute notifie les canaux que l'algorithme a été executé.
     */
    @Override
    public void execute() {
        for (ObservateurGenerateurAsync o : generateur.getCanauxObservers()) {
            o.update(generateur);
        }
    }

    /**
     * @see AlgoDiffusion#getValue(ObservateurGenerateurAsync)
     * Rajoute à la valeur créée par le générateur un tag sous la forme : @hh:mm:ss
     * @param oCanal Le canal qui a fait l'appel à getValue du générateur
     * @return la valeur du générateur modifiée par l'algo sous la forme d'une chaine de caractère.
     */
    @Override
    public String getValue(ObservateurGenerateurAsync oCanal) {

        String tag =  "@" + new SimpleDateFormat("HH:mm:ss").format(new Date());
        return this.generateur.getValue() + tag;
    }
}
