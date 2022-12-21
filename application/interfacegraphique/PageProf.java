package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class PageProf extends Page {
    JFrame frame;
    PageProf(JFrame frameAJeter){
        frameAJeter.dispose();
		JFrame framebis = new JFrame("StayShark");
        BaseFenetre.defautfenetre(framebis);
        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        frame=framebis; //permet d'utiliser la frame dans une autre méthode
    }
}