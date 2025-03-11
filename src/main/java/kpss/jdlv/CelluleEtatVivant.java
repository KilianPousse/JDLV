package kpss.jdlv;

/**
 * Classe permettant de représenter un état de cellule vivante.
 * @author Kilian POUSSE
 * @since 2025-03-11
 * @version 1.0
 */
public class CelluleEtatVivant implements CelluleEtat {

    /* ======= Variables d'instances ======= */

    /** Variable d'instance qui represente une instance de la classe (Singleton) */
    private static CelluleEtat instance;

    /* ======== Méthodes de classe ========= */

    /**
     * Recuperation d'une seule instance de la classe (Singleton)
     * @return L'unique instance de la classe
     */
    public static CelluleEtat getInstance() {
        if(CelluleEtatVivant.instance == null) {
            CelluleEtatVivant.instance = new CelluleEtatVivant();
        }
        return CelluleEtatVivant.instance;
    }



    /* =========== Constructeurs =========== */

    /**
     * Constructeur d'un état de cellule vivante
     */
    private CelluleEtatVivant() {/* ... */}


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
     * @return vrai étant donné que l'état est "vivant"
     */
    @Override
    public boolean estVivante() {
        return true;
    }
   
}
