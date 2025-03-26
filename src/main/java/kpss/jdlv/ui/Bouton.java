package kpss.jdlv.ui;

import javax.swing.JButton;

import kpss.jdlv.Observateur;

/**
 * Bouton de l'application du jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-26
 */
public interface Bouton extends Observateur {
    
    /**
     * Permet d'executer l'action quand le bouton est pressé
     */
    public void executer();

    /**
     * Setter: Text dans le bouton
     * @param text Text dans le bouton
     */
    public void setText(String text);

    /**
     * Getter: du composant Swing
     * @return
     */
    public JButton getBouton();

}
