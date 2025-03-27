package kpss.jdlv.ui;

import kpss.jdlv.*;
import kpss.jdlv.commande.*;

/**
 * Etat du bouton de démarrage = Démarrage
 * @author Kilian POUSSE
 * @since 2025-03-26
 */
public class DemarrageBoutonEtatStart implements DemarrageBoutonEtat {

    /** Bouton */
    private DemarrageBouton bouton;

    /** Commande a faire */
    private JDLVCommande commande;

    /** Application du JDLV */
    private App app;

    /**
     * Constructeur
     * @param bouton Bouton
     * @param app Application du JDLV
     */
    public DemarrageBoutonEtatStart(DemarrageBouton bouton, App app) {
        this.bouton = bouton;
        this.bouton.setText("Arrêter");
        this.app = app;
        commande = new CmdDemarrerArreter(app);
    }

    /**
     * Execution de l'action lors que le bouton est actionné
     */
    @Override
    public void executer() {
        commande.executer();
        bouton.setEtat(new DemarrageBoutonEtatStop(bouton, app));
    }
    
}
