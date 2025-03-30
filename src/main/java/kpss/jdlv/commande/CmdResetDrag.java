package kpss.jdlv.commande;

import kpss.jdlv.App;

/**
 * Classe (Commande) qui permet de réinitialiser le décalage du rendu
 * @author Kilian POUSSE
 * @since 2025-03-30
 */
public class CmdResetDrag implements JDLVCommande {

    /** Application du jeu de la vie */
    private App app;

    /**
     * Constructeur: Commande qui réinitialise le décalage du rendu
     * @param app Application du jeu de la vie
     */
    public CmdResetDrag(App app) {
        this.app = app;
    }


    /**
     * Réinitialise le décalage du rendu
     */
    @Override
    public void executer() {
        app.getUi().resetDrag();
        app.getUi().repaint();
    }
    
}
