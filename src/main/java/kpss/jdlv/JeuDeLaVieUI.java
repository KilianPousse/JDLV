package kpss.jdlv;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    /** Zoom à appliquer sur l'image (%)*/
    private int zoom = 100;

    /** Indique si nous sommes en train de glisser */
    private boolean dragging = false;  

    /** Point de départ du drag */
    private Point dragStartPoint = null; 

    /** Décalage actuel pour le décalage du rendu */
    private Point dragPoint = new Point(0, 0);



    /* =========== Constructeurs =========== */

    /**
     * Constructeur d'une interface du JDLV
     * @param jeu Jeu de la vie
     * @param taille Taille d'une cellule
     */
    public JeuDeLaVieUI(App app, JeuDeLaVie jeu, int taille) {
        this.jeu = jeu;
        this.jeu.attacheObservateur(this);
        this.taille = taille;

        this.addMouseWheelListener((e) -> zoomer(zoom - e.getWheelRotation() * 10, app));

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                // Démarre le drag lorsque la souris est enfoncée
                dragging = true;
                dragStartPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Arrête le drag lorsque la souris est relâchée
                dragging = false;
                dragStartPoint = null;
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if(dragging) {
                    // Calculer le déplacement basé sur la différence entre la position actuelle et celle du début du drag
                    int deltaX = e.getX() - dragStartPoint.x;
                    int deltaY = e.getY() - dragStartPoint.y;

                    // Mettre à jour l'point pour déplacer la vue
                    dragPoint.translate(deltaX, deltaY);

                    // Repeindre l'interface avec le nouvel point
                    dragStartPoint = e.getPoint();
                    actualise();
                }
            }
        });

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
        return new Dimension(taille * jeu.getTaille(), taille * jeu.getTaille());
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
        if(App.MINI_ZOOM > zoom || zoom > App.MAX_ZOOM) return;
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

        Regle regle = jeu.getRegle();

        Graphics2D g2d = (Graphics2D) g;

        double echelle = ((double) zoom) / 100;
        int longueur = jeu.getTaille() * taille;
        int largeur = jeu.getTaille() * taille;

        // Appliquer l'point à la translation de l'affichage
        int x = (int) ((getWidth() - longueur * echelle) / 2) + dragPoint.x;
        int y = (int) ((getHeight() - largeur * echelle) / 2) + dragPoint.y;

        g2d.translate(x, y);
        g2d.scale(echelle, echelle);

        for(Cellule cellule: jeu) {
            g2d.setColor(cellule.getCouleur(regle));
            g2d.fillRect(cellule.getX() * taille, cellule.getY() * taille, taille, taille);
        }

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(0, 0, jeu.getTaille() * taille, jeu.getTaille() * taille);
    }

    /**
     * Fait zoomer le rendu du jeu de la vie
     * @param zoom Nouvelle coef de zoom
     * @param app Application du JDLV
     */
    public void zoomer(int zoom, App app) {
        if(jeu.estVide()) return;
        setZoom(zoom);
        app.notifieObservateurs();
    }

    /**
     * Réinitialise le drag
     */
    public void resetDrag() {
        dragPoint = new Point(0, 0);
        dragging = false;
    }

}
