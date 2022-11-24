package application;

import javax.swing.*;
import java.awt.*;

//javac -encoding utf8 Home.java --> pour que ça affiche bien les caractères
public class Home 
{
    public static void main(String[] args) 
    {
        // Définissez le frame
        JFrame frame = new JFrame("StayShark");
        frame.getContentPane().setBackground(Color.decode("#ffdfba"));

        JLabel label1 = new JLabel("<html><div style='text-align:center; font-size: 440%'>Bienvenue sur StayShark !</div><br/><div style='text-align:center; font-size: 180%'>Êtes-vous professeur.e ou étudiant.e ?</div></html>",SwingConstants.CENTER);
        frame.add(label1);

        // Définir les boutons
        JButton btn1 = new JButton("Professeur.e"); 
        btn1.setForeground(Color.white);
        btn1.setFont(new Font("Apple Casual", Font.BOLD, 25));
        JButton btn2 = new JButton("Etudiant.e");
        btn2.setForeground(Color.white);
        btn2.setFont(new Font("Apple Casual", Font.BOLD, 25));
        
        btn1.setBackground(Color.decode("#ffb3ba"));
        btn2.setBackground(Color.decode("#ffb3ba"));
  
        JPanel panel = new JPanel();
        panel.setBackground( Color.decode("#ffdfba") );
        panel.add(btn1); 
        panel.add(btn2);
        JLabel shark = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./application/sharkDancing.gif")));
        frame.add(shark);

        // Ajouter label et panel au frame
        frame.setLayout(new GridLayout(3, 1));
        frame.add(panel);

        Image logo = Toolkit.getDefaultToolkit().getImage("./application/shark.png");  
        frame.setIconImage(logo);
        frame.pack();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
          
    }
}