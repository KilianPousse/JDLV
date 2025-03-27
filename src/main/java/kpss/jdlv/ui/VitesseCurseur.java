package kpss.jdlv.ui;

import kpss.jdlv.App;
import kpss.jdlv.commande.CmdChangerVitesse;
import kpss.jdlv.commande.JDLVCommandeObj;

public class VitesseCurseur extends AbstractCurseur {

    private JDLVCommandeObj<Integer> commande;

    public VitesseCurseur(App app) {
        super(app, 0, 1000, app.getDelais(), "Delais", "ms");
        curseur.setMajorTickSpacing(200);
        curseur.setMinorTickSpacing(100);
        curseur.setPaintTicks(true);
        curseur.setPaintLabels(true);
        commande = new CmdChangerVitesse(app);
    }

    @Override
    public void executer() {
        commande.executer(getValeur());
    }
    
}
