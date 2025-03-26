package kpss.jdlv.ui;

import kpss.jdlv.*;
import kpss.jdlv.ui.commande.CmdSelectionRegle;
import kpss.jdlv.ui.commande.JDLVCommandeObj;

public class RegleChoix extends AbstractChoix<Regle> {

    private JDLVCommandeObj<Regle> commande;

    public RegleChoix(App app) {
        super(app, app.getRegles(), app.getJeu().getRegle());
        commande = new CmdSelectionRegle(app);
    }

    @Override
    public void executer() {
        commande.executer(getSelection());
    }

    @Override
    public void actualise() {
        if(app.estVide()) {
            choix.setEnabled(false);
            return;
        } 
        choix.setEnabled(app.estEnPause());
    }
    
}
