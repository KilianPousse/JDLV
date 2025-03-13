package kpss.jdlv;

import javax.swing.*;
import java.awt.*;

/**
 * Classe representant l'interface du jeu de la vie.
 * @author Kilian POUSSE
 * @since 2025-03-11
 */
public class JeuDeLaVieUI extends JPanel implements Observateur {

    /* ======= Variables d'instances ======= */

    /** Jeu de la vie */
    private JeuDeLaVie jeu;

    /** Taille d'une cellule */
    private int taille;



    /* =========== Constructeurs =========== */

    /**
     * Constructeur d'une interface du JDLV
     * @param jeu Jeu de la vie
     * @param taille Taille d'une cellule
     */
    public JeuDeLaVieUI(JeuDeLaVie jeu, int taille) {
        this.jeu = jeu;
        this.jeu.attacheObservateur(this);
        this.taille = taille;
    }

    /* ========== Getter & Setter ========== */

    /**
     * Setter: Affectation de la taille des cellules
     * @param taille taille des cellules
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }

    /**
     * Getter: Récupération de la taille des cellules
     * @return taille des cellules
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Getter: Récupération de la dimension d'une cellule
     * @return dimension d'une cellule
     */
    public Dimension getDimension() {
        return new Dimension(taille, taille);
    }

    /**
     * Getter: Récupération de la dimension de la fenetre
     * @return dimension de la fenetre
     */
    public Dimension getDimFrame() {
        return new Dimension(taille * jeu.getXMax(), taille * jeu.getYMax());
    }

    /* ======= Méthodes d'instance ========= */

    /**
     * Permet d'actualiser l'interface.
     */
    @Override
    public void actualise() {
        repaint();
    }
    
    /**
     * Permet de dessiner les cellules
     * @param g graphique de reference
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);

        for(Cellule cellule: jeu) {
            if(cellule.estVivante()) {
                g.fillOval(cellule.getX()*taille, cellule.getY()*taille, taille, taille);
            }
        }
    }
}
