package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public abstract class BaseFenetre {
    public static void defautfenetre(JFrame frame){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#ffdfba"));
        frame.setPreferredSize(new Dimension(800, 600));
        Image logo = Toolkit.getDefaultToolkit().getImage("./application/data/medias/shark.png");  
        frame.setIconImage(logo);
    }  
}
