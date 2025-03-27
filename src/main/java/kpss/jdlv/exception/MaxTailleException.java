package kpss.jdlv.exception;

import kpss.jdlv.JeuDeLaVie;

/**
 * Exception: Taille maximale
 * @author Kilian POUSSE
 * @since 2025-03-27
 */
public class MaxTailleException extends Exception {
    
    /**
     * Constructeur
     */
    public MaxTailleException() {
        super("Valeur dépassée! La taille maximale de la grille est "+ JeuDeLaVie.MAX_TAILLE + ".");
    }

}
