package kpss.jdlv;

/**
 * Classe des regles 'Day & Night' du jeu de la vie. C'est un visiteur qui visite les cellules de
 * magniere simple et classique (Visiteur, Singleton)
 * 
 * <p><b>Règles du jeu :</b></p>
 * <ul>
 *   <li><b>Jour:</b> Une cellule vivante survit à l'étape suivante si elle est entourée de 3, 4, 6, 7 ou 8 cellules vivantes.</li>
 *   <li><b>Nuit:</b> Une cellule morte y naît à l'étape suivante si elle est entourée de 3, 6, 7 ou 8 voisines vivantes.</li>
 * </ul>
 * 
 * @author Kilian POUSSE
 * @since 2025-03-27
 */
public class RegleDayNight extends Regle {
    
    /**
     * Constructeur d'un visiteur 'Day & Night'.
     * @param jeu jeu de la vie
     */
    public RegleDayNight(JeuDeLaVie jeu) {
        super(jeu);
    }
    
    
    /**
     * Constructeur d'un visiteur 'Day & Night'.
     */
    public RegleDayNight() {
        super();
    }
    

    /**
     * <p>Visite d'une cellule vivante</p>
     * <ul>
     *   <li><b>Jour:</b> Une cellule vivante survit à l'étape suivante si elle est entourée de 3, 4, 6, 7 ou 8 cellules vivantes.</li>
     * </ul>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellVivante(Cellule cellule) {
        int voisines = cellule.nombreVoisinesVivantes(jeu);

        if(!(voisines == 3 || voisines == 4 || voisines == 6 || voisines == 7 ||voisines == 8)) {
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    /**
     * <p>Visite d'une cellule morte</p>
     * <ul>
     *   <li><b>Nuit:</b> Une cellule morte y naît à l'étape suivante si elle est entourée de 3, 6, 7 ou 8 voisines vivantes.</li>
     * </ul>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellMorte(Cellule cellule) {
        int voisines = cellule.nombreVoisinesVivantes(jeu);

        if(voisines == 3 || voisines == 6 || voisines == 7 || voisines == 8) {
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }

    /**
     * Representation en String de la regle
     * @return la representation
     */
    @Override
    public String toString() {
        return "Day & Night";
    }



}
