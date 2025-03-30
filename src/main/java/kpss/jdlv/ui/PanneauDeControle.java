package kpss.jdlv.ui;

import java.util.ArrayList;
import java.util.List;
import kpss.jdlv.*;
import javax.swing.*;
import java.awt.*;

/**
 * Panneau de controle du jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-26
 */
public class PanneauDeControle extends JPanel implements Observable, Observateur {

    /* ======= Variables d'instance =======  */

    /** Application du Jeu de la vie */
    private App app;

    /** Liste des observeurs du panneau */
    private List<Observateur> observateurs = new ArrayList<>();

    /* ======== Constructeurs ======== */

    /**
     * Constructeur d'un Panneau de controle
     * @param jeu Jeu de la vie
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

        Bouton demarrageBouton = new DemarrageBouton(app);
        Bouton avancerBouton = new AvancerBouton(app);
        Curseur vitesseCurseur = new VitesseCurseur(app);
        Choix<Regle> regleChoix = new RegleChoix(app);
        Curseur zoomCurseur = new ZoomCurseur(app);
        Bouton resetDragBouton = new ResetDragBouton(app);

        this.add(demarrageBouton.getBouton());
        this.add(avancerBouton.getBouton());
        this.add(vitesseCurseur.getCurseur());
        this.add(regleChoix.getChoix());
        this.add(zoomCurseur.getCurseur());
        this.add(resetDragBouton.getBouton());

        attacheObservateur(demarrageBouton);
        attacheObservateur(avancerBouton);
        attacheObservateur(vitesseCurseur);
        attacheObservateur(regleChoix);
        attacheObservateur(zoomCurseur);
        attacheObservateur(resetDragBouton);
    }

    /**
     * Getter: Recuperation de l'app
     * @return app
     */
    public App getApp() {
        return app;
    }

    /**
     * Actualiser le panneau en actualisant ses observateurs
     */
    @Override
    public void actualise() {
        notifieObservateurs();
    }

    /**
     * Attacher un nouvel observateur
     */
    @Override
    public void attacheObservateur(Observateur o) {
        observateurs.add(o);
    }

    /**
     * Déttacher un observateur
     */
    @Override
    public void dettacheObservateur(Observateur o) {
        observateurs.remove(o);
    }

    /**
     * Notifier les observateurs pour qu'ils s'actualise
     */
    @Override
    public void notifieObservateurs() {
        for(Observateur o: observateurs) {
            o.actualise();
        }
    }
}
