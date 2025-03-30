package kpss.jdlv;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * Classe représentant une règle de type "Random Rainbow" pour le jeu de la vie.
 * Cette règle permet à une cellule vivante de mourir et à une cellule morte de revivre de manière aléatoire.
 * Les cellules vivantes prennent une couleur aléatoire parmi une liste de couleurs prédéfinies.
 * @author Kilian POUSSE
 * @since 2025-03-30
 */
public class RegleRandomRainbow extends AbstractRegle {

    /** Couleurs possibles de la cellule vivante */
    private static final List<Color> COLORS = new ArrayList<>();
    static {
        COLORS.add(Color.RED);
        COLORS.add(Color.GREEN);
        COLORS.add(Color.BLUE);
        COLORS.add(Color.YELLOW);
        COLORS.add(Color.MAGENTA);
        COLORS.add(Color.CYAN);
    }
    
    /**
     * Creation d'une règle random rainbow
     */
    public RegleRandomRainbow() {
        super("Random Rainbow");
    }

    /**
     * <p>Visite d'une cellule vivante</p>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellVivante(Cellule cellule) {
        Random random = new Random();
        
        int n = random.nextInt(100) + 1;
        int max = random.nextInt(100) + 1;
        if(n <= max) {
            jeu.ajouteCommande(new CommandeMeurt(cellule));
        }
    }

    /**
     * <p>Visite d'une cellule morte</p>
     * @param cellule Cellule visitée et actualisée
     */
    @Override
    public void visiteCellMorte(Cellule cellule) {
        Random random = new Random();
        
        int n = random.nextInt(100) + 1;
        int max = random.nextInt(100) + 1;
        if(n <= max) {
            jeu.ajouteCommande(new CommandeVit(cellule));
        }
    }

    /**
     * Retourne une couleur aléatoire parmi les couleurs possibles
     * @return Couleur aléatoire
     */
    @Override
    public Color getCouleurVivante() {
        Random random = new Random();

        Color couleur = COLORS.get(random.nextInt(COLORS.size()));
        return couleur;
    }

    /**
     * Retourne une couleur aléatoire parmi les couleurs possibles
     * @return Couleur aléatoire
     */
    @Override
    public Color getCouleurGraph() {
        Random random = new Random();

        Color couleur = COLORS.get(random.nextInt(COLORS.size()));
        return couleur;
    }


}
