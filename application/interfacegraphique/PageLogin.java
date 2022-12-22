package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class PageLogin extends Page {
    JFrame frame;
    PageLogin(){
		JFrame framebis = new JFrame("StayShark");
        BaseFenetre.defautfenetre(framebis,200,300);
        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        
        frame=framebis;
}
}
