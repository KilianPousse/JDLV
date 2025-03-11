package kpss.jdlv;

/**
 * Classe du Patern Visiteur (Visiteur)
 * @author Kilian POUSSE
 * @since 2025-03-11
 * @version 1.0
 */
public class Visiteur {
    
    /** Jeu de la vie */
    protected JeuDeLaVie jeu;

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
     * @throws UnsupportedOperationException
     */ 
    public void visiteCellVivante(Cellule cellule) {
        throw new UnsupportedOperationException("Unimplemented method 'visiteCellVivante'");
    }

    /**
     * Visite d'une cellule morte
     * @param cellule Cellule morte
     * @throws UnsupportedOperationException
     */ 
    public void visiteCellMorte(Cellule cellule) {
        throw new UnsupportedOperationException("Unimplemented method 'visiteCellMorte'");
    }

}
