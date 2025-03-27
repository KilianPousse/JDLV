package kpss.jdlv.commande;

import kpss.jdlv.App;

/**
 * Classe (Commande) qui permet de faire avancer le jeu de la vie
 * d'une génération.
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdAvancerGen implements JDLVCommande {

    /** Application du jeu de la vie */
    private App app;

    /**
     * Constructeur: Construit une commande d'avancer de generation
     * @param app Application du je de la vie
     */
    public CmdAvancerGen(App app) {
        this.app = app;
    }

    /**
     * Permet d'avancer d'une generation
     */
    @Override
    public void executer() {
        app.actualise();
    }
    
}
