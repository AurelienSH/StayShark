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
 * Créé la page que le prof verra
 */
public class PageProf extends Page {
    JFrame frame;
    /**
     * Page que le prof verra dans l'application (contenant les exos à déposer et les notes de ses élèves).
     * @param frameAJeter Jframe (PageHome) qui sera tué avant la création de la frame pour le prof
     */
    public PageProf(JFrame frameAJeter, Map infoUser){
        frameAJeter.dispose();
		JFrame framebis = new JFrame("StayShark");
        Page.basefenetre(framebis,800,600);
        
        String[] nbLangues = infoUser.get("langue").toString().split("&");

        ImageIcon image = new ImageIcon("./application/data/medias/déposExo.png");
        JButton exo = new JButton("",image);
        exo.setPreferredSize(new Dimension(390, 150));
        exo.setBackground(Color.decode("#ffdfba"));
        ImageIcon image2 = new ImageIcon("./application/data/medias/suiviEleve.png");
        JButton exo2 = new JButton("",image2);
        exo2.setPreferredSize(new Dimension(390, 150));
        exo2.setBackground(Color.decode("#ffdfba"));
        
        JPanel daronne = new JPanel();
        daronne.setBackground(Color.decode("#ffdfba"));
        JPanel reaPanel = new JPanel();
        reaPanel.setBackground(Color.decode("#ffdfba"));
        reaPanel.setPreferredSize(new Dimension(800, 500));
        JPanel vide = new JPanel();
        vide.setBackground(Color.decode("#ffdfba"));
        vide.setPreferredSize(new Dimension(800, 100));
        
        reaPanel.add(exo,BorderLayout.CENTER);
        reaPanel.add(exo2,BorderLayout.CENTER);
        
        exo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JPanel exosPanel = new JPanel(new GridLayout(12,1,10,10));
                exosPanel.setBackground(Color.decode("#ffdfba"));

                Map<String, String> infoExo = new HashMap<String,String>();
                
                JLabel choixlangueLabel = new JLabel("Choisissez la langue de votre exercice : ",SwingConstants.CENTER);

                String[] choixlangueArray = new String[nbLangues.length+1];
                choixlangueArray[0] = "";
                if(nbLangues.length>1){
                    int ii = 0;
                    for(int i = 1; i < nbLangues.length; i++){
                        choixlangueArray[i] = nbLangues[ii];
                        ii++;
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
                
                JLabel choixlvlLabel = new JLabel("Choisissez la difficulté de votre exercice : ",SwingConstants.CENTER);

                String choix[] = { "","1","2","3","4","5"};
                JComboBox<String> choixlvl = new JComboBox<>(choix);
                choixlvl.setBackground(Color.decode("#ffb3ba"));
                choixlvl.setForeground(Color.WHITE);
                choixlvl.setFont(new Font("Apple Casual", Font.BOLD, 12));
                DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
                listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
                choixlvl.setRenderer(listRenderer);

                JLabel choixCorrectionLabel = new JLabel("Choisissez la difficulté de votre correction : ",SwingConstants.CENTER);

                String choixCorrectionArray[] = { "","1","2","3","4","5"}; // ajouter selon le dictionnaire de dureté de correction d'Aurélien
                JComboBox<String> choixCorrection = new JComboBox<>(choixCorrectionArray);
                choixCorrection.setBackground(Color.decode("#ffb3ba"));
                choixCorrection.setForeground(Color.WHITE);
                choixCorrection.setFont(new Font("Apple Casual", Font.BOLD, 12));
                DefaultListCellRenderer listRenderer2 = new DefaultListCellRenderer();
                listRenderer2.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
                choixCorrection.setRenderer(listRenderer2);                

                JButton valider0 = new JButton("Valider");

                JTextField phrases = new JTextField(65);
                phrases.setHorizontalAlignment(JTextField.CENTER);
                JLabel phrasesLabel1 = new JLabel("Entrez phrase par phrase votre exercice sous la forme : Je suis un #motATrouver#.",SwingConstants.CENTER);
                JLabel phrasesLabel2 = new JLabel("Pour chaque phrase cliquez sur valider. Lorsque vous avez fini écrivez STOP et validez.",SwingConstants.CENTER);
                JButton valider = new JButton("Valider");

                exosPanel.add(choixCorrectionLabel,BorderLayout.CENTER);
                exosPanel.add(choixCorrection,BorderLayout.CENTER);
                exosPanel.add(valider0,BorderLayout.CENTER);
                exosPanel.add(choixlangueLabel,BorderLayout.CENTER);
                exosPanel.add(choixlangue,BorderLayout.CENTER);
                exosPanel.add(choixlvlLabel,BorderLayout.CENTER);
                exosPanel.add(choixlvl,BorderLayout.CENTER);
                exosPanel.add(phrasesLabel1,BorderLayout.CENTER);
                exosPanel.add(phrasesLabel2,BorderLayout.CENTER);
                exosPanel.add(phrases,BorderLayout.CENTER);
                exosPanel.add(valider,BorderLayout.CENTER);

                reaPanel.add(exosPanel,BorderLayout.CENTER);
                exo.setVisible(false);
                exo2.setVisible(false);
                exosPanel.setVisible(true);

                choixlangue.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        e.getSource();
                        String langueChoisie = (String) choixlangue.getSelectedItem();
                        infoExo.put("langue choisie",langueChoisie);
                }});
                choixlvl.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        e.getSource();
                        String lvlchoisi = (String) choixlvl.getSelectedItem();
                        infoExo.put("lvl choisi",lvlchoisi);
                }});
                choixCorrection.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        e.getSource();
                        String correctionChoisie = (String) choixCorrection.getSelectedItem();
                        infoExo.put("correction choisie",correctionChoisie);
                }});

                valider0.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        String path = "./application/data/langues/"+infoExo.get("langue choisie")+"/"+infoExo.get("lvl choisi")+"/exo1.csv";
                        // tester si le fichier existe avec CsvReader.fileexiste()
                            int i = 2;
                            while(true){
                                if(CsvReader.fileExiste(path)==true){
                                    path=path.substring(0,path.length()-5)+i+".csv";
                                    i++;
                                }else{break;}
                            }
                            System.out.println(path);

                        if(CsvReader.fileExiste(path)==false){
                            try{
                                CsvReader.ecriture(path, "truc à écrire pour la correction"); // à voir avec le dico d'Aurélien
                            }catch(Exception IOException){
                                System.out.println("PROBLEME");
                            }
                        }
                }});

                valider.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        if(phrases.getText().contains("STOP")){
                            exo.setVisible(true);
                            exo2.setVisible(true);
                            exosPanel.setVisible(false);
                            JOptionPane.showMessageDialog(framebis, "Vos phrases ont bien été ajoutés !");
                        }else{
                            choixlangueLabel.setVisible(false);
                            choixlangue.setVisible(false);
                            choixlvlLabel.setVisible(false);
                            choixlvl.setVisible(false);
                            try{
                                String path = "./application/data/langues/"+infoExo.get("langue choisie")+"/"+infoExo.get("lvl choisi")+"/exo1.csv";
                                CsvReader.ecriture(path,phrases.getText());
                                phrases.setText("");
                            }
                            catch(Exception FileNotFoundException){
                                System.out.println("PROBLEME");
                            }
                            
                    }
                        
                }});
        }});
        // TO DO
        /*
         * - plateforme de récupération : 
         *                  récupérer langue prof, si plusieurs langues demander pour laquelle il dépose ses exos 
         *                  demander pour quel level (si on fait système de lvl) ou alors c'est beaucoup d'exos et le système de lvl se fait seul
         *                  demander le niveau de dureté de notation ptet??
         *                  --> les mettre sous forme de csv ou txt? (pour qu'ils soient récupérable pour après)
         * - plateforme de suivi des notes/levels : 
         *                  demander élève à checker ou alors tous les afficher et système de recherche?
         */

        daronne.add(vide);
        daronne.add(reaPanel);
        framebis.add(daronne);
        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        frame=framebis; //permet d'utiliser la frame dans une autre méthode
    }
}