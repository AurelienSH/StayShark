package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

/*
 * TO DO : 
 * ajouter espace de crédit des gifs/photos?
 */

/**
 * Contient la frame et ses objets de la page d'accueil et toutes les actions qui résultent de cette page (redirection vers PageProf ou PageEleve après login ou redirection vers l'inscription).
 */
public class PageHome extends Page
{
    /**
     * Création de la frame d'accueil de l'application et redirection vers la page de l'élève ou du prof après login ou de l'inscription.
     */
    public PageHome(){

        // Définition de la frame
        JFrame framehome = new JFrame("StayShark");
        Page.basefenetre(framehome,800,600);

        // Texte de la frame
        JLabel labelhome = new JLabel("Bienvenue sur StayShark !");
        labelhome.setFont(new Font("Verdana", Font.BOLD, 28));
        labelhome.setHorizontalAlignment(JLabel.CENTER);
        framehome.add(labelhome);

        // Boutons de la frame
        JButton btnprof = new JButton("Professeur.e"); 
        btnprof.setForeground(Color.white);
        btnprof.setFont(new Font("Apple Casual", Font.BOLD, 25));
        JButton btnEleve = new JButton("Etudiant.e");
        btnEleve.setForeground(Color.white);
        btnEleve.setFont(new Font("Apple Casual", Font.BOLD, 25));
        JButton btnInscription = new JButton("Inscription");
        btnInscription.setForeground(Color.white);
        btnInscription.setFont(new Font("Apple Casual", Font.BOLD, 25));
        
        btnprof.setBackground(Color.decode("#ffb3ba"));
        btnEleve.setBackground(Color.decode("#ffb3ba"));
        btnInscription.setBackground(Color.decode("#ffb3ba"));

        // Liens vers les autres fenêtres (prof-élève) de l'appli + login
        btnprof.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                PageLogin framelogin = new PageLogin(framehome,"prof");
			}});
        btnEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                PageLogin framelogin = new PageLogin(framehome,"élève");
			}});
        btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                PageInscription frameinscription = new PageInscription();
			}});
        
        // Définition du panel
        JPanel panel = new JPanel();
        panel.setBackground( Color.decode("#ffdfba") );
        panel.add(btnprof); 
        panel.add(btnEleve);
        panel.add(btnInscription);
        JLabel shark = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./application/data/medias/sharkDancing.gif")));
        framehome.add(shark);

        // Ajout gif et panel au frame
        framehome.setLayout(new GridLayout(3, 1));
        framehome.add(panel);

        framehome.pack();
        framehome.setLocationRelativeTo(null);
        framehome.setVisible(true);
    }
}