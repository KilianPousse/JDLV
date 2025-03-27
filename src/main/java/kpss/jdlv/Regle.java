package kpss.jdlv;

import java.io.Serializable;

/**
 * Classe des regle du jeu de la vie (Visiteur)
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public interface Regle extends Serializable {
    
    /**
     * Affectation d'un jeu à la règle
     */
    public void setJeu(JeuDeLaVie jeu);

    /**
     * Récupération du nom de la regle
     * @return nom de la regle
     */
    public String getNom();

    /**
     * Visite d'une cellule vivante
     * @param cellule Cellule vivante
     */ 
    public void visiteCellVivante(Cellule cellule);

    /**
     * Visite d'une cellule morte
     * @param cellule Cellule morte
     */ 
    public void visiteCellMorte(Cellule cellule);

}
