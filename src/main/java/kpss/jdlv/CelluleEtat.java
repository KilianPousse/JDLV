package kpss.jdlv;

import java.io.Serializable;

/**
 * Interface qui représente l'état d'une cellule du Jeu De La Vie.
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public interface CelluleEtat extends Serializable {
    
    /**
     * Permet de modifier l'état d'une cellule.
     * Cette méthode fait "vivre" une cellule.
     * @return Nouveau état de la cellule
     */
    public CelluleEtat vit();
    
    /**
     * Permet de modifier l'état d'une cellule.
     * Cette méthode "tue" une cellule.
     * @return Nouveau état de la cellule
     */
    public CelluleEtat meurt();

    /**
     * Permet de savoir si une cellule est vivante.
     * @return Vrai si la cellule est vivante, Faux sinon
     */
    public boolean estVivante();

    /**
     * Permet de visiter / actualiser une cellule selon son etat
     * @param regle Type de tritement a reatilser selon la regle
     * @param cellule Cellule qui subit le traitement
     */
    public void accepte(Regle regle, Cellule cellule);
}