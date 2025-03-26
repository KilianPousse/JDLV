package kpss.jdlv.ui;

import kpss.jdlv.App;
import kpss.jdlv.ui.commande.CmdZoomer;
import kpss.jdlv.ui.commande.JDLVCommandeObj;

public class ZoomCurseur extends AbstractCurseur {

    private JDLVCommandeObj<Integer> commande;

    public ZoomCurseur(App app) {
        super(app, 0, App.MAX_ZOOM, 100, "Zoom", "%");
        curseur.setMajorTickSpacing(50);
        curseur.setMinorTickSpacing(25);
        curseur.setPaintTicks(true);
        curseur.setPaintLabels(false);
        commande = new CmdZoomer(app);
    }

    @Override
    public void actualise() {
        super.actualise();
        curseur.setValue(app.getUi().getZoom());
    }

    @Override
    public void executer() {
        commande.executer(getValeur());
    }
    
}
