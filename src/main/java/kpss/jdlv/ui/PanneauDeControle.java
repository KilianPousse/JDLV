package kpss.jdlv.ui;

import kpss.jdlv.*;
import kpss.jdlv.ui.commande.CmdAvancerGen;
import kpss.jdlv.ui.commande.CmdChangerVitesse;
import kpss.jdlv.ui.commande.CmdDemarrerArreter;
import kpss.jdlv.ui.commande.CmdSelectionRegle;
import kpss.jdlv.ui.commande.CmdZoomer;
import kpss.jdlv.ui.commande.JDLVCommande;
import kpss.jdlv.ui.commande.JDLVCommandeObj;

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

    /** Slider pour modifier le coefficient de zoom */
    private JSlider zoomSlider;

    /** Bordure qui affiche le coefficient de zoom */
    private TitledBorder zoomBorder;

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
        boutonAvancer = new JButton("Avancer d'une génération");

        // Slider: vistesse
        vitesseSlider = new JSlider(0, 1000, app.getDelais());
        vitesseSlider.setMajorTickSpacing(200);
        vitesseSlider.setMinorTickSpacing(100);
        vitesseSlider.setPaintTicks(true);
        vitesseSlider.setPaintLabels(true);
        vitesseBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Délai: " + app.getDelais() + " ms"
        );
        vitesseSlider.setBorder(vitesseBorder);


        // ComboBox
        reglesComboBox = new JComboBox<>(app.getRegles());
        reglesComboBox.setSelectedItem(app.getJeu().getRegle());

        // Slider: zoom
        zoomSlider = new JSlider(0, 200, app.getUi().getZoom());
        zoomSlider.setMajorTickSpacing(50);
        zoomSlider.setMinorTickSpacing(25);
        zoomSlider.setPaintTicks(true);
        zoomSlider.setPaintLabels(false);
        zoomBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Zoom: "
        );
        zoomSlider.setBorder(zoomBorder);

        // Commandes du jeu de la vie
        JDLVCommande demarrerArreterCommande = new CmdDemarrerArreter(app);
        JDLVCommande avancerCommande = new CmdAvancerGen(app);
        JDLVCommandeObj<Integer> vitesseCommande = new CmdChangerVitesse(app);
        JDLVCommandeObj<Regle> regleCommande = new CmdSelectionRegle(app);
        JDLVCommandeObj<Integer> zoomCommande = new CmdZoomer(app);

        // Initialisation des methodes a effectuer
        boutonDemarrage.addActionListener((e) -> demarrerArreterCommande.executer());
        boutonAvancer.addActionListener((e) -> avancerCommande.executer());
        vitesseSlider.addChangeListener((e) -> vitesseCommande.executer(vitesseSlider.getValue()));
        reglesComboBox.addActionListener((e) -> regleCommande.executer((Regle) reglesComboBox.getSelectedItem()));
        zoomSlider.addChangeListener((e) -> zoomCommande.executer(zoomSlider.getValue()));
        

        this.add(boutonDemarrage);
        this.add(boutonAvancer);
        this.add(vitesseSlider);
        this.add(reglesComboBox);
        this.add(zoomSlider);
    }


    /* ======= Methodes d'instance ======= */


    @Override
    public void actualise() {
        if(app.estVide()) {
            boutonDemarrage.setEnabled(false);
            boutonAvancer.setEnabled(false);
            reglesComboBox.setEnabled(false);
            vitesseSlider.setEnabled(false);
            zoomSlider.setEnabled(false);
        }
        else {
            vitesseSlider.setEnabled(true);
            zoomSlider.setEnabled(true);
            if(app.estEnPause()) {
                boutonDemarrage.setText("Démarrer");
                boutonDemarrage.setEnabled(true);
                boutonAvancer.setEnabled(true);
                reglesComboBox.setEnabled(true);
            }
            else {
                boutonDemarrage.setText("Arrêter");
                boutonAvancer.setEnabled(false);
                reglesComboBox.setEnabled(false);
            }
        }
        vitesseBorder.setTitle("Délai: " + app.getDelais() + " ms");
        reglesComboBox.setSelectedItem(app.getJeu().getRegle());
    }
    
}
