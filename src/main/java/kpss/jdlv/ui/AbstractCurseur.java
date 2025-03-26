package kpss.jdlv.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

import kpss.jdlv.*;

/**
 * Abstract curseur de l'application du JDLV
 * @author Kilian POUSSE
 * @since 2025-03-26
 */
public abstract class AbstractCurseur implements Curseur {
    
    /** Curseur */
    protected JSlider curseur;

    /** Bordure */
    protected TitledBorder bordure;

    /** Application du JDLV */
    protected App app;

    /** Nom de la valeur */
    protected String nom;

    /** Fin de la valeur */
    protected String fin;

    /**
     * Construteur
     * @param app Application du JDLV
     * @param min Valeur minimal du curseur
     * @param max Valeur maximal du curseur
     * @param valeur Valeur du curseur
     * @param nom Nom de la valeur
     * @param fin Fin de la valeur
     */
    public AbstractCurseur(App app, int min, int max, int valeur, String nom, String fin) {
        this.app = app;
        this.nom = nom;
        this.fin = fin;
        curseur = new JSlider(min, max, valeur);
        bordure = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                nom + ": " + valeur + " " + fin
        );
        curseur.addChangeListener((e) -> executer());
        curseur.setBorder(bordure);
    }

    /**
     * Setter: Valeur du curseur
     * @param valeur Valeur du curseur
     */
    public void setValeur(int valeur) {
        curseur.setValue(valeur);
    }

    /**
     * Getter: Valeur du curseur
     * @return Valeur du curseur
     */
    public int getValeur() {
        return curseur.getValue();
    }

    /**
     * Actualisation du curseur
     */
    @Override
    public void actualise() {
        bordure.setTitle(nom + ": " + getValeur() + " " + fin);
        curseur.setEnabled(!app.estVide());
        curseur.repaint();
    }

    /**
     * Retourne le panel
     * @return panel
     */
    @Override
    public JSlider getCurseur() {
        return curseur;
    }


}
