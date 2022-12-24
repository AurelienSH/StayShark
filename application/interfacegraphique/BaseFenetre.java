package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;

/**
 * Contient une méthode qui permet d'ajouter les paramètres de fenêtre par défaut sur chaque objet Jframe sur lequel il a été appelé.
 * @author Aurélien et Elisabeth
 */
public abstract class BaseFenetre {
    /**
     * Méthode qui prend la frame à modifier en entrée et va mettre les paramètres par défauts (choisi par nous) du visuel de la fenêtre.
     * @param frame fenêtre qu'on va modifier
     * @param width largeur de la fenêtre en px
     * @param height hauteur de la fenêtre en px
     */
    public static void defautfenetre(JFrame frame, int width, int height){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //méthode de fermeture de frame
        frame.getContentPane().setBackground(Color.decode("#ffdfba")); // définition de la couleur de fond de la frame
        frame.setPreferredSize(new Dimension(width, height)); //définition de la taille de la frame
        Image logo = Toolkit.getDefaultToolkit().getImage("./application/data/medias/shark.png");  // logo de la frame
        frame.setIconImage(logo); // implantation du logo de la frame
    }  
}
