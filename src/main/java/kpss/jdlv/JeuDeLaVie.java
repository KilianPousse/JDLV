package kpss.jdlv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class JeuDeLaVie implements Observable, Iterable<Cellule>, Serializable {

    /* ======= Constantes de classe ======== */

    /** Valeur par default de 'xMax' */
    public static final int DEFAUT_XMAX = 100;

    /** Valeur par default de 'yMax' */
    public static final int DEFAUT_YMAX = 100;

    /** Valeur par default de 'ration' */
    public static final double DEFAUT_RATION = 5;


    
    /* ======= Variables d'instances ======= */
    
    /** Grille qui stocke les cellules du JDLV */
    private Cellule[][] grille;

    /** Coordonnée maximal sur l'axe X */
    private int xMax;

    /** Coordonnée maximal sur l'axe Y */
    private int yMax;

    /** Liste des observateurs (Observateur) */
    private List<Observateur> observateurs = new ArrayList<>();

    /** Liste des commandes du jeu de la vie (Commande) */
    private List<Commande> commandes = new ArrayList<>();

    /** Regle du jeu effectuée sur les cellules (Visiteur) */
    private Regle regle;

    /** Numéro de la génération */
    private long generation;

    /** Nombre de cellules vivantess */
    private int nbVivantes;

    /** Donnees liees au nombre d'individu par generation */
    private List<Integer> donnees = new ArrayList<>();


    /* ========= Méthodes de classe ========= */

    /**
     * Chargement d'un jeu de la vie
     * @param path Chemin vers le jeu de la vie
     * @return Instance du jeu de la vie sauvegardée
     */
    public static JeuDeLaVie load(String path) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (JeuDeLaVie) ois.readObject();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sauvegarde l'instance du jeu de la vie
     * @param jeu Jeu au sauvegarder
     * @param path Chemin de sauvegarde
     */
    public static void save(JeuDeLaVie jeu, String path) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(jeu);
            System.out.println("Jeu de la vie sauvegardee !");
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    

    /* =========== Constructeurs =========== */

    /**
     * Constructeur d'un je de la vie avec des parametres.
     * @param xMax Coordonnée maximal sur l'axe X
     * @param yMax Coordonnée maximal sur l'axe Y
     */
    public JeuDeLaVie(int xMax, int yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.grille = new Cellule[xMax][yMax];
        this.regle = new RegleClassique(this);
        this.generation = 0;
    }

    /**
     * Constructeur d'un jeu de la vie avec des parametres
     * par defaut.
     */
    public JeuDeLaVie() {
        this.xMax = DEFAUT_XMAX;
        this.yMax = DEFAUT_YMAX;
        this.grille = new Cellule[DEFAUT_XMAX][DEFAUT_YMAX];
        this.regle = new RegleClassique(this);
        this.generation = 0;
    }


    /* ========== Getter & Setter ========== */

    /**
     * Getter: Recupération d'une cellule de la grille du JDLV
     * @return Grille qui stocke les cellules du JDLV 
     */
    public Cellule getGrilleXY(int x, int y) {
        return grille[x][y];
    }

    /**
     * Getter: Recupération de la coordonnée maximal sur l'axe X
     * @return Coordonnée maximal sur l'axe X
     */
    public int getXMax() {
        return xMax;
    }

    /**
     * Getter: Recupération de la coordonnée maximal sur l'axe Y
     * @return Coordonnée maximal sur l'axe Y
     */
    public int getYMax() {
        return yMax;
    }

    /**
     * Getter: Recupération de la generation
     * @return generation
     */
    public long getGeneration() {
        return generation;
    }

    /**
     * Setter: Affectation de la generation
     * @param generation generation
     */
    public void setGeneration(long generation) {
        this.generation = generation;
    }

    /**
     * Getter: Recupération de la regle du jeu
     * @return regle du jeu en vigueur
     */ 
    public Regle getRegle() {
        return regle;
    }

    /**
     * Setter: Affectation d'une nouvelle regle du jeu
     * @param regle nouvelle regle du jeu
     */
    public void setRegle(Regle regle) {
        this.regle = regle;
    }

    /**
     * Getter: Recupération de le nnombre de cellules vivantes
     * @return Nombre de cellules vivantes
     */
    public int getNbVivantes() {
        return nbVivantes;
    }

    /**
     * Getter: Recupération des donnees liees au nombre d'individu
     * par generation.
     * @return donnees liees au nombre d'individu par generation
     */
    public List<Integer> getDonnees() {
        return donnees;
    }

    /* ======= Méthodes d'instance ========= */

    /**
     * Initialiser la grille selon 'xMax' et 'yMax'
     * @param ration ration qui definie la chance d'avoir une cellule morte ou vivante
     */
    public void initialiseGrille(double ration) {
        // Initialisation de la varible aléatoire
        Random rand = new Random();

        // Parcours de la grille de jeu
        for(int i=0; i<xMax; i++) {
            for(int j=0; j<yMax; j++) {

                // Etat de la cellule
                CelluleEtat etat = null;

                double rdm = rand.nextDouble();
                
                // Génération de l'etat de la cellule
                if(rdm < (ration/100.0)) {
                    // VIVANTE :)
                    etat = CelluleEtatVivant.getInstance();
                }
                else {
                    // MORTE :(
                    etat = CelluleEtatMort.getInstance();
                }

                grille[i][j] = new Cellule(i, j, etat);
            }
        }
    }

    /**
     * Initialiser la grille selon 'xMax' et 'yMax'
     */
    public void initialiseGrille() {
        initialiseGrille(DEFAUT_RATION);
    }

    /**
     * Verifie la validité d'une position dans le jeu.
     * @param x Coordonnée sur l'axe X
     * @param y Coordonnée sur l'axe Y
     * @return Vrai si la position est valide, Faux sinon
     */
    public boolean estValide(int x, int y) {
        return ((0 <= x) && (x < xMax)) && ((0 <= y) && (y < yMax));
    }

    /**
     * Permet d'attacher un observateur.
     * @param o Observateur à attacher
     */
    @Override
    public void attacheObservateur(Observateur o) {
        observateurs.add(o);
    }

    /**
     * Permet de déttacher un observateur.
     * @param o Observateur à déttacher
     */
    @Override
    public void dettacheObservateur(Observateur o) {
        observateurs.remove(o);
    }

    /**
     * Permet de notifer les observateurs
     */
    @Override
    public void notifieObservateurs() {
        for(Observateur o: observateurs) {
            o.actualise();
        }
    }

    /**
     * Ajouter une commande à effectuer
     * @param c Commande à effectuer
     */
    public void ajouteCommande(Commande c) {
        commandes.add(c);
    }

    /**
     * Executer les commandes
     */
    public void executeCommandes() {
        for(Commande c : commandes) {
            c.executer();
        }
        commandes.clear();
    }

    /**
     * Actualisation des cellules
     */
    public void distribueVisiteur() {
        for(Cellule cellule: this) {
            cellule.accepte(regle);
        }
    }

    /**
     * Calculer la nouvelle generation
     */
    public long calculerGeneration() {
        generation += 1 ;

        distribueVisiteur();
        executeCommandes();

        nbVivantes = 0;
        for(Cellule cellule: this) {
            if(cellule.estVivante()) {
                nbVivantes++;
            }
        }

        donnees.add(nbVivantes);

        
        notifieObservateurs();

        return generation;
    }

    /**
     * Ajouter une cellule vivante dans le jeu
     * @param x Coordonnées sur l'axe X
     * @param y Coordonnées sur l'axe Y
     */
    public void ajouteCellule(int x, int y) {
        grille[x][y].vit();
    }

    /**
     * Permet de rendre le jeu de la vie iterable.
     * @return Iterateur du jeu de la vie
     */
    public Iterator<Cellule> iterator() {
        return new JeuDeLaVieIterateur(grille);
    }

    /**
     * Verifie si jeu est vide
     * @return
     */
    public boolean estVide() {
        return false;
    }
}
