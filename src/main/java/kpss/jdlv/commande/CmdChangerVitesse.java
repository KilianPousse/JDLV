package kpss.jdlv.commande;

import kpss.jdlv.App;

/**
 * Classe (Commande) qui permet de changer la vitesse actualisation du jeu
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdChangerVitesse implements JDLVCommandeObj<Integer> {
    
    
    /** Application du jeu de la vie */
    private App app;

    /**
     * Constructeur: Construit une commande de changement la vitesse
     * actualisation du jeu
     * @param app Application du je de la vie
     */
    public CmdChangerVitesse(App app) {
        this.app = app;
    }

    /**
     * Permet de changer la vitesse actualisation du jeu
     * @param vitesse Vitesse d'actualisation de l'application
     */
    @Override
    public void executer(Integer vitesse) {
        app.setDelais(vitesse);
    }
    

}
