package kpss.jdlv.ui;

import javax.swing.*;
import java.awt.*;
import kpss.jdlv.*;

/**
 * Classe qui représente un écran avec les informations sur le Jeu de la Vie.
 * @author Kilian POUSSE
 * @since 2025-03-14
 */
public class EcranInfo extends JPanel implements Observateur {

    /** Jeu de la vie */
    private final JeuDeLaVie jeu;

    /** Label des informations */
    private final JLabel label;

    /**
     * Constructeur d'un écran d'information.
     * @param jeu Jeu de la Vie
     */
    public EcranInfo(JeuDeLaVie jeu, JDLVGraphique graphique) {
        this.jeu = jeu;
        
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "Données"
        ));
        setOpaque(false); 

        label = new JLabel("", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
        add(graphique);

        actualise(); 
    }

    /**
     * Actualisation du composant
     */
    @Override
    public void actualise() {
        SwingUtilities.invokeLater(() -> label.setText(String.format(
            "<html>Numéro de la Génération: %4d<br>Nombre d'individus: %d</html>",
            jeu.getGeneration(),
            jeu.getNbVivantes()
        )));
    }
}
