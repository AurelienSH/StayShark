package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * Créé la page que l'élève verra.
 */
public class PageEleve extends Page {
    JFrame frame;
    /**
     * Page que l'élève verra dans l'application (contenant les exos et ses notes)
     * @param frameAJeter Jframe (PageHome) qui sera tué avant la création de la frame pour l'élève
     */
    public PageEleve(JFrame frameAJeter){
        frameAJeter.dispose();
		JFrame framebis = new JFrame("StayShark");
        Page.basefenetre(framebis,800,600);

        // TO DO
        /*
         * - plateforme de suivi de ses notes/level : 
         *                      demander langue si jamais il y en plusieurs
         *                      afficher lvl
         *                      afficher historique des notes
         *                      afficher dans combien de temps le lvl est atteint
         * - plateforme d'exos : 
         *                      choix de la langue si plusieurs
         *                      exos
         *                      récap de toutes les fautes après l'exo ou pendant?
         */

        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        
        frame=framebis; //permet d'utiliser la frame dans une autre méthode
    }
}