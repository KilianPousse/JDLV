package kpss.jdlv.ui;

import javax.swing.JComboBox;

import kpss.jdlv.Observateur;

/**
 * Boite a choix du jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-26
 */
public interface Choix<T> extends Observateur {
    
    /**
     * Permet d'executer l'action quand un choix est fait
     */
    public void executer();

    /**
     * Getter: prenre le composant
     * @return composant
     */
    public JComboBox<T> getChoix();

    /**
     * Getter: Recuperation de l'objet selectionne
     * @return objet selectionne
     */
    public T getSelection();
}
