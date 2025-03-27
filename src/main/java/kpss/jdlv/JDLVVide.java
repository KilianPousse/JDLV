package kpss.jdlv;

/**
 * Classe qui represente un jeu de la vie vide
 * @author Kilian POUSSE
 * @since 2025-03-18
 */
public class JDLVVide extends JeuDeLaVie {
    
    /**
     * Constructeur de la classe Jeu de la vie Vide
     */
    public JDLVVide() {
        super();
    }

    /**
     * Verifie si jeu est vide
     * @return
     */
    @Override
    public boolean estVide() {
        return true;
    }

    @Override
    public void notifieObservateurs() {
        System.out.println("Jeu de la vie Vide !!");
    }
}
