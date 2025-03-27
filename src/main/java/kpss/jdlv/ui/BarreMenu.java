package kpss.jdlv.ui;

import javax.swing.*;
import kpss.jdlv.*;
import kpss.jdlv.commande.CmdAvancerGen;
import kpss.jdlv.commande.CmdCharger;
import kpss.jdlv.commande.CmdDemarrerArreter;
import kpss.jdlv.commande.CmdEnregistrer;
import kpss.jdlv.commande.CmdNouveau;
import kpss.jdlv.commande.CmdQuitter;
import kpss.jdlv.commande.JDLVCommande;

/**
 * Classe representant une barre de menu de l'application
 * @author Kilian POUSSE
 * @since 2025-03-17
 */
public class BarreMenu extends JMenuBar implements Observateur {
    
    /** Application du jeu de la vie */
    private App app;

    /** Sous-menu d'enregistrement */
    private JMenuItem enregistreItem;

    /** Sous-menu demarrage/arreter */
    private JMenuItem demarrerArreterItem;

    /** Sous-menu Avancer d'une generation */
    private JMenuItem avancerGenItem;

    /**
     * Construction: Barre de menu de l'application
     * @param app Application du jeu
     */
    public BarreMenu(App app) {
        super();
        this.app = app;

        JMenu fichierMenu = new JMenu("Fichier");

        JMenuItem nouveauItem = new JMenuItem("Nouveau");
        enregistreItem = new JMenuItem("Enregister");
        JMenuItem chargerItem = new JMenuItem("Charger");
        JMenuItem quitterItem = new JMenuItem("Quitter");


        JMenu jeuMenu = new JMenu("Jeu");

        demarrerArreterItem = new JMenuItem("Démarrer");
        avancerGenItem = new JMenuItem("Avancer");

        fichierMenu.add(nouveauItem);
        fichierMenu.add(enregistreItem);
        fichierMenu.add(chargerItem);
        fichierMenu.addSeparator();
        fichierMenu.add(quitterItem);

        jeuMenu.add(demarrerArreterItem);
        jeuMenu.add(avancerGenItem);

        add(fichierMenu);
        add(jeuMenu);

        JDLVCommande nouveauCommande = new CmdNouveau(app);
        JDLVCommande enregistreCommande = new CmdEnregistrer(app);
        JDLVCommande chargeCommande = new CmdCharger(app);
        JDLVCommande quitteCommande = new CmdQuitter(app);

        JDLVCommande demarrerArreterCommande = new CmdDemarrerArreter(app);
        JDLVCommande avancerCommande = new CmdAvancerGen(app);

        
        nouveauItem.addActionListener((e) -> nouveauCommande.executer());
        enregistreItem.addActionListener((e) -> enregistreCommande.executer());
        chargerItem.addActionListener((e) -> chargeCommande.executer());
        quitterItem.addActionListener((e) -> quitteCommande.executer());

        demarrerArreterItem.addActionListener((e) -> demarrerArreterCommande.executer());
        avancerGenItem.addActionListener((e) -> avancerCommande.executer());
    }

    /**
     * Actualiser le composant
     */
    @Override
    public void actualise() {
        if(app.estVide()) {
            demarrerArreterItem.setEnabled(false);
            avancerGenItem.setEnabled(false);
            enregistreItem.setEnabled(false);
        }
        else{
            enregistreItem.setEnabled(true);
            if(app.estEnPause()) {
                demarrerArreterItem.setText("Démarrer");
                demarrerArreterItem.setEnabled(true);
                avancerGenItem.setEnabled(true);
            }
            else {
                demarrerArreterItem.setText("Arrêter");
                avancerGenItem.setEnabled(false);
            }
        }
    }

}
