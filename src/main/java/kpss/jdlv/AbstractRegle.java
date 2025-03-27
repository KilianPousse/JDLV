package kpss.jdlv;

/**
 * Classe abstraite des regle du jeu de la vie (Visiteur)
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public abstract class AbstractRegle implements Regle {
    
    /** Jeu de la vie */
    protected JeuDeLaVie jeu;

    /** Nom de la regle du jeu de la vie */
    private String nom;

    /**
     * Constructeur d'une regle sans jeu
     */
    public AbstractRegle(String nom) {
        jeu = null;
        this.nom = nom;
    }

    /**
     * Affectation d'un jeu à la règle
     */
    @Override
    public void setJeu(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    /**
     * Recuperation du nom de la regle
     * @return nom de la regle
     */
    @Override
    public String getNom() {
        return nom;
    }
    
    /**
     * Representation en String de la regle
     * @return la representation
     */
    @Override
    public String toString() {
        return nom;
    }

}
