package kpss.jdlv;

/**
 * Classe du Patern Visiteur (Visiteur)
 * @author Kilian POUSSE
 * @since 2025-03-11
 * @version 1.0
 */
public class Visiteur {
    
    /** Jeu de la vie */
    private JeuDeLaVie jeu;

    /**
     * Constructeur de Visiteur
     * @param jeu Jeu de la vie
     */
    public Visiteur(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    /**
     * Visite d'une cellule vivante
     * @param cellule Cellule vivante
     */ 
    public void visiteCellVivante(Cellule cellule) {

    }

    /**
     * Visite d'une cellule morte
     * @param cellule Cellule morte
     */ 
    public void visiteCellMorte(Cellule cellule) {

    }

}
