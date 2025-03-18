package kpss.jdlv.ui.commande;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import kpss.jdlv.*;

/**
 * Classe (Commande) qui permet de sauvegarder le jeu
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class CmdEnregistrer implements JDLVCommande {
    
    /** Application du jeu de la vie */
    private App app;

    /** 
     * Constructeur: Commande qui enregiste le jeu
     * @param app Application du jeu de la vie
     */
    public CmdEnregistrer(App app) {
        this.app = app;
    }

    /**
     * Permet de sauvegarder le jeu
     */
    @Override
    public void executer() {
        app.arret();
        String path = "";

        // Ouvrir fenetre de choix de chemin
        JFileChooser fenetreChoix = new JFileChooser();
        fenetreChoix.setDialogTitle("Sauvegarder le jeu");
        fenetreChoix.setSelectedFile(new File("save.jdlv"));

        // Mettre un filtre pour le fichier et son extension
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("Sauvegarde du JDLV (*.jdlv)", "jdlv");
        fenetreChoix.setAcceptAllFileFilterUsed(false);
        fenetreChoix.setFileFilter(filtre);

        int opt = fenetreChoix.showSaveDialog(app.getFenetre());

        if(opt == JFileChooser.APPROVE_OPTION) {
            path = fenetreChoix.getSelectedFile().getAbsolutePath();
        }

        if(path.equals("")) return;

        app.enregiste(path);
    }

}
