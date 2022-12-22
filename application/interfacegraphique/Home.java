package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

//javac -encoding utf8 ./application/interfacegraphique/Home.java --> pour que ça affiche bien les caractères
// java application.interfacegraphique.Home

// TO DO :

// faire une classe de home pour le mettre dans le contrôleur : cf --> https://www.javatpoint.com/login-form-java --> en gros le mettre dans le constructeur
// déclarer en amont toutes les variables dans ma classe cPaBo

public class Home implements ActionListener
{
    public static void main(String[] args) 
    {
        //Fenêtre Home

        JFrame framehome = new JFrame("StayShark");
        BaseFenetre.defautfenetre(framehome,800,600);

        JLabel labelhome = new JLabel("Bienvenue sur StayShark !");
        labelhome.setFont(new Font("Verdana", Font.BOLD, 28));
        labelhome.setHorizontalAlignment(JLabel.CENTER);
        framehome.add(labelhome);

        // Définir les boutons
        JButton btnprof = new JButton("Professeur.e"); 
        btnprof.setForeground(Color.white);
        btnprof.setFont(new Font("Apple Casual", Font.BOLD, 25));
        JButton btnEleve = new JButton("Etudiant.e");
        btnEleve.setForeground(Color.white);
        btnEleve.setFont(new Font("Apple Casual", Font.BOLD, 25));
        
        btnprof.setBackground(Color.decode("#ffb3ba"));
        btnEleve.setBackground(Color.decode("#ffb3ba"));
        btnprof.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                PageLogin framelogin = new PageLogin(framehome,"prof");
			}});
        btnEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                PageLogin framelogin = new PageLogin(framehome,"élève");
			}});
  
        JPanel panel = new JPanel();
        panel.setBackground( Color.decode("#ffdfba") );
        panel.add(btnprof); 
        panel.add(btnEleve);
        JLabel shark = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./application/data/medias/sharkDancing.gif")));
        framehome.add(shark);

        // Ajouter label et panel au frame
        framehome.setLayout(new GridLayout(3, 1));
        framehome.add(panel);

        framehome.pack();
        framehome.setLocationRelativeTo(null);
        framehome.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        JFrame framebis = new JFrame("StayShark");
        BaseFenetre.defautfenetre(framebis,800,600);
      }
}