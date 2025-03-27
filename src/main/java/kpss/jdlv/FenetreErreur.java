package kpss.jdlv;

import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 * Classe qui permet de gerer les fenetres d'erreur
 * @author Kilian POUSSE
 * @since 2025-03-27
 */
public class FenetreErreur {
    
    /**
     * Ouvre une fenetre d'erreur
     * @param app Application du jeu de la vie
     * @param e Exception
     */
    public static void ouvrir(App app, Exception e) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(
            app.getFenetre(),                   
            e.getLocalizedMessage(),                
            "Erreur: " + e.getClass().getSimpleName(),               
            JOptionPane.ERROR_MESSAGE
        );
    }

}
