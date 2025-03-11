package kpss.jdlv;

/**
 * Interface du Design Pattern <<Obsercable>> sur le jeu
 * @author Kilian POUSSE
 * @since 2025-03-11
 * @version 1.0
 */
public interface Observable {
    
    /**
     * Permet d'attacher un observateur.
     * @param o Observateur à attacher
     */
    public void attacheObservateur(Observateur o);

    /**
     * Permet de déttacher un observateur.
     * @param o Observateur à déttacher
     */
    public void dettacheObservateur(Observateur o);

    /**
     * Permet de notifer les observateurs
     */
    public void notifieObservateurs();
}
