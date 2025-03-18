package kpss.jdlv.ui.commande;

/**
 * Interface representant les commandes possibles present
 * dans l'application jeu de la vie.
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public interface JDLVCommandeObj<T> {
    /**
     * Permet d'executer la commande
     */
    public void executer(T obj);
}
