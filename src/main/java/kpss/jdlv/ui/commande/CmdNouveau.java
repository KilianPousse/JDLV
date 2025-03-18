package kpss.jdlv.ui.commande;

import java.awt.*;
import javax.swing.*;
import kpss.jdlv.*;

public class CmdNouveau implements JDLVCommande {
    
    /** Application du jeu de la vie */
    private App app;

    /** 
     * Constructeur: Commande qui creer un nouveau jeu
     * @param app Application du jeu de la vie
     */
    public CmdNouveau(App app) {
        this.app = app;
    }

    /**
     * Permet de creer un nouveau jeu
     */
    @Override
    public void executer() {


        JDialog fenetreNouveau = new JDialog((Frame) null, "Nouveau", true);
        fenetreNouveau.setSize(300, 200);
        fenetreNouveau.setLocationRelativeTo(app.getFenetre());
        fenetreNouveau.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Panel principal avec BoxLayout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Panel pour la taille
        JPanel taillePanel = new JPanel(new BorderLayout(5, 5));
        JLabel tailleLabel = new JLabel("Taille: ");
        JTextField tailleSaisie = new JTextField("200", 10);
        taillePanel.add(tailleLabel, BorderLayout.WEST);
        taillePanel.add(tailleSaisie, BorderLayout.CENTER);

        // Panel pour le ratio
        JPanel ratioPanel = new JPanel(new BorderLayout(5, 5));
        JLabel ratioLabel = new JLabel("Ratio: ");
        SpinnerModel ratioModel = new SpinnerNumberModel(10.0, 0.0, 100.0, 1.0);
        JSpinner ratioSaisie = new JSpinner(ratioModel);
        ratioPanel.add(ratioLabel, BorderLayout.WEST);
        ratioPanel.add(ratioSaisie, BorderLayout.CENTER);

        // Ajout des éléments au panel principal
        contentPanel.add(taillePanel);
        contentPanel.add(ratioPanel);

        // Bouton de validation
        JButton validerButton = new JButton("Valider");
        contentPanel.add(validerButton);
        validerButton.addActionListener((e) -> {
            int taille = Integer.parseInt(tailleSaisie.getText());
            double ratio = (Double) ratioSaisie.getValue();

            JeuDeLaVie jeu = new JeuDeLaVie(taille, taille);
            jeu.setRegle(new RegleClassique(jeu));
            jeu.initialiseGrille(ratio);
            app.setJeu(jeu);

            fenetreNouveau.dispose();
        });

        

        // Ajout du panel principal à la fenêtre
        fenetreNouveau.add(contentPanel);
        fenetreNouveau.pack(); // Ajuste automatiquement la taille
        fenetreNouveau.setVisible(true);
    }

}
