package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

//javac -encoding utf8 ./application/interfacegraphique/Home.java --> pour que ça affiche bien les caractères
// java application.interfacegraphique.Home

public class Home implements ActionListener
{
    public static void main(String[] args) 
    {
        //Fenêtre Home

        JFrame framehome = new JFrame("StayShark");
        framehome.getContentPane().setBackground(Color.decode("#ffdfba"));

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
        //faire d'abord ptite frame login, nom, prénom puis  
        btnprof.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				framehome.dispose();
				JFrame framebis = new JFrame("StayShark");
                Image logo = Toolkit.getDefaultToolkit().getImage("./application/data/médias/shark.png");  
                framebis.setIconImage(logo);
                framebis.getContentPane().setBackground(Color.decode("#ffdfba"));
                framebis.setSize(800, 600);
                framebis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                framebis.setLocationRelativeTo(null);
                framebis.setVisible(true);
			}});
        btnEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				framehome.dispose();
				JFrame framebis = new JFrame("StayShark");
                framebis.getContentPane().setBackground(Color.decode("#ffdfba"));
                Image logo = Toolkit.getDefaultToolkit().getImage("./application/data/médias/shark.png");  
                framebis.setIconImage(logo);
                framebis.setSize(800, 600);
                framebis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                framebis.setLocationRelativeTo(null);
                framebis.setVisible(true);
			}});
  
        JPanel panel = new JPanel();
        panel.setBackground( Color.decode("#ffdfba") );
        panel.add(btn1); 
        panel.add(btn2);
        JLabel shark = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./application/data/médias/sharkDancing.gif")));
        framehome.add(shark);

        // Ajouter label et panel au frame
        framehome.setLayout(new GridLayout(3, 1));
        framehome.add(panel);

        Image logo = Toolkit.getDefaultToolkit().getImage("./application/data/médias/shark.png");  
        framehome.setIconImage(logo);
        framehome.pack();

        framehome.setSize(800, 600);
        framehome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framehome.setLocationRelativeTo(null);
        framehome.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        JFrame framebis = new JFrame("StayShark");
        framebis.getContentPane().setBackground(Color.decode("#ffdfba"));
        framebis.setSize(800, 600);
        framebis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
      }
}