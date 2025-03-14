package kpss.jdlv.ui;

import javax.swing.*;
import kpss.jdlv.*;
import java.awt.*;

/**
 * Classe qui represente la barre du menu en haut.
 * @author Kilian POUSSE
 * @since 2025-03-14
 */
public class MenuBarre extends JMenuBar {
    
    /* ====== Variables d'instance ====== */

    /** Application */
    private App app;

    /** Menu Demarrage */
    public JMenuItem demarrage;

    /* ======= Constructeurs ======= */

    public MenuBarre(App app) {
        super();
        this.app = app;

        JMenu fichier = new JMenu("Fichier");

        JMenuItem nouveau = new JMenuItem("Nouveau");
        JMenuItem sauver = new JMenuItem("Enregister");
        JMenuItem charger = new JMenuItem("Charger");
        JMenuItem quitter = new JMenuItem("Quitter");

        quitter.addActionListener((e) -> app.close());

        fichier.add(nouveau);
        fichier.add(sauver);
        fichier.add(charger);
        fichier.addSeparator();
        fichier.add(quitter);

        add(fichier);


        JMenu controle = new JMenu("Contrôle");

        demarrage = new JMenuItem("Demarrage");

        demarrage.addActionListener((e) -> app.panneauControle.miseEnPause());

        controle.add(demarrage);
        add(controle);
    }

}
