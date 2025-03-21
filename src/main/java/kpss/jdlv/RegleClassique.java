package kpss.jdlv;

/**
 * Classe des regles classique du jeu de la vie. C'est un visiteur qui visite les cellules de
 * magniere simple et classique (Visiteur, Singleton)
 * 
 * <p><b>Règles du jeu :</b></p>
 * <ul>
 *   <li><b>Solitude:</b> Une cellule vivante ayant moins de 2 voisines meurt.</li>
 *   <li><b>Surpopulation:</b> Une cellule vivante ayant plus de 3 voisines meurt.</li>
 *   <li><b>Naissance:</b> Une cellule morte ayant exactement 3 voisines devient vivante.</li>
 * </ul>
 * 
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public class RegleClassique extends Regle {
    
    /**
     * Constructeur d'un visiteur classique.
     * @param jeu jeu de la vie
     */
    public RegleClassique(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Creation d'une règle classique
     */
    public RegleClassique() {
        super();
    }

    /**
     * <p>Visite d'une cellule vivante</p>
     * <ul>
     *   <li><b>Solitude:</b> Une cellule vivante ayant moins de 2 voisines meurt.</li>
     *   <li><b>Surpopulation:</b> Une cellule vivante ayant plus de 3 voisines meurt.</li>
     * </ul>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellVivante(Cellule cellule) {
        int voisines = cellule.nombreVoisinesVivantes(jeu);

        if(voisines < 2 || voisines > 3) {
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    /**
     * <p>Visite d'une cellule morte</p>
     * <ul>
     *   <li><b>Naissance:</b> Une cellule morte ayant exactement 3 voisines devient vivante.</li>
     * </ul>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellMorte(Cellule cellule) {
        int voisines = cellule.nombreVoisinesVivantes(jeu);

        if(voisines == 3) {
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }

    /**
     * Representation en String de la regle
     * @return la representation
     */
    @Override
    public String toString() {
        return "Règle Classique";
    }
    
}
