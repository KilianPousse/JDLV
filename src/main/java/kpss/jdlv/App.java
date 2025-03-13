package kpss.jdlv;

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

    /** Sauvegarde si le jeu est en pause */
    private boolean enPause;
    
    /* =========== Constructeurs =========== */

    /**
     * Constructeur de l'application
     */
    public App() {
        fenetre = new JFrame("Le Jeu De La Vie");

        jeu = new JeuDeLaVie(200, 200);
        jeu.initialiseGrille(10);

        ui = new JeuDeLaVieUI(jeu, 3);
        console = new Console(jeu);

        jeu.attacheObservateur(ui);
        jeu.attacheObservateur(console);

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setSize(ui.getDimFrame());
        fenetre.add(ui);

        timer = new Timer(100, e -> update());
    }


    
    /* ======= Méthodes d'instance ========= */

    /**
     * Lancer l'application
     */
    public void run() {
        fenetre.setVisible(true);
    }

    /**
     * Lancer le jeu
     */
    public void start() {
        if(!timer.isRunning()) {
            timer.start();
            enPause = false;
        }
    }

    /**
     * Met le jeu en pause
     */
    public void pause() {
        if(timer.isRunning()) {
            timer.stop();
            enPause = true;
        }
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
