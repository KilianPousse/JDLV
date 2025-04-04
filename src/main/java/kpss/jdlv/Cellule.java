package kpss.jdlv;

import java.awt.Color;
import java.io.Serializable;

/**
 * Classe représentant une cellule du JDLV.
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public class Cellule implements Serializable {

    /* ======= Variables d'instances ======= */

    /** 
     * Etat de la cellule
     *   - morte:   CelluleEtatMort
     *   - vivante: CelluleEtatVivant
     */
    private CelluleEtat etat;

    /** Coordonnée à la position X (absisse) */
    private int x;

    /** Coordonnée à la position Y (ordonnée) */
    private int y;



    /* =========== Constructeurs =========== */

    /**
     * Constructeur d'une cellule du JDLV.
     * @param x Coordonnée à la position X (absisse)
     * @param y Coordonnée à la position Y (ordonnée)
     * @param etat Etat de la cellule
     */
    public Cellule(int x, int y, CelluleEtat etat) {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }




    /* ========== Getter & Setter ========== */

    /**
     * Setter: Affectation de l'état de la cellule
     * @param etat Etat de la cellule
     */
    public void setEtat(CelluleEtat etat) {
        this.etat = etat;
    }

    /**
     * Getter: Récupération de l'état de la cellule
     * @return Etat de la cellule
     */
    public CelluleEtat getEtat() {
        return etat;
    }

    /**
     * Setter: Affectation de l'absisse
     * @param int Coordonnée à la position X (absisse)
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter: Récupération de l'absisse
     * @return Coordonnée à la position X (absisse)
     */
    public int getX() {
        return x;
    }

    /**
     * Setter: Affectation de l'ordonnée
     * @param int Coordonnée à la position Y (ordonnée)
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter: Récupération de l'ordonnée
     * @return Coordonnée à la position Y (ordonnée)
     */
    public int getY() {
        return y;
    }


    /* ======= Méthodes d'instance ========= */

    /**
     * Permet de faire vivre la cellule.
     */
    public void vit() {
        this.etat = etat.vit();
    }

    /**
     * Permet de tuer la cellule
     */
    public void meurt() {
        this.etat = etat.meurt();
    }

    /**
     * Permet de savoir si la cellule est vivante
     * @return Vrai si la cellule est vivante, Faux sinon
     */
    public boolean estVivante() {
        return etat.estVivante();
    }

    /**
     * Compte le nombre de voisins vivantes de la cellule
     * @param jeu Jeu auquelle fait parti la cellule
     * @return Le nombre de voisins vivantes
     */
    public int nombreVoisinesVivantes(JeuDeLaVie jeu) {
        int res = 0;

        for(int dx=-1; dx<=1; dx++) {
            for(int dy=-1; dy<=1; dy++) {
                if(dx==0 && dy==0) {
                    continue;
                }

                int voisinX = x + dx;
                int voisinY = y + dy;

                if(!jeu.estValide(voisinX, voisinY)) {
                    continue;
                }

                if(jeu.getGrilleXY(voisinX, voisinY).estVivante()) {
                    res++;
                }
            }
        }

        return res;
    }

    
    /**
     * Acces au rege du jeu (Visiteur)
     * @param regle Type de regle
     */
    public void accepte(Regle regle) {
        etat.accepte(regle, this);
    }

    /**
     * Representation en String
     * @return String equivalent
     */
    @Override
    public String toString() {
        return "<Cellule pos=(" + x + "," + y + ") etat='" + etat + "'>";
    }

    /**
     * Récupération de la couleur de la cellule
     * @param regle Regle du jeu
     * @return Couleur de la cellule
     */
    public Color getCouleur(Regle regle) {
        return etat.getCouleur(regle);
    }
}
