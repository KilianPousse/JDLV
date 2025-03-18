package kpss.jdlv.ui.commande;

import kpss.jdlv.App;
import kpss.jdlv.Regle;

/**
 * Classe (Commande) qui permet de selectionner une nouvelle
 * regle du jeu.
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdSelectionRegle implements JDLVCommande {

    /** Application du jeu de la vie */
    private App app;

    /** Regle a appliquer eu jeu de la vie */
    private Regle regle;

    /**
     * Constructeur: Construit une commande de selection une nouvelle regle du jeu
     * @param app Application du je de la vie
     * @param regle Regle a appliquer eu jeu de la vie
     */
    public CmdSelectionRegle(App app, Regle regle) {
        this.app = app;
        this.regle = regle;
    }

    /**
     * Permet de selectionner une nouvelle regle du jeu
     */
    @Override
    public void executer() {
        app.setRegle(regle);
        
    }
    

}
