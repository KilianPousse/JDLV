package kpss.jdlv;

/**
 * Classe representant la commande lié aux cellules morte
 * @author Kilian POUSSE
 * @since 2025-03-11
 * @version 1.0
 */
public class CommandeMeurt extends Commande {
 
    /**
     * Constructeur de commande de mort
     * @param c Cellule morte
     */
    public CommandeMeurt(Cellule c) {
        cellule = c;
    }

    /**
     * Exercuter la commande
     */
    @Override
    public void executer() {
        cellule.meurt();
    }

}
