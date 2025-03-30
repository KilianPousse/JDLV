package kpss.jdlv;

import java.awt.Color;

/**
 * Classe des regles 'High Life' du jeu de la vie (Visiteur, Singleton)
 * 
 * <p><b>Règles du jeu :</b></p>
 * <ul>
 *   <li><b>Naissance:</b> Une cellule morte y naît à l'étape suivante si elle est entourée de 3 ou 6 voisines vivantes.</li>
 *   <li><b>Survit:</b> Une cellule vivante survit à l'étape suivante si elle est entourée de deux ou trois cellules vivantes.</li>
 * </ul>
 * 
 * @author Kilian POUSSE
 * @since 2025-03-27
 */
public class RegleHighLife extends AbstractRegle {
    
    
    /**
     * Constructeur d'un visiteur 'High Life'.
     */
    public RegleHighLife() {
        super("High Life", Color.GREEN, Color.WHITE);
    }
    

    /**
     * <p>Visite d'une cellule vivante</p>
     * <ul>
     *   <li><b>Survit:</b> Une cellule vivante survit à l'étape suivante si elle est entourée de deux ou trois cellules vivantes.</li>
     * </ul>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellVivante(Cellule cellule) {
        int voisines = cellule.nombreVoisinesVivantes(jeu);

        if(!(voisines == 2 || voisines == 3)) {
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    /**
     * <p>Visite d'une cellule morte</p>
     * <ul>
     *   <li><b>Naissance:</b> Une cellule morte y naît à l'étape suivante si elle est entourée de 3 ou 6 voisines vivantes.</li>
     * </ul>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellMorte(Cellule cellule) {
        int voisines = cellule.nombreVoisinesVivantes(jeu);

        if(voisines == 3 || voisines == 6) {
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }

}
