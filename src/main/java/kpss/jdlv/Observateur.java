package kpss.jdlv;

import java.io.Serializable;

/**
 * Interface du Design Pattern <<Obeservateur>>.
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public interface Observateur extends Serializable {
    
    /**
     * Methode qui conciste à actualiser le jeu
     */
    public void actualise();

}
