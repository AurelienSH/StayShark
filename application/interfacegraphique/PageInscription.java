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
        phrase.setFont(new Font("Apple Casual", Font.BOLD, 12));
        String choix[] = { "","professeur.e", "étudiant.e"};
        JComboBox<String> choixBoite = new JComboBox<>(choix);
        choixBoite.setBackground(Color.decode("#ffb3ba"));
        choixBoite.setForeground(Color.WHITE);
        choixBoite.setFont(new Font("Apple Casual", Font.BOLD, 12));
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        choixBoite.setRenderer(listRenderer);
        newPanel.add(phrase);
        newPanel.add(choixBoite, BorderLayout.CENTER);

        choixBoite.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				e.getSource();
				String role=(String) choixBoite.getSelectedItem();
                newUser.replace("rôle",role);
        }});
        
        JLabel userNom = new JLabel("Votre nom :", SwingConstants.CENTER);
        userNom.setFont(new Font("Apple Casual", Font.BOLD, 12));  
        JTextField reponseNom = new JTextField("Vador",50);
        reponseNom.setHorizontalAlignment(JTextField.CENTER);
        reponseNom.setFont(new Font("Apple Casual", Font.PLAIN, 12)); 
        JLabel userPrenom = new JLabel("Votre prénom :", SwingConstants.CENTER);
        userPrenom.setFont(new Font("Apple Casual", Font.BOLD, 12));  
        JTextField reponsePrenom = new JTextField("Dark",50);
        reponsePrenom.setHorizontalAlignment(JTextField.CENTER);
        reponsePrenom.setFont(new Font("Apple Casual", Font.PLAIN, 12));
        JLabel userLangue = new JLabel("Langue(s) que vous voulez apprendre ou enseigner :", SwingConstants.CENTER);
        userLangue.setFont(new Font("Apple Casual", Font.BOLD, 12));
        JTextField reponseLangue = new JTextField("Français Anglais",50);
        reponseLangue.setHorizontalAlignment(JTextField.CENTER);
        reponseLangue.setFont(new Font("Apple Casual", Font.PLAIN, 12));
        newPanel.add(userNom);
        newPanel.add(reponseNom);
        newPanel.add(userPrenom);
        newPanel.add(reponsePrenom);
        newPanel.add(userLangue);
        newPanel.add(reponseLangue);

        JButton inscription = new JButton("S'inscrire");
        inscription.setFont(new Font("Apple Casual", Font.BOLD, 20)); 
        inscription.setForeground(Color.white);
        inscription.setBackground(Color.decode("#ffb3ba"));
        newPanel.add(new JSeparator(SwingConstants.VERTICAL));
        newPanel.add(inscription);
        inscription.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                String temporaire=reponseNom.getText().toLowerCase();
                temporaire = temporaire.substring(0,1).toUpperCase()+temporaire.substring(1,temporaire.length());
                newUser.put("nom",temporaire);
                temporaire=reponsePrenom.getText().toLowerCase();
                temporaire = temporaire.substring(0,1).toUpperCase()+temporaire.substring(1,temporaire.length());
                newUser.put("prénom",temporaire);
                newUser.put("langue",reponseLangue.getText().toLowerCase());
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
                    temporaire = "";
                    for(String word : words){
                        if(temporaire.contains(word.substring(0,1).toUpperCase()+word.substring(1,word.length()))){continue;} // on vérifie qu'il n'y a pas de doublons
                        word=word.substring(0,1).toUpperCase()+word.substring(1,word.length());
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
                    temporaire = "";
                    for(String word : words){
                        if(temporaire.contains(word.substring(0,1).toUpperCase()+word.substring(1,word.length()))){continue;} // on vérifie qu'il n'y a pas de doublons
                        word=word.substring(0,1).toUpperCase()+word.substring(1,word.length());
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
                if(newUser.get("login")==null){
                    JOptionPane.showMessageDialog(framebis, "Oh mince vous avez oublié de sélectionner si vous êtes étudiant.e ou professeur.e ! ");
                }else{JOptionPane.showMessageDialog(framebis, "Bienvenue ! Votre login dorénavant est : "+newUser.get("login")+". Notez le quelque part ! 😊 "); 
                framebis.dispose();}
                
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
