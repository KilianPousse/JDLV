package kpss.jdlv.ui;

import javax.swing.JButton;

import kpss.jdlv.App;

/**
 * Boutton Abtrait de l'application du jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-26
 */
public abstract class AbstractBouton implements Bouton {

    /** Application du JDLV */
    protected App app;

    /** Bouton */
    protected JButton bouton;

    /**
     * Constructeur
     * @param app Application du JDLV 
     * @param text Texte dans le bouton
     */
    public AbstractBouton(App app, String text) {
        this.app = app;
        bouton = new JButton(text);
        bouton.addActionListener((e) -> executer());
    }

    /**
     * Setter: Text dans le bouton
     * @param text Text dans le bouton
     */
    public void setText(String text) {
        bouton.setText(text);
    }

    /**
     * Getter: du composant Swing
     * @return
     */
    public JButton getBouton() {
        return bouton;
    }

    /**
     * Actualise le bouton (son affichage)
     */
    @Override
    public void actualise() {
        bouton.setEnabled(!app.estVide());
        bouton.repaint();
    }
    
}
