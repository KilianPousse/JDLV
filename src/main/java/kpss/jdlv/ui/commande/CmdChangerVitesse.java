package kpss.jdlv.ui.commande;

import kpss.jdlv.App;

/**
 * Classe (Commande) qui permet de changer la vitesse actualisation du jeu
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdChangerVitesse implements JDLVCommande {
    
    
    /** Application du jeu de la vie */
    private App app;

    /** Vitesse a donner à l'application */
    private int vitesse;

    /**
     * Constructeur: Construit une commande de changement la vitesse
     * actualisation du jeu
     * @param app Application du je de la vie
     * @param vitesse Vitesse d'actualisation de l'application
     */
    public CmdChangerVitesse(App app, int vitesse) {
        this.app = app;
        this.vitesse = vitesse;
    }

    /**
     * Permet de changer la vitesse actualisation du jeu
     */
    @Override
    public void executer() {
        app.setDelais(vitesse);
    }
    

}
