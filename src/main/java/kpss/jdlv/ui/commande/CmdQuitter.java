package kpss.jdlv.ui.commande;

import kpss.jdlv.App;

/**
 * Classe (Commande) qui permet de quitter le jeu
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdQuitter implements JDLVCommande {

    /** Application du jeu de la vie */
    private App app;

    /** 
     * Constructeur: Commande qui quitte un jeu
     * @param app Application du jeu de la vie
     */
    public CmdQuitter(App app) {
        this.app = app;
    }

    /**
     * Permet de quitter le jeu
     */
    @Override
    public void executer() {
        app.quitter();
    }
}
