package kpss.jdlv;

/**
 * Classe permettant de représenter un état de cellule morte.
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public class CelluleEtatMort implements CelluleEtat {

    /* ======= Variables d'instances ======= */

    /** Variable d'instance qui represente une instance de la classe (Singleton) */
    private static CelluleEtat instance;

    /* ======== Méthodes de classe ========= */

    /**
     * Recuperation d'une seule instance de la classe (Singleton)
     * @return L'unique instance de la classe
     */
    public static CelluleEtat getInstance() {
        if(CelluleEtatMort.instance == null) {
            CelluleEtatMort.instance = new CelluleEtatMort();
        }
        return CelluleEtatMort.instance;
    }



    /* =========== Constructeurs =========== */

    /**
     * Constructeur d'un état de cellule morte
     */
    private CelluleEtatMort() {/* ... */}


    /* ======= Méthodes d'instance ========= */

    /**
     * Permet de modifier l'état d'une cellule.
     * Cette méthode fait "vivre" une cellule.
     * @return Nouveau état de la cellule
     */
    @Override
    public CelluleEtat vit() {
        return CelluleEtatVivant.getInstance();
    }

    /**
     * Permet de modifier l'état d'une cellule.
     * Cette méthode "tue" une cellule.
     * @return Nouveau état de la cellule
     */
    @Override
    public CelluleEtat meurt() {
        return CelluleEtatMort.getInstance();
    }

    /**
     * Permet de savoir si une cellule est vivante.
     * @return faux étant donné que l'état est "mort"
     */
    @Override
    public boolean estVivante() {
        return false;
    }
    
    /**
     * Permet de visiter / actualiser une cellule morte
     * @param visiteur Type de tritement a reatilser
     * @param cellule Cellule qui subit le traitement
     */
    @Override
    public void accepte(Visiteur visiteur, Cellule cellule) {
        visiteur.visiteCellMorte(cellule);
    }

    /**
     * Representation en String
     * @return String equivalent
     */
    @Override
    public String toString() {
        return "morte";
    }
}
