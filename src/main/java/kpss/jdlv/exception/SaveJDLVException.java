package kpss.jdlv.exception;

/**
 * Exception: Erreur lors de l'enregistrement d'un jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-27
 */
public class SaveJDLVException extends Exception {
    /**
     * Constructeur
     * @param chemin Cemain vers le fichier
     */
    public SaveJDLVException(String chemin) {
        super("Erreur lors de l'enregistrement du jeu de la vie: '" + chemin + "'");
    }
}
