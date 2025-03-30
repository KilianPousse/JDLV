package kpss.jdlv.ui;

import kpss.jdlv.*;
import kpss.jdlv.commande.*;

/**
 * Bouton qui permet de réinitialiser le rendu
 * @author Kilian POUSSE
 * @since 2025-03-30
 */
public class ResetDragBouton extends AbstractBouton {

    /** Commande a faire */
    private JDLVCommande commande;

    /**
     * Constructeur
     * @param app Application du JDLV
     */
    public ResetDragBouton(App app) {
        super(app, "Centrer le rendu");
        commande = new CmdResetDrag(app);
        bouton.setEnabled(false);
    }

    /**
     * Réinitialise le rendu
     */
    @Override
    public void executer() {
        commande.executer();
    }
}
