package kpss.jdlv.ui;

import javax.swing.JSlider;

import kpss.jdlv.Observateur;

/**
 * Curseur de l'application du jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-26
 */
public interface Curseur extends Observateur {

    /**
     * Setter: Valeur du curseur
     * @param valeur Valeur du curseur
     */
    public void setValeur(int valeur);

    /**
     * Getter: Valeur du curseur
     * @return Valeur du curseur
     */
    public int getValeur();

    /**
     * Action a executer quand la valeur du curseur
     * change
     */
    public void executer();

    /**
     * Retourne le curseur
     * @return curseur
     */
    public JSlider getCurseur();

} 