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

    /* ======= Constantes de classe ======== */

    /** Zoom maximum applicable sur le jeu */
    public static final int MAX_ZOOM = 400;

    /** Zoom minimal applicable sur le jeu */
    public static final int MINI_ZOOM = 1;

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
    private List<Observateur> observateurs = new ArrayList<>();

    /** Ecran des informations du jeu de la vie */
    private EcranInfo ecranInfo;

    /** Barre du menu de l'application */
    private BarreMenu menu;

    /** Panel de gauche */
    private JPanel panelGauche;

    /** Graphique des donnees du jue */
    private JDLVGraphique graphique;

    /** Ensembles des règles d ujeu de la vie possible */
    private Regle[] regles = new Regle[] {
        new RegleClassique(),
        new RegleDayNight(),
        new RegleHighLife()
    };
    
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
        fenetre.setSize(826, 678);
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);

        menu = new BarreMenu(this);
        fenetre.setJMenuBar(menu);

        jeu = new JDLVVide();
        graphique = new JDLVGraphique(jeu);

        timer = new Timer(200, e -> actualise());
        timer.stop();

        ui = new JeuDeLaVieUI(this, jeu, 3);
        console = new Console(jeu);
        panneauControle = new PanneauDeControle(this);
        ecranInfo = new EcranInfo(jeu, graphique);

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
        
        notifieObservateurs();
    }


    /* ======= Getter & Setter ======= */

    /**
     * Getter: Récupération du jeu de la vie
     * @return Jeu de la vie
     */
    public JeuDeLaVie getJeu() {
        return jeu;
    }

    /**
     * Setter: Affectation d'un nouveau jeu
     * @param nouvJeu Nouveau jeu de la vie
     */
    public void setJeu(JeuDeLaVie nouvJeu) {
        jeu.dettacheObservateur(console);
        jeu.dettacheObservateur(graphique);

        jeu = nouvJeu;
        graphique.setJeu(jeu);
        panelGauche.remove(ecranInfo);  
        ecranInfo = new EcranInfo(jeu, graphique); 
        jeu.attacheObservateur(ecranInfo);
        panelGauche.add(ecranInfo, BorderLayout.NORTH); 
        for(Regle r: regles) r.setJeu(jeu);
        console = new Console(jeu);
        ui.setJeu(jeu);
        ui.zoomer(100, this);
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

    /**
     * Getter: Recuperation des règles du jeu de la vie
     * @return règles du jeu de la vie
     */
    public Regle[] getRegles() {
        return regles;
    }

    /**
     * Getter: Recuperation de l'UI
     * @return UI
     */
    public JeuDeLaVieUI getUi() {
        return ui;
    }

    /**
     * Getter: Recuperation des Donnees liees au nombre
     * d'individu par generation.
     * @return Donnees liees au nombre d'individu par generation
     */
    public JDLVGraphique getGraphique() {
        return graphique;
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
        arret();
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
        try {
            JeuDeLaVie.save(jeu, fichier);
        }
        catch(Exception e) {
            FenetreErreur.ouvrir(this, e);
        }
    }

    /**
     * Permet de charger un jeu depuis un fichier
     */
    public void charge(String fichier) {
        fenetre.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        jeu.dettacheObservateur(console);

        JeuDeLaVie nouvJeu = new JDLVVide();
        try {
            nouvJeu = JeuDeLaVie.load(fichier);
        }
        catch(Exception e) {
            FenetreErreur.ouvrir(this, e);
        }
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

    /**
     * Verifie si jeu est vide
     * @return
     */
    public boolean estVide() {
        return jeu.estVide();
    }
}
