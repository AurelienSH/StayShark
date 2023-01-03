package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
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
        JPanel panel = new JPanel(new GridLayout(2, 1,100,100));
        panel.setBackground(Color.decode("#ffdfba"));
        
        String[] nbLangues = infoUser.get("langue").toString().split("&");
        if(nbLangues.length>1){
            for(String word : nbLangues){
                panel.add(new JButton(word),BorderLayout.NORTH);
            }
        }else{
            JLabel langue = new JLabel(nbLangues[0], SwingConstants.CENTER);
            langue.setFont(new Font("Apple Casual", Font.BOLD, 21));
            panel.add(langue);
            ImageIcon image = new ImageIcon("./application/data/medias/déposExo.png");
            JButton exo = new JButton("",image);
            exo.setPreferredSize(new Dimension(390, 150));
            exo.setBackground(Color.decode("#ffdfba"));
            
            String choix[] = { "","1","2","3","4","5"};
            JComboBox<String> choixlvl = new JComboBox<>(choix);
            choixlvl.setBackground(Color.decode("#ffb3ba"));
            choixlvl.setForeground(Color.WHITE);
            choixlvl.setFont(new Font("Apple Casual", Font.BOLD, 12));
            DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
            listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
            choixlvl.setRenderer(listRenderer);

            JTextField phrases = new JTextField(150);
            phrases.setHorizontalAlignment(JTextField.CENTER);
            JLabel phrasesLabel1 = new JLabel("Entrez phrase par phrase votre exercice sous la forme : Je suis un #motATrouver#.");
            JLabel phrasesLabel2 = new JLabel("Pour chaque phrase cliquez sur valider. Lorsque vous avez fini écrivez STOP et validez.");
            JButton valider = new JButton("Valider");
            JPanel sizePanel = new JPanel();
            sizePanel.setBackground(Color.decode("#ffdfba"));
            sizePanel.add(exo,BorderLayout.CENTER);
            sizePanel.add(phrasesLabel1,BorderLayout.CENTER);
            sizePanel.add(phrasesLabel2,BorderLayout.CENTER);
            sizePanel.add(phrases);
            sizePanel.add(valider);
            phrasesLabel1.setVisible(false);
            phrasesLabel2.setVisible(false);
            phrases.setVisible(false);
            valider.setVisible(false);
            panel.add(sizePanel);
            exo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    exo.setVisible(false);
                    phrasesLabel1.setVisible(true);
                    phrasesLabel2.setVisible(true);
                    choixlvl.setVisible(true);
                    phrases.setVisible(true);
                    valider.setVisible(true);
            }});
            valider.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    if(phrases.getText().contains("STOP")){
                        exo.setVisible(true);
                        phrasesLabel1.setVisible(false);
                        phrasesLabel2.setVisible(false);
                        phrases.setVisible(false);
                        valider.setVisible(false);
                        JOptionPane.showMessageDialog(framebis, "Vos phrases ont bien été ajoutés !");
                    }else{
                        choixlvl.setVisible(false);
                        try{
                            String path = "./application/data/langues/"+nbLangues[0]+"/";
                            System.out.println(path);
                            // CsvReader.ecriture(phrases.getText(), path);
                            phrases.setText("");
                        }
                        catch(Exception FileNotFoundException){
                            System.out.println("PROBLEME");
                        }
                        
                }
                    
            }});
        }

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

        framebis.add(panel);
        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        frame=framebis; //permet d'utiliser la frame dans une autre méthode
    }
}