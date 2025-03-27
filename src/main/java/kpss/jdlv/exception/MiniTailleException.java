package kpss.jdlv.exception;

import kpss.jdlv.JeuDeLaVie;

/**
 * Exception: Taille miniamale de la grille
 * @author Kilian POUSSE
 * @since 2025-03-27
 */
public class MiniTailleException extends Exception {
    
    /**
     * Constructeur
     */
    public MiniTailleException() {
        super("Valeur dépassée! La taille minimale de la grille est "+ JeuDeLaVie.MINI_TAILLE + ".");
    }

}
