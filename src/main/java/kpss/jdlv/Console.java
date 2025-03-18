package kpss.jdlv;

/**
 * Classe qui observe le jeu de la vie et affiche des informations d'une génération.
 * @author Kilian POUSSE
 * @since 2025-03-13
 */
public class Console implements Observateur {

    /* ====== Variables d'instance ====== */

    /** Jeu de la vie */
    public JeuDeLaVie jeu;


    /* ====== Constructeurs ====== */

    /**
     * Construction d'une console de jeu
     * @param jeu jeu de la vie
     */
    public Console(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    /* ======== Getter & Setter ======== */

   
    /* ====== Méthodes d'instance ====== */

    /**
     * Actualiser la console selon l'objet observable
     */
    @Override
    public void actualise() {
        System.out.println("Generation n°" + jeu.getGeneration() + ":");
        System.out.println("  - Nombre de cellule vivante: " + jeu.getNbVivantes());
    }
    
}
