package kpss.jdlv;

import java.awt.GridLayout;
import javax.swing.*;

/**
 * Classe representant l'application
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public class App {

    /* ======= Variables d'instances ======= */

    /** Fenetre de l'application */
    private JFrame fenetre;

    /** Jeu de la vie */
    private JeuDeLaVie jeu;

    /** Interface du jeu de la vie */
    private JeuDeLaVieUI ui;

    /** Console qui est appellé à chaque boucle */
    private Console console;

    /** Timer pour la boucle du jeu */
    private Timer timer;

    /** Panneau de controle du jeu */
    private PanneauDeControle pdc;

    /** Liste des règles du jeu possibles */
    private Regle[] regles;
    
    /* =========== Constructeurs =========== */

    /**
     * Constructeur de l'application
     */
    public App() {
        fenetre = new JFrame("Le Jeu De La Vie");

        jeu = new JeuDeLaVie(200, 200);
        jeu.initialiseGrille(10);

        regles = new Regle[] {
            new RegleClassique(jeu),
            new RegleClassique(jeu)
        };

        timer = new Timer(200, e -> update());
        timer.stop();

        ui = new JeuDeLaVieUI(jeu, 3);
        console = new Console(jeu);
        pdc = new PanneauDeControle(jeu, timer, regles);

        jeu.attacheObservateur(ui);
        jeu.attacheObservateur(console);

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(1225, 625);

        fenetre.setLayout(new GridLayout(1, 2));
        fenetre.add(pdc.getPanel());
        fenetre.add(ui);
    }


    
    /* ======= Méthodes d'instance ========= */

    /**
     * Lancer l'application
     */
    public void run() {
        fenetre.setVisible(true);
    }

    /*
     * Actualiser la simulation
     */
    public void update() {
        jeu.calculerGeneration();
    }

    /**
     * Ajouter une cellule vivante
     */
    public void ajouteCellule(int x, int y) {
        jeu.ajouteCellule(x, y);
    }
}
