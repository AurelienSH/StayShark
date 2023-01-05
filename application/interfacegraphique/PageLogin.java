package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
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
      Page.basefenetre(framebis,400,150);

      JLabel userLabel = new JLabel("Votre login", SwingConstants.CENTER);  
      JTextField textField1 = new JTextField("Halbout1",15);
      JButton connect = new JButton("Se connecter"); 
      connect.setForeground(Color.white);
      connect.setBackground(Color.decode("#ffb3ba"));
      JPanel newPanel = new JPanel(new GridLayout(2, 1));
      newPanel.setBackground( Color.decode("#ffdfba") );  
      newPanel.add(userLabel);
      newPanel.add(textField1); 
      newPanel.add(connect); 
      framebis.add(newPanel, BorderLayout.CENTER); 
      connect.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
              String userlogin = textField1.getText();
              if(personne == "élève"){
                try{
                  if(CsvReader.loginExiste(userlogin,"./application/data/dataeleve.csv")){
                    Map<String,String> preinfoUser=CsvReader.liseurCsv("./application/data/dataeleve.csv");
                    Map<String, String> infoUser = new HashMap<String,String>();
                    for(Map.Entry<String, String> entry : preinfoUser.entrySet()){
                      String login = entry.getKey();
                      if(userlogin.contains(login)){
                        String[] infos = entry.getValue().split(",");
                        infoUser.put("login",login);
                        infoUser.put("nom",infos[0]);
                        infoUser.put("prénom",infos[1]);
                        infoUser.put("langue",infos[2]);
                      }
                    }
                    //ajouter fonction de lecture du csv pour choper nom login itout
                      JOptionPane.showMessageDialog(framebis, "Bienvenue "+infoUser.get("prénom")+" "+infoUser.get("nom")+" !");
                      framebis.dispose();
                      PageEleve framebis = new PageEleve(frameAJeter,infoUser);
                      
              }else{
                  JOptionPane.showMessageDialog(framebis, "Login incorrect");
              }}
              catch(Exception e){
                  System.out.println("ALERTE PROBLEME "+e.getClass());
              }
              }else if (personne == "prof"){
                try{
                  if(CsvReader.loginExiste(userlogin,"./application/data/dataprof.csv")){
                    Map<String,String> preinfoUser=CsvReader.liseurCsv("./application/data/dataprof.csv");
                    Map<String, String> infoUser = new HashMap<String,String>();
                    for(Map.Entry<String, String> entry : preinfoUser.entrySet()){
                      String login = entry.getKey();
                      if(userlogin.contains(login)){
                        String[] infos = entry.getValue().split(",");
                        infoUser.put("login",login);
                        infoUser.put("nom",infos[0]);
                        infoUser.put("prénom",infos[1]);
                        infoUser.put("langue",infos[2]);
                      }
                    }
                      JOptionPane.showMessageDialog(framebis, "Bienvenue "+infoUser.get("prénom")+" "+infoUser.get("nom")+" !");
                      framebis.dispose();
                      PageProf framebis = new PageProf(frameAJeter, infoUser);
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
