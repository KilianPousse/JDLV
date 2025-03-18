package kpss.jdlv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    /** Zoom à appliquer sur l'image (%)*/
    private int zoom = 100;


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

    /**
     * Setter: Affectation d'un jeu de la vie
     * @param jeu jeu de la vie
     */
    public void setJeu(JeuDeLaVie jeu) {
        this.jeu = jeu;
    }

    /**
     * Getter: Recuperation du coefficient de zoom
     * @return coefficient de zoom (%)
     */
    public int getZoom() {
        return zoom;
    }

    /**
     * Setter: Affectation du coefficient de zoom
     * @param zoom coefficient de zoom (%)
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
        actualise();
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
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double scale = ((double)zoom)/100;
        g2d.scale(scale, scale);

        for(Cellule cellule: jeu) {
            if(cellule.estVivante()) {
                g2d.setColor(Color.BLUE);
                g2d.fillOval(cellule.getX() * taille, cellule.getY() * taille, taille, taille);
            }
        }

        g2d.setColor(Color.BLACK); 
        g2d.setStroke(new BasicStroke(1)); 
        g2d.drawRect(0, 0, jeu.getXMax() * taille, jeu.getYMax() * taille);
    }

}
