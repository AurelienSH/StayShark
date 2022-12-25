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
        BaseFenetre.defautfenetre(framebis,800,600);
        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        
        frame=framebis; //permet d'utiliser la frame dans une autre méthode
    }
}