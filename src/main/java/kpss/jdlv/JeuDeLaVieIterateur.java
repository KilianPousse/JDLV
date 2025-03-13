package kpss.jdlv;

import java.util.*;

/**
 * Classe représentant un itérateur de la classe 'JeuDeLaVie'. Cette itérateur parcours les cellules
 * de la grille du jeu de la vie.
 * @author Kilian POUSSE
 * @since 2025-03-13
 */
public class JeuDeLaVieIterateur implements Iterator<Cellule> {

    /* ===== Variables d'instance ===== */

    /** Grille du jeu de la vie */
    private Cellule[][] grille;

    /* Indice sur l'axe X */
    private int i = 0;

    /* Indice sur l'axe Y */
    private int j = 0;


    /* ====== Constructeur ====== */

    /**
     * Constructeur d'un iterateur du jeu de la vie
     * @param grille Grille du jeu de la vie
     */
    public JeuDeLaVieIterateur(Cellule[][] grille) {
        this.grille = grille;
    }



    /* ====== Méthodes classes ====== */

    /**
     * Verifie si l'iterateur peu continuer
     * @return Vrai si il peut continuer
     */
    @Override
    public boolean hasNext() {
        return i < grille.length && j < grille[0].length;
    }

    /**
     * Renvoie la prochaine cellule de la grille du jeu de la vie
     * @return prochaine cellule de la grille du jeu de la vie
     */
    @Override
    public Cellule next() {
        if(!hasNext()) {
            throw new NoSuchElementException("Plus de cellules disponibles.");
        }

        Cellule cellule = grille[i][j];

        j++;
        if(j >= grille[0].length) {
            j = 0;
            i++;
        }

        return cellule;
    }
}
