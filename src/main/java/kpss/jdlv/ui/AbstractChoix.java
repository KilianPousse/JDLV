package kpss.jdlv.ui;

import javax.swing.JComboBox;

import kpss.jdlv.App;

/**
 * Actract boite a choix du jeu de la vie
 * @author Kilian POUSSE
 * @since 2025-03-26
 */
public abstract class AbstractChoix<T> implements Choix<T> {
    
    /** Boite a choix */
    protected JComboBox<T> choix;

    /** Application du jeu de la vie */
    protected App app;

    /**
     * Constructeur
     * @param app Application du jeu de la vie
     * @param listeChoix Liste des choix
     * @param selectionne Choix par defaut
     */
    public AbstractChoix(App app, T[] listeChoix, T selectionne) {
        this.app = app;
        choix = new JComboBox<>(listeChoix);
        choix.setSelectedItem(selectionne);
        choix.addActionListener((e) -> executer());
        choix.setEnabled(false);
    }

    /**
     * Actualise la boite a choix
     */
    @Override
    public void actualise() {
        choix.setEnabled(!app.estVide());
        choix.repaint();
    }

    /**
     * Getter: prenre le composant
     * @return composant
     */
    public JComboBox<T> getChoix() {
        return choix;
    }

    /**
     * Getter: récupération de l'objet sélectionné.
     * @return Objet sélectionné dans la liste
     */
    @SuppressWarnings("unchecked")
    @Override
    public T getSelection() {
        return (T) choix.getSelectedItem();
    }
}
