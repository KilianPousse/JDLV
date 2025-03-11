package kpss.jdlv;

/**
 * Classe représentant une cellule du JDLV.
 * @author Kilian POUSSE
 * @since 2025-03-11
 * @version 1.0
 */
public class Cellule {

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
        int res = -1;
        for(int dx=-1; dx<2; dx++) {
            for(int dy=-1; dy<2; dy++) {
                
                if(!jeu.estValide(x + dx, y + dy))
                    continue;
                
                res += jeu.getGrilleXY(x + dx, y + dy).estVivante() ? 1 : 0;
            }
        }
        return res;
    }
    
    /**
     * Visiteur de cellule (Visiteur)
     * @param visiteur Type de visite
     */
    public void accepte(Visiteur visiteur) {
        etat.accepte(visiteur, this);
    }
}
