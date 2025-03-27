package kpss.jdlv.commande;

import kpss.jdlv.App;

/**
 * Classe (Commande) qui permet de zoomer
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdZoomer implements JDLVCommandeObj<Integer> {

    /** Application du jeu de la vie */
    private App app;

    /** 
     * Constructeur: Commande qui quitte un jeu
     * @param app Application du jeu de la vie
     */
    public CmdZoomer(App app) {
        this.app = app;
    }

    /**
     * Permet de quitter le jeu
     * @param zoom nouveau zoom
     */
    @Override
    public void executer(Integer zoom) {
        app.getUi().zoomer(zoom, app);
        app.notifieObservateurs();
    }
}
