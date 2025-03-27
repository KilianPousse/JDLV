package kpss.jdlv.ui;

import kpss.jdlv.*;
import kpss.jdlv.commande.CmdSelectionRegle;
import kpss.jdlv.commande.JDLVCommandeObj;

/**
 * Boite a choix des regles du jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-27
 */
public class RegleChoix extends AbstractChoix<Regle> {

    /** Commande a reatliser pour le choix */
    private JDLVCommandeObj<Regle> commande;

    /**
     * Constructeur de la boite a choix
     * @param app Application du jeu de la vie
     */
    public RegleChoix(App app) {
        super(app, app.getRegles(), app.getJeu().getRegle());
        commande = new CmdSelectionRegle(app);
    }

    /**
     * Execute la commande a realiser
     */
    @Override
    public void executer() {
        commande.executer(getSelection());
    }

    /**
     * Actualise la boite a choix des regles
     */
    @Override
    public void actualise() {
        if(app.estVide()) {
            choix.setEnabled(false);
            return;
        } 
        choix.setEnabled(app.estEnPause());
        choix.setSelectedItem(app.getJeu().getRegle());
    }
    
}
