package kpss.jdlv;

import java.awt.Color;

/**
 * Classe abstraite des regle du jeu de la vie (Visiteur)
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public abstract class AbstractRegle implements Regle {
    
    /** Jeu de la vie */
    protected JeuDeLaVie jeu;

    /** Nom de la regle du jeu de la vie */
    private String nom;

    /** Couleur de la cellule morte */
    private Color couleurMorte = Color.WHITE;

    /** Couleur de la cellule vivante */
    private Color couleurVivante = Color.BLACK;

    /**
     * Constructeur d'une regle sans jeu
     * @param nom Nom de la regle
     * @param couleurVivante Couleur de la cellule vivante
     * @param couleurMorte Couleur de la cellule morte
     */
    public AbstractRegle(String nom, Color couleurVivante, Color couleurMorte) {
        jeu = null;
        this.nom = nom;
        this.couleurVivante = couleurVivante;
        this.couleurMorte = couleurMorte;
    }

    /**
     * Constructeur d'une regle sans jeu
     * @param nom Nom de la regle
     */
    public AbstractRegle(String nom) {
        this(nom, Color.BLACK, Color.WHITE);
    }

    /**
     * Affectation d'un jeu à la règle
     */
    @Override
    public void setJeu(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    /**
     * Recuperation du nom de la regle
     * @return nom de la regle
     */
    @Override
    public String getNom() {
        return nom;
    }
    
    /**
     * Representation en String de la regle
     * @return la representation
     */
    @Override
    public String toString() {
        return nom;
    }

    /**
     * Récupération de la couleur de la cellule morte
     * @return couleur de la cellule morte
     */
    @Override
    public Color getCouleurVivante() {
        return couleurVivante;
    }

    /**
     * Récupération de la couleur de la cellule vivante
     * @return couleur de la cellule vivante
     */
    @Override
    public Color getCouleurMorte() {
        return couleurMorte;
    }

    /**
     * Récupération de la couleur du graphique
     * @return couleur du graphique
     */
    @Override
    public Color getCouleurGraph() {
        return couleurVivante;
    }

}
