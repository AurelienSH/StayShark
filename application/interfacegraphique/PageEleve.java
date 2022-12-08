package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class PageEleve extends Page {
    PageEleve(JFrame frameAJeter){
        frameAJeter.dispose();
		JFrame framebis = new JFrame("StayShark");
        Image logo = Toolkit.getDefaultToolkit().getImage("./application/data/médias/shark.png");  
        framebis.setIconImage(logo);
        framebis.getContentPane().setBackground(Color.decode("#ffdfba"));
        framebis.setSize(800, 600);
        framebis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
    }
}