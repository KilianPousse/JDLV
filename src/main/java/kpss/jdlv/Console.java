package kpss.jdlv;

/**
 * Classe qui observe le jeu de la vie et affiche des informations d'une génération.
 * @author Kilian POUSSE
 * @since 2025-03-13
 */
public class Console implements Observateur {

    /* ====== Variables d'instance ====== */

    /** Jeu de la vie */
    private JeuDeLaVie jeu;


    /* ====== Constructeurs ====== */

    public Console(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    /* ====== Méthodes d'instance ====== */

    /**
     * Actualiser la console selon l'objet observable
     */
    @Override
    public void actualise() {
        System.out.println("Generation n°" + jeu.getGeneration() + ":");

        int nbVivantes = 0;
        for(Cellule cellule: jeu) {
            if(cellule.estVivante()) {
                nbVivantes++;
            }
        }

        System.out.println("  - Nombre de cellule vivante: " + nbVivantes);
    }
    
}
