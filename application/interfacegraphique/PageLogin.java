package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import application.controleur.CsvReader;

/**
 * Créé la page dui permet de se logger pour le prof ou l'élève.
 * On a juste besoin d'insérer son login pour accéder à la page auquel on a le droit.
 */
public class PageLogin extends Page {
  /**
   * Créé une page qui permet de se logger au professeur ou à l'élève.
   * @param frameAJeter la page d'accueil qui sera tuée si jamais le login est réussi.
   * @param personne "prof" ou "élève" en fonction de qui se connecte
   */
    PageLogin(JFrame frameAJeter, String personne){
      // création de la page de login
      JFrame framebis = new JFrame("StayShark");
      Page.basefenetre(framebis,400,150);

      JLabel userLabel = new JLabel("Votre login", SwingConstants.CENTER);

      JTextField textField1 = new JTextField(15); // le login a insérer
      
      // bouton qui récupère le login
      JButton connect = new JButton("Se connecter"); 
      connect.setForeground(Color.white);
      connect.setBackground(Color.decode("#ffb3ba"));

      //Panel qui contient les éléments de la page login
      JPanel newPanel = new JPanel(new GridLayout(2, 1));
      newPanel.setBackground( Color.decode("#ffdfba") );  
      newPanel.add(userLabel);
      newPanel.add(textField1); 
      newPanel.add(connect); 
      framebis.add(newPanel, BorderLayout.CENTER); 

      // Quand on clique sur le bouton se connecter : on vérifie que le login existe dans le csv prof ou élève, si oui, c'est bon ! 
      connect.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent ae) {
              // on récupère le login
              String userlogin = textField1.getText();

              // on vérifie le login de l'élève dans le bon csv qui contient les données des élèves
              if(personne == "élève"){ 
                try{
                  if(CsvReader.loginExiste(userlogin,"./application/data/dataeleve.csv")){
                    Map<String,String> preinfoUser=CsvReader.liseurCsv("./application/data/dataeleve.csv");
                    // on écrit dans un dictionnaire le login, nom, prénom et langues de l'élève qui sera envoyé dans la page d'accueil de l'élève (pour créer apprenant)
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
                      PageEleve framebis = new PageEleve(frameAJeter,infoUser); // on créé la page d'accueil de l'élève et jette la page d'accueil de l'application et la page de login
                      
              }else{
                  JOptionPane.showMessageDialog(framebis, "Login incorrect");
              }}
              catch(Exception e){
                  System.out.println("ALERTE PROBLEME "+e.getClass());
              }

              // on vérifie le login du professeur qui est similaire à celui d'élève plus haut
              }else if (personne == "prof"){
                try{
                  if(CsvReader.loginExiste(userlogin,"./application/data/dataprof.csv")){
                    Map<String,String> preinfoUser=CsvReader.liseurCsv("./application/data/dataprof.csv");
                    // on écrit dans un dictionnaire le login, nom, prénom et langues du prof qui sera envoyé dans la page d'accueil du prof
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
