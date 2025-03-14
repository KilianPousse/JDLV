package kpss.jdlv;

import kpss.jdlv.ui.*;

import java.awt.Toolkit;
import javax.swing.*;

/**
 * Classe representant l'application
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public class App {

    /* ======= Variables d'instances ======= */

    /** Fenetre de l'application */
    public JFrame fenetre;

    /** Jeu de la vie */
    public JeuDeLaVie jeu;

    /** Interface du jeu de la vie */
    public JeuDeLaVieUI ui;

    /** Console qui est appellé à chaque boucle */
    public Console console;

    /** Timer pour la boucle du jeu */
    public Timer timer;

    /** Panneau de controle du jeu */
    public PanneauDeControle panneauControle;

    /** Liste des règles du jeu possibles */
    public Regle[] regles;

    /** Ecran qui affiche des information sur le jeu */
    public EcranInfo ecranInfo;

    /** Barre du menu en haut */
    public MenuBarre menu;
    
    /* =========== Constructeurs =========== */

    /**
     * Constructeur de l'application
     */
    public App() {
        fenetre = new JFrame("Le Jeu De La Vie");
        fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fenetre.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                close();
            }
        });
        fenetre.setSize(1225, 625);

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
        panneauControle = new PanneauDeControle(jeu, timer, regles);
        ecranInfo = new EcranInfo(jeu);
        menu = new MenuBarre(this);

        fenetre.setJMenuBar(menu);

        jeu.attacheObservateur(ui);
        jeu.attacheObservateur(console);
        jeu.attacheObservateur(ecranInfo);

        JPanel panelGauche = new JPanel();
        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
        panelGauche.add(ecranInfo);
        panelGauche.add(panneauControle);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelGauche, ui);
        splitPane.setDividerSize(4);

        fenetre.add(splitPane);
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

    /**
     * Sortie de l'application
     */
    public void close() {
        Toolkit.getDefaultToolkit().beep();
        int choix = JOptionPane.showConfirmDialog(
            fenetre, 
            "Voulez-vous vraiment quitter ?", 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );

        if(choix == JOptionPane.YES_OPTION) {
            fenetre.dispose();
            System.exit(0); 
        }
    }
}
