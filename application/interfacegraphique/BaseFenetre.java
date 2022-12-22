package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFenetre {
    public static void defautfenetre(JFrame frame, int width, int height){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#ffdfba"));
        frame.setPreferredSize(new Dimension(width, height));
        Image logo = Toolkit.getDefaultToolkit().getImage("./application/data/medias/shark.png");  
        frame.setIconImage(logo);
    }  
}
