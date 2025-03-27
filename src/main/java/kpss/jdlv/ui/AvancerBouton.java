package kpss.jdlv.ui;

import kpss.jdlv.App;
import kpss.jdlv.commande.CmdAvancerGen;
import kpss.jdlv.commande.JDLVCommande;

/**
 * Bouton qui permet d'avancer d'une génération
 * @author Kilian POUSSE
 * @since 2025-03-26
 * 
 */
public class AvancerBouton extends AbstractBouton {

    /** Commande a faire */
    private JDLVCommande commande;

    /**
     * Constructeur
     * @param app Application du JDLV
     */
    public AvancerBouton(App app) {
        super(app, "Avancer d'une génération");
        commande = new CmdAvancerGen(app);
        bouton.setEnabled(false);
    }

    /**
     * Actualise le bouton (son affichage)
     */
    @Override
    public void actualise() {
        if(app.estVide()) {
            bouton.setEnabled(false);
            return;
        }
        bouton.setEnabled(app.estEnPause());
        bouton.repaint();
    }

    /**
     * Avancer d'une generation
     */
    @Override
    public void executer() {
        commande.executer();
    }

}
