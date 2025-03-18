package kpss.jdlv;

import java.io.Serializable;

/**
 * Classe des regle du jeu de la vie (Visiteur)
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public abstract class Regle implements Serializable {
    
    /** Jeu de la vie */
    protected JeuDeLaVie jeu;

    /**
     * Constructeur de Visiteur
     * @param jeu Jeu de la vie
     */
    public Regle(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    /**
     * Constructeur d'une regle sans jeu
     */
    public Regle() {
        jeu = null;
    }

    /**
     * Affectation d'un jeu à la règle
     */
    public void setJeu(JeuDeLaVie jeu) {
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
