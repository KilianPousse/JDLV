package kpss.jdlv.commande;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import kpss.jdlv.App;

/**
 * Classe (Commande) qui permet de charger le jeu
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdCharger implements JDLVCommande {

    /** Application du jeu de la vie */
    private App app;

    /** 
     * Constructeur: Commande qui charge un jeu
     * @param app Application du jeu de la vie
     */
    public CmdCharger(App app) {
        this.app = app;
    }

    /**
     * Permet de charger un jeu
     */
    @Override
    public void executer() {
        app.arret();

        String path = "";

        // Ouvrir fenetre de choix de fichier
        JFileChooser fenetreChoix = new JFileChooser();
        fenetreChoix.setDialogTitle("Chargement d'un jeu");

        // Ajouter un filtre pour l'extenion du fichier
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("Sauvegarde du JDLV (*.jdlv)", "jdlv");
        fenetreChoix.setAcceptAllFileFilterUsed(false);
        fenetreChoix.setFileFilter(filtre);

        int opt = fenetreChoix.showOpenDialog(app.getFenetre());

        if(opt == JFileChooser.APPROVE_OPTION) {
            path = fenetreChoix.getSelectedFile().getAbsolutePath();
        }

        if(path.equals("")) return;
        
        app.charge(path);
    }
}
