package kpss.jdlv.commande;

import kpss.jdlv.App;

/**
 * Classe (Commande) qui permet de arrêter le jeu si il est en cours d'execution,
 * ou de la lancer si il est a l'arrêt.
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdDemarrerArreter implements JDLVCommande {

    /** Application du jeu de la vie */
    private App app;

    /**
     * Constructeur: Construit une commande de demarrage/arret
     * @param app Application du je de la vie
     */
    public CmdDemarrerArreter(App app) {
        this.app = app;
    }

    /**
     * Permet de demarrage/arret le jeu
     */
    @Override
    public void executer() {
        if(app.estEnPause()) {
            app.demarre();
        }
        else {
            app.arret();
        }
    }
    
}
