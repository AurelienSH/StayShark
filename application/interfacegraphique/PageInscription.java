package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import application.controleur.CsvReader;

public class PageInscription extends Page {
    PageInscription(){

        Map<String, String> newUser = new HashMap<String,String>();
        newUser.put("rôle","");

        JFrame framebis = new JFrame("StayShark");
        Page.basefenetre(framebis,400,400);

        JPanel newPanel = new JPanel();
        newPanel.setBackground( Color.decode("#ffdfba") );
        
        JLabel shark = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./application/data/medias/sharkSwimming.gif")), SwingConstants.LEFT);
        newPanel.add(shark);
        JLabel phrase = new JLabel("Vous vous inscrivez en tant que :", SwingConstants.CENTER);
        String choix[] = { "","professeur.e", "étudiant.e"};
        JComboBox<String> choixBoite = new JComboBox<>(choix);
        newPanel.add(phrase);
        newPanel.add(choixBoite, BorderLayout.CENTER);

        choixBoite.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				e.getSource();
				String role=(String) choixBoite.getSelectedItem();
                int index = choixBoite.getSelectedIndex(); 
                newUser.replace("rôle",role);
                //ajouter un check ou si la personne a sélectionné vide alors c'est pas bon
        }});
        
        JLabel userNom = new JLabel("Votre nom :", SwingConstants.CENTER);  
        JTextField reponseNom = new JTextField("Vador",15);
        JLabel userPrenom = new JLabel("Votre prénom :", SwingConstants.CENTER);  
        JTextField reponsePrenom = new JTextField("Dark",15);
        JLabel userLangue = new JLabel("Langue(s) que vous voulez apprendre ou enseigner :", SwingConstants.CENTER);
        JTextField reponseLangue = new JTextField("Espéranto Anglais",50);
        // faire traitement ou je mets tout en minuscule avec une lettre majuscule
        newPanel.add(userNom);
        newPanel.add(reponseNom);
        newPanel.add(userPrenom);
        newPanel.add(reponsePrenom);
        newPanel.add(userLangue);
        newPanel.add(reponseLangue);

        JButton inscription = new JButton("S'inscrire"); 
        inscription.setForeground(Color.white);
        inscription.setBackground(Color.decode("#ffb3ba"));
        newPanel.add(new JSeparator(SwingConstants.VERTICAL));
        newPanel.add(inscription);
        inscription.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                newUser.put("nom",reponseNom.getText());
                newUser.put("prénom",reponsePrenom.getText());
                newUser.put("langue",reponseLangue.getText());
                if(newUser.get("rôle") == "étudiant.e"){
                    try {
                        if(CsvReader.loginExiste(newUser.get("nom").replaceAll(" ","")+"1","./application/data/dataeleve.csv")){
                            int i=1;
                            while(true){
                                if(CsvReader.loginExiste(newUser.get("nom").replaceAll(" ","")+i,"./application/data/dataeleve.csv")){
                                    i++;
                                }else{break;}
                            }
                            newUser.put("login",newUser.get("nom").replaceAll(" ","")+i);
                        }else{
                            newUser.put("login",newUser.get("nom").replaceAll(" ","")+"1");
                        }
                    }
                    catch(Exception e){
                        System.out.println("ALERTE PROBLEME "+e.getClass());
                    } 
                    String[] words = newUser.get("langue").split(" "); 
                    String temporaire = "";
                    for(String word : words){
                        if(temporaire.contains("word")){continue;} // on vérifie qu'il n'y a pas de doublons
                        temporaire += word+":0|";
                    }
                    temporaire = temporaire.substring(0,temporaire.length()-1);
                    newUser.replace("langue",temporaire);
                    try{
                        CsvReader.inscription("./application/data/dataeleve.csv", newUser.get("login")+","+newUser.get("nom")+","+newUser.get("prénom")+","+newUser.get("langue"));
                    }catch(Exception e){
                        System.out.println("ALERTE PROBLEME "+e.getClass());
                    } 
                }
                else if(newUser.get("rôle") == "professeur.e"){
                    try {
                        if(CsvReader.loginExiste(newUser.get("nom").replaceAll(" ","")+"1","./application/data/dataprof.csv")){
                            int i=1;
                            while(true){
                                if(CsvReader.loginExiste(newUser.get("nom").replaceAll(" ","")+i,"./application/data/dataprof.csv")){
                                    i++;
                                }else{break;}
                            }
                            newUser.put("login",newUser.get("nom").replaceAll(" ","")+i);
                        }else{
                            newUser.put("login",newUser.get("nom").replaceAll(" ","")+"1");
                        }
                    }
                    catch(Exception e){
                        System.out.println("ALERTE PROBLEME "+e.getClass());
                    } 
                    String[] words = newUser.get("langue").split(" "); 
                    String temporaire = "";
                    for(String word : words){
                        if(temporaire.contains("word")){continue;} // on vérifie qu'il n'y a pas de doublons
                        temporaire += word+"|";
                    }
                    temporaire = temporaire.substring(0,temporaire.length()-1);
                    newUser.replace("langue",temporaire);
                    try{
                        CsvReader.inscription("./application/data/dataprof.csv", newUser.get("login")+","+newUser.get("nom")+","+newUser.get("prénom")+","+newUser.get("langue"));
                    }catch(Exception e){
                        System.out.println("ALERTE PROBLEME "+e.getClass());
                    } 
                }
                JOptionPane.showMessageDialog(framebis, "Bienvenue ! Votre login dorénavant est : "+newUser.get("login")+". Notez le quelque part 😊"); 
                framebis.dispose();
            }
        });

        newPanel.setLayout(new GridLayout(12,1)); 
        framebis.add(newPanel);

        framebis.setTitle("Inscription");  
        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
    }
}
