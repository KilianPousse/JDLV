package kpss.jdlv.ui.commande;

import kpss.jdlv.App;
import kpss.jdlv.Regle;

/**
 * Classe (Commande) qui permet de selectionner une nouvelle
 * regle du jeu.
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdSelectionRegle implements JDLVCommandeObj<Regle> {

    /** Application du jeu de la vie */
    private App app;

    /**
     * Constructeur: Construit une commande de selection une nouvelle regle du jeu
     * @param app Application du je de la vie
     */
    public CmdSelectionRegle(App app) {
        this.app = app;
    }

    /**
     * Permet de selectionner une nouvelle regle du jeu
     * @param regle Regle a appliquer eu jeu de la vie
     */
    @Override
    public void executer(Regle regle) {
        app.setRegle(regle);
    }
    

}
