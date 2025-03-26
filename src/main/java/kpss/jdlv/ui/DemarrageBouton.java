package kpss.jdlv.ui;

import kpss.jdlv.*;

/**
 * Classe représentant le bouton de démarrage/arrêt
 * @author Kilian POUSSE
 * @since 2025-03-26
 * @see AbstractBouton
 */
public class DemarrageBouton extends AbstractBouton {
    
    /** Etat du bouton de démarrage */
    private DemarrageBoutonEtat etat;

    /**
     * Constructeur
     * @param app Application du JDLV
     */
    public DemarrageBouton(App app) {
        super(app, "");
        this.etat = new DemarrageBoutonEtatStop(this, app);
    }

    /**
     * Setter de l'état du bouton
     * @param etat état du bouton (arrêté/démarré)
     */
    public void setEtat(DemarrageBoutonEtat etat) {
        this.etat = etat;
    }

    /**
     * Execute l'action du bouton
     */
    public void executer() {
        etat.executer();
    }

}
