package kpss.jdlv;

import javax.swing.*;

/**
 * Classe representant l'application
 * @author Kilian POUSSE
 * @since 2025-03-11
 * @version 1.0
 */
public class App extends JFrame {

    /* ======= Variables d'instances ======= */

    /** Jeu de la vie */
    private JeuDeLaVie jeu;

    /** Interface du jeu de la vie */
    private JeuDeLaVieUI ui;
    
    /* =========== Constructeurs =========== */

    /**
     * Constructeur de l'application
     */
    public App() {
        super("Jeu de la Vie");

        jeu = new JeuDeLaVie();
        jeu.initialiseGrille(10);

        ui = new JeuDeLaVieUI(jeu, 10);

        jeu.attacheObservateur(ui);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(ui.getDimFrame());
        this.add(ui);
    }


    
    /* ======= Méthodes d'instance ========= */

    /**
     * Lancer l'application
     */
    public void run() {
        this.setVisible(true);
    }

}
