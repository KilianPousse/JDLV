package kpss.jdlv.ui;

import kpss.jdlv.App;
import kpss.jdlv.commande.CmdDemarrerArreter;
import kpss.jdlv.commande.JDLVCommande;

/**
 * Etat du bouton de démarrage = Arrêt
 * @author Kilian POUSSE
 * @since 2025-03-26
 */
public class DemarrageBoutonEtatStop implements DemarrageBoutonEtat {
    
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
    public DemarrageBoutonEtatStop(DemarrageBouton bouton, App app) {
        this.bouton = bouton;
        this.bouton.setText("Démarrer");
        this.app = app;
        commande = new CmdDemarrerArreter(app);
    }

    /**
     * Execution de l'action lors que le bouton est actionné
     */
    @Override
    public void executer() {
        commande.executer();
        bouton.setEtat(new DemarrageBoutonEtatStart(bouton, app));
    }
}
