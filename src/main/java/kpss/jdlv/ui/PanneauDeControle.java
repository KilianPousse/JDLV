package kpss.jdlv.ui;

import kpss.jdlv.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class PanneauDeControle extends JPanel {

    /* ======= Variables d'instance =======  */

    /** Jeu de la vie */
    private JeuDeLaVie jeu;

    /** Timer du jeu de la vie */
    private Timer timer;

    /** Bouton de demarrage/arrête du jeu */
    private JButton boutonDemarrage;

    /** Bouton pour avancer d'une génération si le jeu est en pause */
    private JButton boutonAvancer;

    /** Slider pour modifier la vitesse d'actualisation du jeu */
    private JSlider vitesseSlider;

    /** Bordure qui affiche la vitesse */
    private TitledBorder vitesseBorder;

    /** ComboBox des regles du jeu */
    private JComboBox<Regle> reglesComboBox;

    /* ======== Constructeurs ======== */

    /**
     * Constructeur d'un Panneau de controle
     * @param jeu Jeu de la vie
     * @param timer timer du jeu de la vie
     */
    public PanneauDeControle(JeuDeLaVie jeu, Timer timer, Regle[] regles) {
        super(new GridLayout(6, 1));
        // Initialisation des variables
        this.jeu = jeu;
        this.timer = timer;
        
        this.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Panneau de Contôle"
                )
            );

        // Boutons:
        boutonDemarrage = new JButton("Démarrer");
        boutonAvancer = new JButton("Avancer d'une génération");

        // Slider
        vitesseSlider = new JSlider(0, 1000, timer.getDelay());
        vitesseSlider.setMajorTickSpacing(200);
        vitesseSlider.setMinorTickSpacing(100);
        vitesseSlider.setPaintTicks(true);
        vitesseSlider.setPaintLabels(true);
        vitesseBorder  = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Vitesse: " + timer.getDelay()
        );
        vitesseSlider.setBorder(vitesseBorder);


        // ComboBox
        reglesComboBox = new JComboBox<>(regles);
        reglesComboBox.setSelectedItem(jeu.getRegle());

        // Initialisation des methodes a effectuer
        boutonDemarrage.addActionListener((e) -> miseEnPause());
        boutonAvancer.addActionListener((e) -> avanceGeneration());
        vitesseSlider.addChangeListener((e) -> actualiseVitesse(vitesseSlider.getValue()));
        reglesComboBox.addActionListener((e) -> choixRegle());

        this.add(boutonDemarrage);
        this.add(boutonAvancer);
        this.add(vitesseSlider);
        this.add(reglesComboBox);
    }


    /* ======= Methodes d'instance ======= */

    /**
     * Permet de mettre le jeu en pause si il est en court d'execution,
     * ou de le mettre en court d'execution si il est en pause
     */
    public void miseEnPause() {
        // Mettre le jeu en pause
        if(timer.isRunning()) {
            timer.stop();
            boutonDemarrage.setText("Reprendre");
            boutonAvancer.setEnabled(true);
        }
        // Redemarrage
        else {
            timer.start();
            boutonDemarrage.setText("Arrêter");
            boutonAvancer.setEnabled(false);
        }
    }

    /**
     * Permet de passer à la génération suivante si le jeu est en pause
     */
    public void avanceGeneration() {
        if(!timer.isRunning()) {
            jeu.calculerGeneration();
        }
    }

    /**
     * Permet d'actualiser le delais du timer du jeu
     * @param vitesse Nouvelle valeur du timer
     */
    public void actualiseVitesse(int vitesse) {
        timer.setDelay(vitesse);
        vitesseBorder.setTitle("Vitesse: " + timer.getDelay());
        if(timer.isRunning()) {
            timer.restart();
        }
    }

    /**
     * Permet de choisir une nouvelle regle du jeu
     */
    public void choixRegle() {
        Regle regle = (Regle) reglesComboBox.getSelectedItem();
        jeu.setRegle(regle);
    }
    
}
