package kpss.jdlv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kpss.jdlv.exception.*;

public class JeuDeLaVie implements Observable, Iterable<Cellule>, Serializable {

    /* ======= Constantes de classe ======== */

    /** Valeur par default de 'taille' */
    public static final int DEFAUT_TAILLE = 200;

    /** Valeur par default de 'ration' */
    public static final double DEFAUT_RATION = 5;

    /** Taille maximum applicable sur le jeu */
    public static final int MAX_TAILLE = 2000;

    /** Taille minimal applicable sur le jeu */
    public static final int MINI_TAILLE = 10;


    
    /* ======= Variables d'instances ======= */
    
    /** Grille qui stocke les cellules du JDLV */
    private Cellule[][] grille;

    /** Taille de la grille */
    private int taille;

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
     * @throws IOException 
     * @throws ClassNotFoundException 
     */
    public static JeuDeLaVie load(String path) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (JeuDeLaVie) ois.readObject();
        }
        catch(Exception e) {
            throw new LoadJDLVException(path);
        }
    }


    /**
     * Sauvegarde l'instance du jeu de la vie
     * @param jeu Jeu à sauvegarder
     * @param path Chemin de sauvegarde
     * @throws IOException
     */
    public static void save(JeuDeLaVie jeu, String path) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(jeu);
            System.out.println("Jeu de la vie sauvegardé !");
        }
        catch(Exception e) {
            throw new SaveJDLVException(path);
        }
    }

    

    /* =========== Constructeurs =========== */

    /**
     * Constructeur d'un je de la vie avec des parametres.
     * @param taille Taille de la grille
    * @throws Exception 
    */
    public JeuDeLaVie(int taille) throws Exception {
        if(taille > MAX_TAILLE) {
            throw new MaxTailleException();
        }
        if(MINI_TAILLE > taille) {
            throw new MiniTailleException();
        }
        this.taille = taille;
        this.grille = new Cellule[taille][taille];
        this.regle = new RegleClassique();
        regle.setJeu(this);
        this.generation = 0;
    }

    /**
     * Constructeur d'un jeu de la vie avec des parametres
     * par defaut.
     */
    public JeuDeLaVie() {
        this.taille = 0;
        this.grille = new Cellule[taille][taille];
        this.regle = new RegleClassique();
        regle.setJeu(this);
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
     * @return Taille de la grille
     */
    public int getTaille() {
        return taille;
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
        for(int i=0; i<taille; i++) {
            for(int j=0; j<taille; j++) {

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
        return ((0 <= x) && (x < taille)) && ((0 <= y) && (y < taille));
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
     * @return Numero de la generation
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
