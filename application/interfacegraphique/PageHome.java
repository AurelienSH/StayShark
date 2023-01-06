package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * Créé la page d'accueil que l'élève et le professeur verront.
 * Ils pourront aller vers la page qui leur est destinée (PageProf ou PageEleve) après s'être connecté. S'ils n'ont pas de login ils peuvent s'inscire sur la page.
 */
public class PageHome extends Page
{
    /**
     * Accueil que le professeur ou l'élève voit (contenant un lien vers l'inscription dans l'application, un lien de log pour les professeurs et un lien de log pour les élèves).
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

        // Boutons de la frame (log prof, élève et inscription)
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
        
        // Définition du panel pour ajouter le requin qui danse
        JPanel panel = new JPanel();
        panel.setBackground( Color.decode("#ffdfba") );
        panel.add(btnprof); 
        panel.add(btnEleve);
        panel.add(btnInscription);
        JLabel shark = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./application/data/medias/sharkDancing.gif")));
        framehome.add(shark);

        framehome.setLayout(new GridLayout(3, 1));
        framehome.add(panel);

        framehome.pack();
        framehome.setLocationRelativeTo(null);
        framehome.setVisible(true);
    }
}