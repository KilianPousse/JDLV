package kpss.jdlv;

import java.awt.Color;

/**
 * Classe des règles 'Reverse Life' du jeu de la vie (Visiteur)
 * @author Kilian POUSSE
 * @since 2025-03-29
 */
public class RegleReverseLife extends AbstractRegle {

    /**
     * Création d'une règle Reverse Life
     */
    public RegleReverseLife() {
        super("Reverse Life", Color.RED, Color.WHITE);
    }

    /**
     * <p>Visite d'une cellule vivante</p>
     * <ul>
     *   <li><b>Mort:</b> Une cellule vivante meurt si elle n'a pas exactement 2 ou 3 voisins vivants.</li>
     * </ul>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellVivante(Cellule cellule) {
        int voisines = cellule.nombreVoisinesVivantes(jeu);

        if (voisines != 2 && voisines != 3) {
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    /**
     * <p>Visite d'une cellule morte</p>
     * <ul>
     *   <li><b>Naissance:</b> Une cellule morte devient vivante si elle a exactement 3 voisins vivants.</li>
     * </ul>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellMorte(Cellule cellule) {
        int voisines = cellule.nombreVoisinesVivantes(jeu);

        if (voisines == 3) {
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }
    
}
