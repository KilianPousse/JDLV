package kpss.jdlv.commande;

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
        app.arret();

        JDialog fenetreNouveau = new JDialog((Frame) null, "Nouveau", true);
        fenetreNouveau.setSize(300, 200);
        fenetreNouveau.setLocationRelativeTo(app.getFenetre());
        fenetreNouveau.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fenetreNouveau.setResizable(false);

        // Panel principal avec BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel Titre
        JPanel titrePanel = new JPanel(new BorderLayout(5, 5));
        JLabel titreLabel = new JLabel("Initialisation: ");
        Font titreFont = titreLabel.getFont();
        titreLabel.setFont(new Font(titreFont.getFontName(), Font.BOLD, titreFont.getSize()+2)); 
        titrePanel.add(titreLabel, BorderLayout.WEST);
        titrePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel pour la taille
        JPanel taillePanel = new JPanel(new BorderLayout(5, 5));
        JLabel tailleLabel = new JLabel("Taille: ");
        JTextField tailleSaisie = new JTextField("200", 10);
        taillePanel.add(tailleLabel, BorderLayout.WEST);
        taillePanel.add(tailleSaisie, BorderLayout.CENTER);
        taillePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        // Panel pour le ratio
        JPanel ratioPanel = new JPanel(new BorderLayout(5, 5));
        JLabel ratioLabel = new JLabel("Ratio: ");
        SpinnerModel ratioModel = new SpinnerNumberModel(33.0, 0.0, 100.0, 1.0);
        JSpinner ratioSaisie = new JSpinner(ratioModel);
        ratioPanel.add(ratioLabel, BorderLayout.WEST);
        ratioPanel.add(ratioSaisie, BorderLayout.CENTER);
        ratioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        // Panel pour la séléction de la règle
        JPanel reglePanel = new JPanel(new BorderLayout(5, 5));
        JLabel regleLabel = new JLabel("Règle: ");
        JComboBox<Regle> regleSaisie = new JComboBox<>(app.getRegles());
        regleSaisie.setSelectedItem(app.getJeu().getRegle());
        reglePanel.add(regleLabel, BorderLayout.WEST);
        reglePanel.add(regleSaisie, BorderLayout.CENTER);
        reglePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        // Ajout des éléments au panel principal
        panel.add(titrePanel);
        panel.add(taillePanel);
        panel.add(ratioPanel);
        panel.add(reglePanel);

        // Bouton de validation
        JButton validerButton = new JButton("Valider");
        panel.add(validerButton);
        validerButton.addActionListener((e) -> {
            int taille = Integer.parseInt(tailleSaisie.getText());
            double ratio = (Double) ratioSaisie.getValue();

            JeuDeLaVie jeu;
            try {
                jeu = new JeuDeLaVie(taille);
            }
            catch(Exception exception) {
                fenetreNouveau.dispose();
                FenetreErreur.ouvrir(app, exception);
                return;
            }

            Regle regle = (Regle) regleSaisie.getSelectedItem();
            regle.setJeu(jeu);

            jeu.setRegle(regle);
            jeu.initialiseGrille(ratio);
            app.setJeu(jeu);

            fenetreNouveau.dispose();
        });

        

        // Ajout du panel principal à la fenêtre
        fenetreNouveau.add(panel);
        fenetreNouveau.pack(); // Ajuste automatiquement la taille
        fenetreNouveau.setVisible(true);
    }

}
