package kpss.jdlv;

import kpss.jdlv.ui.*;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Classe representant l'application
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public class App implements Observable {

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
    private PanneauDeControle panneauControle;

    /** Liste des observateurs de l'application */
    private List<Observateur> observateurs;

    /** Ecran des informations du jeu de la vie */
    private EcranInfo ecranInfo;

    /** Barre du menu de l'application */
    private BarreMenu menu;

    /** Panel de gauche */
    private JPanel panelGauche;
    
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
                quitter();
            }
        });
        fenetre.setSize(830, 665);
        fenetre.setLocationRelativeTo(null);

        menu = new BarreMenu(this);
        fenetre.setJMenuBar(menu);

        jeu = new JeuDeLaVie(200, 200);
        jeu.initialiseGrille(0);

        timer = new Timer(200, e -> actualise());
        timer.stop();

        ui = new JeuDeLaVieUI(jeu, 3);
        console = new Console(jeu);
        panneauControle = new PanneauDeControle(this);
        ecranInfo = new EcranInfo(jeu);

        jeu.attacheObservateur(ui);
        jeu.attacheObservateur(console);
        jeu.attacheObservateur(ecranInfo);

        panelGauche = new JPanel();
        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.Y_AXIS));
        panelGauche.add(panneauControle);
        panelGauche.add(ecranInfo);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelGauche, ui);
        splitPane.setDividerSize(4);

        fenetre.add(splitPane);

        observateurs = new ArrayList<>();
        attacheObservateur(panneauControle);
        attacheObservateur(menu);
        
    }


    /* ======= Getter & Setter ======= */

    /**
     * Getter: Récupération du jeu de la vie
     * @return Jeu de la vie
     */
    public JeuDeLaVie getJeu() {
        return jeu;
    }

    public void setJeu(JeuDeLaVie nouvJeu) {
        jeu.dettacheObservateur(console);

        jeu = nouvJeu;
        panelGauche.remove(ecranInfo);  
        ecranInfo = new EcranInfo(jeu); 
        jeu.attacheObservateur(ecranInfo);
        panelGauche.add(ecranInfo, BorderLayout.NORTH); 
        console = new Console(jeu);
        ui.setJeu(jeu);
        jeu.attacheObservateur(ui);
        jeu.attacheObservateur(console);
        notifieObservateurs();
        ui.actualise();
    }

    /**
     * Getter: recuperation du delais d'actualisation du jeu
     * @return delais d'actualisation du jeu
     */
    public int getDelais() {
        return timer.getDelay();
    }

    /**
     * Setter: Affectation du delais d'actualisation du jeu
     * @param vitesse delais d'actualisation du jeu
     */
    public void setDelais(int vitesse) {
        timer.setDelay(vitesse);
        notifieObservateurs();
        if(timer.isRunning()) {
            timer.restart();
        }
    }

    /**
     * Setter: Affectation d'une nouvelle regle au jeu
     * @param regle nouvelle regle
     */
    public void setRegle(Regle regle) {
        jeu.setRegle(regle);
        notifieObservateurs();
    }

    /**
     * Getter: Recuperation de la fenetre du l'application
     * @return fenetre du l'application
     */
    public JFrame getFenetre() {
        return fenetre;
    }

    /* ======= Méthodes d'instance ========= */

    /**
     * Lancer l'application
     */
    public void lance() {
        fenetre.setVisible(true);
    }

    /**
     * Permet de savoir si l'application est en pause
     * @return Vrai si l'app est en pause, Faux sinon
     */
    public boolean estEnPause() {
        return !timer.isRunning();
    }

    /*
     * Actualiser la simulation
     */
    public void actualise() {
        jeu.calculerGeneration();
        //notifieObservateurs();
    }

    /**
     * Démarrage du jeu de la vie
     */
    public void demarre() {
        if(estEnPause()) {
            timer.start();
            notifieObservateurs();
        }
    }

    /**
     * Arrêt du jeu de la vie
     */
    public void arret() {
        if(!estEnPause()) {
            timer.stop();
            notifieObservateurs();
        }
    }

    /**
     * Sortie de l'application
     */
    public void quitter() {
        Toolkit.getDefaultToolkit().beep();
        int choix = JOptionPane.showConfirmDialog(
            fenetre, 
            "Voulez-vous quitter sans sauvegarder ?", 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );

        if(choix == JOptionPane.YES_OPTION) {
            fenetre.dispose();
            System.exit(0); 
        }
    }

    /**
     * Permet de sauvegarder le jeu en cours dans un fichier
     * @param fichier Chemin vers le fichier 
     */
    public void enregiste(String fichier) {
        JeuDeLaVie.save(jeu, fichier);
    }

    /**
     * Permet de charger un jeu depuis un fichier
     */
    public void charge(String fichier) {
        fenetre.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        jeu.dettacheObservateur(console);

        JeuDeLaVie nouvJeu = JeuDeLaVie.load(fichier);
        setJeu(nouvJeu);
        
        fenetre.setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Permet d'attacher un observateur.
     * @param o Observateur à attacher
     */
    @Override
    public void attacheObservateur(Observateur o) {
        observateurs.add(o);
    }

    /**
     * Permet de déttacher un observateur.
     * @param o Observateur à déttacher
     */
    @Override
    public void dettacheObservateur(Observateur o) {
        observateurs.remove(o);
    }

    /**
     * Permet de notifer les observateurs
     */
    @Override
    public void notifieObservateurs() {
        for(Observateur o: observateurs) {
            o.actualise();
        }
    }
}
