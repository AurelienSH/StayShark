package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import application.controleur.CsvReader;

/**
 * Créé la page que le professeur verra.
 * Le professeur pourra accéder à une page de création d'exercice et une page de suivi des notes de ses élèves.
 */
public class PageProf extends Page {
    JFrame frame;
    /**
     * Page que le prof verra dans l'application (contenant les exos à déposer et les notes de ses élèves).
     * @param frameAJeter Jframe (PageHome) qui sera tué avant la création de la frame pour le prof
     * @param infoUser dictionnaire contenant le login en clé et en valeur les autres infos (nom, prénom, langue et leur points)
     */
    public PageProf(JFrame frameAJeter, Map infoUser){
        // on se débarasse de la page d'accueil et de la page login
        frameAJeter.dispose();
		JFrame framebis = new JFrame("StayShark");
        Page.basefenetre(framebis,800,600);
        
        String[] nbLangues = infoUser.get("langue").toString().split("&"); // liste des langues du prof

        // création des boutons de création d'exercice et de suivi des notes des élèves
        ImageIcon image = new ImageIcon("./application/data/medias/déposExo.png");
        JButton exo = new JButton("",image);
        exo.setPreferredSize(new Dimension(390, 150));
        exo.setBackground(Color.decode("#ffdfba"));
        ImageIcon image2 = new ImageIcon("./application/data/medias/suiviEleve.png");
        JButton suivi = new JButton("",image2);
        suivi.setPreferredSize(new Dimension(390, 150));
        suivi.setBackground(Color.decode("#ffdfba"));
        
        // panel qui contient les boutons
        JPanel panelMere = new JPanel();
        panelMere.setBackground(Color.decode("#ffdfba"));
        JPanel realPanel = new JPanel();
        realPanel.setBackground(Color.decode("#ffdfba"));
        realPanel.setPreferredSize(new Dimension(800, 500));
        JPanel vide = new JPanel();
        vide.setBackground(Color.decode("#ffdfba"));
        vide.setPreferredSize(new Dimension(800, 100));
        
        realPanel.add(exo,BorderLayout.CENTER);
        realPanel.add(suivi,BorderLayout.CENTER);
        
        // quand on clique sur le bouton exercice : création d'un panel qui contient la page pour créer des exercices.
        exo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // création du panel qui contient le dépôt des exercices
                JPanel exosPanel = new JPanel(new GridLayout(12,1,10,10));
                exosPanel.setBackground(Color.decode("#ffdfba"));

                Map<String, String> infoExo = new HashMap<String,String>(); // contient la langue de l'exercice, son level (1 : A1, 2:A2, 3:B1, etc.) et la dureté de la correction
                
                // choix de la langue pour laquelle on va créer l'exercice
                JLabel choixlangueLabel = new JLabel("Choisissez la langue de votre exercice : ",SwingConstants.CENTER);
                String[] choixlangueArray = new String[nbLangues.length+1];
                choixlangueArray[0] = "";
                if(nbLangues.length!=1){
                    int i = 1;
                    for(String word : nbLangues){
                        choixlangueArray[i] = word; 
                        i++;
                    }
                }else{
                    choixlangueArray[1] = nbLangues[0];
                    }
                
                JComboBox<String> choixlangue = new JComboBox<>(choixlangueArray);
                choixlangue.setBackground(Color.decode("#ffb3ba"));
                choixlangue.setForeground(Color.WHITE);
                choixlangue.setFont(new Font("Apple Casual", Font.BOLD, 12));
                DefaultListCellRenderer listRenderer1 = new DefaultListCellRenderer();
                listRenderer1.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
                choixlangue.setRenderer(listRenderer1);
                
                // choix du level pour l'exercice créé
                JLabel choixlvlLabel = new JLabel("Choisissez la difficulté de votre exercice : ",SwingConstants.CENTER);
                String choix[] = { "","1","2","3","4","5"};
                JComboBox<String> choixlvl = new JComboBox<>(choix);
                choixlvl.setBackground(Color.decode("#ffb3ba"));
                choixlvl.setForeground(Color.WHITE);
                choixlvl.setFont(new Font("Apple Casual", Font.BOLD, 12));
                DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
                listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
                choixlvl.setRenderer(listRenderer);

                // choix de la dureté de correction pour l'exercice
                JLabel choixCorrectionLabel = new JLabel("Choisissez la difficulté de votre correction : ",SwingConstants.CENTER);
                String choixCorrectionArray[] = { "","permissive","intransigeante","aucune erreur"}; 
                JComboBox<String> choixCorrection = new JComboBox<>(choixCorrectionArray);
                choixCorrection.setBackground(Color.decode("#ffb3ba"));
                choixCorrection.setForeground(Color.WHITE);
                choixCorrection.setFont(new Font("Apple Casual", Font.BOLD, 12));
                DefaultListCellRenderer listRenderer2 = new DefaultListCellRenderer();
                listRenderer2.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
                choixCorrection.setRenderer(listRenderer2);                

                // va récupérer toutes les infos (langue,level et dureté de correction) sur l'exercice
                JButton valider0 = new JButton("Valider");

                // récupération des phrases qui seront dans l'exercice
                JTextField phrases = new JTextField(65);
                phrases.setHorizontalAlignment(JTextField.CENTER);
                JLabel phrasesLabel1 = new JLabel("Entrez phrase par phrase votre exercice sous la forme : Je suis un #motATrouver#.",SwingConstants.CENTER);
                JLabel phrasesLabel2 = new JLabel("Pour chaque phrase cliquez sur valider. Lorsque vous avez fini écrivez STOP et validez.",SwingConstants.CENTER);
                JButton valider = new JButton("Valider");

                exosPanel.add(choixCorrectionLabel,BorderLayout.CENTER);
                exosPanel.add(choixCorrection,BorderLayout.CENTER);
                exosPanel.add(choixlangueLabel,BorderLayout.CENTER);
                exosPanel.add(choixlangue,BorderLayout.CENTER);
                exosPanel.add(choixlvlLabel,BorderLayout.CENTER);
                exosPanel.add(choixlvl,BorderLayout.CENTER);
                exosPanel.add(valider0,BorderLayout.CENTER);
                exosPanel.add(phrasesLabel1,BorderLayout.CENTER);
                exosPanel.add(phrasesLabel2,BorderLayout.CENTER);
                exosPanel.add(phrases,BorderLayout.CENTER);
                exosPanel.add(valider,BorderLayout.CENTER);

                realPanel.add(exosPanel,BorderLayout.CENTER);
                exo.setVisible(false);
                suivi.setVisible(false);
                exosPanel.setVisible(true);

                // récupération de la langue de l'exercice
                choixlangue.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        e.getSource();
                        String langueChoisie = (String) choixlangue.getSelectedItem();
                        infoExo.put("langue choisie",langueChoisie);
                }});
                // récupération du level de l'exercice
                choixlvl.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        e.getSource();
                        String lvlchoisi = (String) choixlvl.getSelectedItem();
                        infoExo.put("lvl choisi",lvlchoisi);
                }});
                // récupération de la dureté/méthode de correction pour l'exercice
                choixCorrection.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        e.getSource();
                        String correctionChoisie = (String) choixCorrection.getSelectedItem();
                        infoExo.put("correction choisie",correctionChoisie);
                }});

                // quand on clique sur valider on va écrire la dureté de correction choisie dans le csv (qui stockera les phrases de l'exercice)
                valider0.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        //on va tester si le path de l'exo existe si oui on en créé un nouveau pour l'exo
                        String path = "./application/data/langues/"+infoExo.get("langue choisie")+"/"+infoExo.get("lvl choisi")+"/exo1.csv";
                        int i = 2;
                        while(true){
                            if(CsvReader.fileExiste(path)==true){
                                path=path.substring(0,path.length()-5)+i+".csv";
                                i++;
                            }else{break;}
                        }
                            try{
                                CsvReader.ecriture(path, infoExo.get("correction choisie").toString());
                            }catch(Exception IOException){
                                System.out.println("PROBLEME");
                            }
                        choixCorrectionLabel.setVisible(false);
                        choixCorrection.setVisible(false);
                        valider0.setVisible(false);
                        choixlangueLabel.setVisible(false);
                        choixlangue.setVisible(false);
                        choixlvlLabel.setVisible(false);
                        choixlvl.setVisible(false);
                }});

                // quand on clique sur valider on va écrire la phrase à trou dans le csv
                valider.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        if(phrases.getText().contains("STOP")){ // arrêt de la saisie des phrases à trous
                            exo.setVisible(true);
                            suivi.setVisible(true);
                            exosPanel.setVisible(false);
                            JOptionPane.showMessageDialog(framebis, "Vos phrases ont bien été ajoutés !");
                        }else{
                            try{
                                String path = "./application/data/langues/"+infoExo.get("langue choisie")+"/"+infoExo.get("lvl choisi")+"/exo1.csv";
                                int i = 2;
                                while(true){
                                    if(CsvReader.fileExiste(path)==true){
                                        path=path.substring(0,path.length()-5)+i+".csv";
                                        i++;
                                    }else{
                                        break;}
                                } 
                                i=i-2;
                                path=path.substring(0,path.length()-5)+i+".csv";
                                CsvReader.ecriture(path,phrases.getText());
                                phrases.setText("");
                            }
                            catch(Exception FileNotFoundException){
                                System.out.println("PROBLEME");
                            }
                            
                    }
                        
                }});
        }});

        // quand on clique sur le bouton suivi on a accès aux points que les élèves ont accumulés dans la langue qu'on enseigne
        suivi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // on se débarrasse de la page d'accueil du professeur (qui contient les boutons dépot d'exercice et suivi des notes)
                exo.setVisible(false);
                suivi.setVisible(false);

                Map<String, String> infoSuivi = new HashMap<String,String>(); // contient la langue choisie où l'on veut voir les notes des élèves

                // création du panel qui contient le choix de la langue à regarder
                JPanel suiviPanel = new JPanel(new GridLayout(2,1,10,10));
                suiviPanel.setBackground(Color.decode("#ffdfba"));

                JLabel choixlangueLabel = new JLabel("Pour quelle langue voulez vous suivre les notes des élèves?");

                // choix de la langue parmis celles qu'il enseigne
                String[] choixlangueArray = new String[nbLangues.length+1];
                choixlangueArray[0] = "";
                if(nbLangues.length>1){
                    int i = 1;
                    for(String word : nbLangues){
                        choixlangueArray[i] = word; 
                        i++;
                    }  
                }else{
                    choixlangueArray[1] = nbLangues[0];
                    }
                
                JComboBox<String> choixlangue = new JComboBox<>(choixlangueArray);
                choixlangue.setBackground(Color.decode("#ffb3ba"));
                choixlangue.setForeground(Color.WHITE);
                choixlangue.setFont(new Font("Apple Casual", Font.BOLD, 12));
                DefaultListCellRenderer listRenderer1 = new DefaultListCellRenderer();
                listRenderer1.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
                choixlangue.setRenderer(listRenderer1);

                // va récupérer le choix de la langue
                JButton choixlangueButton = new JButton("Valider");

                suiviPanel.add(choixlangueLabel,BorderLayout.CENTER);
                suiviPanel.add(choixlangue,BorderLayout.CENTER);
                suiviPanel.add(choixlangueButton,BorderLayout.CENTER);

                realPanel.add(suiviPanel);
                suiviPanel.setVisible(true);

                // récupération du choix de la langue à regarder
                choixlangue.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        e.getSource();
                        String langueChoisie = (String) choixlangue.getSelectedItem();
                        infoSuivi.put("langue choisie",langueChoisie);
                }});

                // quand on clique sur valider on va pouvoir accéder aux points que les élèves ont dans cette langue
                choixlangueButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        // création du panel qui contient les points des élèves
                        JPanel suiviPanelAffiche = new JPanel();
                        suiviPanelAffiche.setBackground(Color.decode("#ffdfba"));
                        vide.setVisible(false);
                        suiviPanel.setVisible(false);

                        // permet de revenir à la page d'accueil du professeur
                        JButton quitter = new JButton("Quitter");

                        // quand on clique sur quitter on revient à la apge d'accueil du professeur (qui contient les boutons de dépot d'exercice et de suivi des notes)
                        quitter.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e) {
                                suiviPanelAffiche.setVisible(false);
                                vide.setVisible(true);
                                exo.setVisible(true);
                                suivi.setVisible(true);
                        }});

                        try {
                            Map<String,String> infosEleves = CsvReader.liseurCsv("./application/data/dataeleve.csv");

                            Map<String,String> elevesAAfficher = new HashMap<String,String>(); // dictionnaire des élèves qui ont la langue voulu comme langue qu'ils apprennent

                            // on affiche les élève dans un scoll panel
                            for(Map.Entry<String, String> entry : infosEleves.entrySet()){
                                String clé = entry.getKey();
                                String valeur = entry.getValue();
                                if(valeur.contains(infoSuivi.get("langue choisie"))){
                                    String[] infosEleveString = valeur.split(",");
                                    String[] languesEleve = infosEleveString[2].split("&");
                                    for(String word : languesEleve){
                                        if(word.contains(infoSuivi.get("langue choisie"))){
                                            elevesAAfficher.put(infosEleveString[0]+","+infosEleveString[1],word);
                                        }
                                    }
                                }
                            }
                            String[] columns = new String[] {"Nom","Prénom","Points"};
                            Object[][] data = new Object[elevesAAfficher.size()][columns.length];
                            int i=0;
                            for(Map.Entry<String, String> entry : elevesAAfficher.entrySet()){
                                String[] clé = entry.getKey().split(",");
                                String[] valeur = entry.getValue().split(":");
                                data[i][0] = clé[0];
                                data[i][1] = clé[1];
                                data[i][2] = valeur[1];
                                i++;
                            }
                        JTable table = new JTable(data, columns);
                        JScrollPane scroll = new JScrollPane(table);
                        table.setFillsViewportHeight(true);
                        JLabel labelHead = new JLabel("Liste des élèves",SwingConstants.CENTER);

                        suiviPanelAffiche.add(labelHead,BorderLayout.NORTH);
                        suiviPanelAffiche.add(scroll,BorderLayout.CENTER);
                        suiviPanelAffiche.add(quitter,BorderLayout.CENTER);
                        realPanel.add(suiviPanelAffiche,BorderLayout.CENTER);
                        suiviPanelAffiche.setVisible(true);

                        }catch(Exception FileNotFoundException){
                            System.out.println("PROBLEME FileNotFoundException");
                        }
                }});
                

        }});

        panelMere.add(vide);
        panelMere.add(realPanel);
        framebis.add(panelMere);
        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        frame=framebis; //permet d'utiliser la frame dans une autre méthode
    }
}