package kpss.jdlv;

import javax.swing.*;
import java.awt.*;

public class PanneauDeControle {

    /* ======= Variables d'instance =======  */

    /** Jeu de la vie */
    private JeuDeLaVie jeu;

    /** Timer du jeu de la vie */
    private Timer timer;

    /** Panel Principal du Paneau de Control */
    private JPanel panel;

    /** Bouton de demarrage/arrête du jeu */
    private JButton boutonDemarrage;

    /** Bouton pour avancer d'une génération si le jeu est en pause */
    private JButton boutonAvancer;

    /* Slider pour modifier la vitesse d'actualisation du jeu */
    private JSlider vitesseSlider;

    /* ComboBox des regles du jeu */
    private JComboBox<Regle> reglesComboBox;

    /* ======== Constructeurs ======== */

    /**
     * Constructeur d'un Panneau de controle
     * @param jeu Jeu de la vie
     * @param timer timer du jeu de la vie
     */
    public PanneauDeControle(JeuDeLaVie jeu, Timer timer, Regle[] regles) {
        // Initialisation des variables
        this.jeu = jeu;
        this.timer = timer;
        
        // Panel principal
        panel = new JPanel(new GridLayout(4, 1));
        panel.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Panneau de Contôle"
                )
            );

        // Boutons:
        boutonDemarrage = new JButton("Démarrer");
        boutonAvancer = new JButton("Avancer d'une génération");
        // Slider
        vitesseSlider = new JSlider(100, 1000, timer.getDelay());
        vitesseSlider.setMajorTickSpacing(100);
        vitesseSlider.setMinorTickSpacing(50);
        vitesseSlider.setPaintTicks(true);
        vitesseSlider.setPaintLabels(true);
        // ComboBox
        reglesComboBox = new JComboBox<>(regles);
        reglesComboBox.setSelectedItem(jeu.getRegle());

        // Initialisation des methodes a effectuer
        boutonDemarrage.addActionListener((e) -> miseEnPause());
        boutonAvancer.addActionListener((e) -> avanceGeneration());
        vitesseSlider.addChangeListener((e) -> actualiseVitesse(vitesseSlider.getValue()));
        reglesComboBox.addActionListener((e) -> choixRegle());

        panel.add(boutonDemarrage);
        panel.add(boutonAvancer);
        panel.add(vitesseSlider);
        panel.add(reglesComboBox);
    }

    /* ======= Getter & Setter ======= */

    /**
     * Getter: Recuperation du JPanel du Panneau de Control
     * @return JPanel du Panneau de Control
     */
    public JPanel getPanel() {
        return panel;
    }


    /* ======= Methodes d'instance ======= */

    /**
     * Permet de mettre le jeu en pause si il est en court d'execution,
     * ou de le mettre en court d'execution si il est en pause
     */
    private void miseEnPause() {
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
    private void avanceGeneration() {
        if(!timer.isRunning()) {
            jeu.calculerGeneration();
        }
    }

    /**
     * Permet d'actualiser le delais du timer du jeu
     * @param vitesse Nouvelle valeur du timer
     */
    private void actualiseVitesse(int vitesse) {
        timer.setDelay(vitesse);
        if(!timer.isRunning()) {
            timer.restart();
        }
    }

    /**
     * Permet de choisir une nouvelle regle du jeu
     */
    private void choixRegle() {
        Regle regle = (Regle) reglesComboBox.getSelectedItem();
        jeu.setRegle(regle);
    }
    
}
