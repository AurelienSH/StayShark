package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * Créé la page que le prof verra
 */
public class PageProf extends Page {
    JFrame frame;
    /**
     * Page que le prof verra dans l'application (contenant les exos à déposer et les notes de ses élèves).
     * @param frameAJeter Jframe (PageHome) qui sera tué avant la création de la frame pour le prof
     */
    public PageProf(JFrame frameAJeter){
        frameAJeter.dispose();
		JFrame framebis = new JFrame("StayShark");
        Page.basefenetre(framebis,800,600);

        // TO DO
        /*
         * - plateforme de récupération : 
         *                  récupérer langue prof, si plusieurs langues demander pour laquelle il dépose ses exos 
         *                  demander pour quel level (si on fait système de lvl) ou alors c'est beaucoup d'exos et le système de lvl se fait seul
         *                  demander le niveau de dureté de notation ptet??
         *                  --> les mettre sous forme de csv ou txt? (pour qu'ils soient récupérable pour après)
         * - plateforme de suivi des notes/levels : 
         *                  demander élève à checker ou alors tous les afficher et système de recherche?
         */

        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        frame=framebis; //permet d'utiliser la frame dans une autre méthode
    }
}