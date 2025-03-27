package kpss.jdlv.commande;

import java.awt.Desktop;
import java.net.URI;
import javax.swing.JOptionPane;

import kpss.jdlv.App;
import kpss.jdlv.FenetreErreur;

/**
 * Commande permettant d'ouvrir un lien
 * @author Kilian POUSSE
 * @since 2025-03-27
 */
public class CmdOuvrirUrl implements JDLVCommandeObj<String> {

    /** Application du jeu de la vie */
    private App app;

    /**
     * Constructeur
     * @param app Application du jeu de la vie
     */
    public CmdOuvrirUrl(App app) {
        this.app = app;
    }

    /**
     * Execution de la commande
     */
    @Override
    public void executer(String lien) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(lien));
                System.out.println("lien: "+ lien);
            } else {
                JOptionPane.showMessageDialog(null, "Navigation non supportée.");
            }
        }
        catch (Exception e) {
            FenetreErreur.ouvrir(app, e);
        }
    }
    
}
