package kpss.jdlv;

public class RegleStatique extends Regle {

    public RegleStatique(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * Creation d'une Regle Statique
     */
    public RegleStatique() {
        super();
    }

    @Override
    public String toString() {
        return "Règle Statique";
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
        
    }
    
}
