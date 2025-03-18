package kpss.jdlv.ui;

import kpss.jdlv.*;
import kpss.jdlv.ui.commande.CmdAvancerGen;
import kpss.jdlv.ui.commande.CmdChangerVitesse;
import kpss.jdlv.ui.commande.CmdDemarrerArreter;
import kpss.jdlv.ui.commande.CmdSelectionRegle;
import kpss.jdlv.ui.commande.JDLVCommande;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class PanneauDeControle extends JPanel implements Observateur {

    /* ======= Variables d'instance =======  */

    /** Application du Jeu de la vie */
    private App app;

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
    public PanneauDeControle(App app) {
        super(new GridLayout(6, 1));
        this.app = app;
        
        this.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Panneau de Contôle"
                )
            );

        // Boutons:
        boutonDemarrage = new JButton("Démarrer");
        boutonDemarrage.setEnabled(false);
        boutonAvancer = new JButton("Avancer d'une génération");
        boutonAvancer.setEnabled(false);

        // Slider
        vitesseSlider = new JSlider(0, 1000, app.getDelais());
        vitesseSlider.setMajorTickSpacing(200);
        vitesseSlider.setMinorTickSpacing(100);
        vitesseSlider.setPaintTicks(true);
        vitesseSlider.setPaintLabels(true);
        vitesseBorder  = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Vitesse: " + app.getDelais()
        );
        vitesseSlider.setBorder(vitesseBorder);


        // ComboBox
        reglesComboBox = new JComboBox<>(app.getJeu().getRegles());
        reglesComboBox.setSelectedItem(app.getJeu().getRegle());

        // Commandes du jeu de la vie
        JDLVCommande demarrerArreterCommande = new CmdDemarrerArreter(app);
        JDLVCommande avancerCommande = new CmdAvancerGen(app);

        // Initialisation des methodes a effectuer
        boutonDemarrage.addActionListener((e) -> demarrerArreterCommande.executer());
        boutonAvancer.addActionListener((e) -> avancerCommande.executer());
        vitesseSlider.addChangeListener((e) -> new CmdChangerVitesse(app, vitesseSlider.getValue()).executer());
        reglesComboBox.addActionListener((e) -> new CmdSelectionRegle(app, (Regle) reglesComboBox.getSelectedItem()));

        this.add(boutonDemarrage);
        this.add(boutonAvancer);
        this.add(vitesseSlider);
        this.add(reglesComboBox);
    }


    /* ======= Methodes d'instance ======= */


    @Override
    public void actualise() {
        boutonDemarrage.setEnabled(true);
        if(app.estEnPause()) {
            boutonDemarrage.setText("Démarrer");
            boutonAvancer.setEnabled(true);
        }
        else {
            boutonDemarrage.setText("Arrêter");
            boutonAvancer.setEnabled(false);
        }
        vitesseBorder.setTitle("Vitesse: " + app.getDelais());
    }
    
}
