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
        BaseFenetre.defautfenetre(framebis,800,600);
        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        frame=framebis; //permet d'utiliser la frame dans une autre méthode
    }
}