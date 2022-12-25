package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import application.controleur.CsvReader;

/**
 * Créé la petite frame qui permet de se logger pour le prof ou l'élève.
 */
public class PageLogin extends Page {
  /**
   * Créé une fenêtre qui permet de se logger au professeur ou à l'élève.
   * @param frameAJeter le frame qui sera tué si jamais le login est réussi.
   * @param personne "prof" ou "élève" en fonction de qui se connecte
   */
    PageLogin(JFrame frameAJeter, String personne){
      JFrame framebis = new JFrame("StayShark");
      BaseFenetre.defautfenetre(framebis,400,150);

      JLabel userLabel = new JLabel();  
      userLabel.setText("Votre login");
      JTextField textField1 = new JTextField(15);
      JButton b1 = new JButton("Se connecter"); 
      JPanel newPanel = new JPanel(new GridLayout(2, 1));  
      newPanel.add(userLabel);
      newPanel.add(textField1); 
      newPanel.add(b1); 
      framebis.add(newPanel, BorderLayout.CENTER); 
      b1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
              String userlogin = textField1.getText();
              if(personne == "élève"){
                try{
                  if(CsvReader.loginExiste(userlogin,"./application/data/dataeleve.csv")){
                      JOptionPane.showMessageDialog(framebis, "Bienvenue "+userlogin+" !");
                      framebis.dispose();
                      PageEleve framebis = new PageEleve(frameAJeter);
                      
              }else{
                  JOptionPane.showMessageDialog(framebis, "Login incorrect");
              }}
              catch(Exception e){
                  System.out.println("ALERTE PROBLEME "+e.getClass());
              }
              }else if (personne == "prof"){
                try{
                  if(CsvReader.loginExiste(userlogin,"./application/data/dataprof.csv")){
                      JOptionPane.showMessageDialog(framebis, "Bienvenue "+userlogin+" !");
                      framebis.dispose();
                      PageProf framebis = new PageProf(frameAJeter);
                      
              }else{
                  JOptionPane.showMessageDialog(framebis, "Login incorrect");
              }}
              catch(Exception e){
                  System.out.println("ALERTE PROBLEME "+e.getClass());
              }
              }
              
    }});  
      framebis.setTitle("Login");  
      framebis.pack();
      framebis.setLocationRelativeTo(null);
      framebis.setVisible(true);
}
}
