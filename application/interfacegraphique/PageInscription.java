package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import application.controleur.CsvReader;

/**
 * Créé la page d'inscription pour l'élève ou le professeur.
 */
public class PageInscription extends Page {
    /**
     * Page d'inscription qui écrit dans le bon csv (prof ou élève) toutes les informations nécessaires (nom,prénom,langues choisies).
     */
    PageInscription(){

        // dictionnaire qui contient les informations de l'utilisateur (professeur ou étudiant, nom, prénom, langues choisies, login assigné)
        Map<String, String> newUser = new HashMap<String,String>();
        newUser.put("rôle","");

        // création de la frame et du panel qui contiennent le formulaire d'inscription 
        JFrame framebis = new JFrame("StayShark");
        Page.basefenetre(framebis,400,400);
        JPanel newPanel = new JPanel();
        newPanel.setBackground( Color.decode("#ffdfba") );

        // juste pour faire joli dans le panel
        JLabel shark = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("./application/data/medias/sharkSwimming.gif")), SwingConstants.LEFT);
        newPanel.add(shark);

        // demande si c'est un professeur ou un élève qui s'inscrit
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

        // récupération de si c'est un professeur ou un élève
        choixBoite.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
				e.getSource();
				String role=(String) choixBoite.getSelectedItem();
                newUser.replace("rôle",role);
        }});
        
        // demande le nom, prénom et langue à apprendre/enseigner par/à l'utilisateur
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

        // bouton de récupération de toutes les informations
        JButton inscription = new JButton("S'inscrire");
        inscription.setFont(new Font("Apple Casual", Font.BOLD, 20)); 
        inscription.setForeground(Color.white);
        inscription.setBackground(Color.decode("#ffb3ba"));
        newPanel.add(new JSeparator(SwingConstants.VERTICAL));
        newPanel.add(inscription);

        // quand on clique sur inscription : on récupère toutes les données de l'utilisateur, lui assigne un login et écrit toutes ses infos dans le bon csv qui contient toutes les données des utilisateurs professeurs ou élèves
        inscription.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                // récupération du nom, prénom et langue (on gère les cas comme : "patAteS" --> "Patates" pour le nom prénom et un peu en avance pour langue)
                String temporaire=reponseNom.getText().toLowerCase();
                temporaire = temporaire.substring(0,1).toUpperCase()+temporaire.substring(1,temporaire.length());
                newUser.put("nom",temporaire);
                temporaire=reponsePrenom.getText().toLowerCase();
                temporaire = temporaire.substring(0,1).toUpperCase()+temporaire.substring(1,temporaire.length());
                newUser.put("prénom",temporaire);
                newUser.put("langue",reponseLangue.getText().toLowerCase());

                // si l'utilisateur est un élève on l'inscrit dans le bon csv et lui assigne le bon rôle. On lui assigne un login.
                if(newUser.get("rôle") == "étudiant.e"){
                    try { // on gère les cas où "Dupont" existe déjà dans les données pour lui attribuer un login différent (login=Nom1)
                        if(CsvReader.loginExiste(newUser.get("nom").replaceAll(" ","")+"1","./application/data/dataeleve.csv")){
                            int i=1;
                            while(true){ // on essaye "Dupont2","Dupont3"...
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
                    } // on récupère les langues de l'élève
                    String[] words = newUser.get("langue").split(" "); 
                    temporaire = "";
                    for(String word : words){ // on va gérer les soucis comme "fRançAis"
                        if(temporaire.contains(word.substring(0,1).toUpperCase()+word.substring(1,word.length()))){continue;} // on vérifie qu'il n'y a pas de doublons dans le choix des langues
                        word=word.substring(0,1).toUpperCase()+word.substring(1,word.length());
                        temporaire += word+":0&"; // 0 --> on rajoute l'expérience à la langue (Français:0 points)
                    }
                    temporaire = temporaire.substring(0,temporaire.length()-1);
                    newUser.replace("langue",temporaire);
                    try{ // inscription de l'élève dans le csv qui a tout les élèves de la plateforme
                        CsvReader.ecriture("./application/data/dataeleve.csv", newUser.get("login")+","+newUser.get("nom")+","+newUser.get("prénom")+","+newUser.get("langue"));
                    }catch(Exception e){
                        System.out.println("ALERTE PROBLEME "+e.getClass());
                    } 
                } 
                // traitement de l'inscription pour professeur.e (presque pareil qu'étudiant)
                else if(newUser.get("rôle") == "professeur.e"){
                    try { // attribution du login
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
                    }  // on récupères ses langues
                    String[] words = newUser.get("langue").split(" "); 
                    temporaire = "";
                    for(String word : words){
                        if(temporaire.contains(word.substring(0,1).toUpperCase()+word.substring(1,word.length()))){continue;} // on vérifie qu'il n'y a pas de doublons
                        word=word.substring(0,1).toUpperCase()+word.substring(1,word.length());
                        temporaire += word+"&";
                    }
                    temporaire = temporaire.substring(0,temporaire.length()-1);
                    newUser.replace("langue",temporaire);
                    try{ // on écrit le nouvel utilisateur dans le csv qui a toutes les infos de tous les professeurs de l'application
                        CsvReader.ecriture("./application/data/dataprof.csv", newUser.get("login")+","+newUser.get("nom")+","+newUser.get("prénom")+","+newUser.get("langue"));
                    }catch(Exception e){
                        System.out.println("ALERTE PROBLEME "+e.getClass());
                    } 
                }
                if(newUser.get("login")==null){ // on vérifie que l'utilisateur a bien choisi un rôle
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
