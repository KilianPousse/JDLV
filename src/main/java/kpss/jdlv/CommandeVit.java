package kpss.jdlv;

/**
 * Classe representant la commande lié aux cellules vivante
 * @author Kilian POUSSE
 * @since 2025-03-11
 * @version 1.0
 */
public class CommandeVit extends Commande {
 
    /**
     * Constructeur de commande de vivant
     * @param c Cellule vivante
     */
    public CommandeVit(Cellule c) {
        cellule = c;
    }

    /**
     * Exercuter la commande
     */
    @Override
    public void executer() {
        cellule.vit();
    }

}
