package kpss.jdlv.exception;

/**
 * Exception: Erreur lors du chargement d'un jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-27
 */
public class LoadJDLVException extends Exception {
    /**
     * Constructeur
     * @param chemin Cemain vers le fichier
     */
    public LoadJDLVException(String chemin) {
        super("Erreur lors du chargement du jeu de la vie: '" + chemin + "'");
    }
}
