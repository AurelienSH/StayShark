package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Créé la page que l'élève verra.
 */
public class PageEleve extends Page {
    JFrame frame;
    /**
     * Page que l'élève verra dans l'application (contenant les exos et ses notes)
     * @param frameAJeter Jframe (PageHome) qui sera tué avant la création de la frame pour l'élève
     */
    public PageEleve(JFrame frameAJeter, Map infoUser){
        frameAJeter.dispose();
		JFrame framebis = new JFrame("StayShark");
        Page.basefenetre(framebis,800,600);
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.setBackground(Color.decode("#ffdfba"));

        String[] nbLangues = infoUser.get("langue").toString().split("&");
        if(nbLangues.length>1){
            for(String word : nbLangues){
                String[] langueExp = word.split(":");
                panel.add(new JButton(langueExp[0]),BorderLayout.NORTH);
            }
        }else{
            String[] langueExp = nbLangues[0].split(":");
            JLabel langue = new JLabel(langueExp[0], SwingConstants.CENTER);
            langue.setFont(new Font("Apple Casual", Font.BOLD, 21));
            panel.add(langue);
            panel.add(new JSeparator(SwingConstants.VERTICAL));
            ImageIcon image = new ImageIcon("./application/data/medias/exercices.png");
            JButton exo = new JButton("",image);
            exo.setPreferredSize(new Dimension(390, 150));
            exo.setBackground(Color.decode("#ffdfba"));
            JPanel sizePanel = new JPanel();
            sizePanel.setBackground(Color.decode("#ffdfba"));
            sizePanel.add(exo,BorderLayout.CENTER);
            panel.add(sizePanel);
            panel.add(new JSeparator(SwingConstants.VERTICAL));
            exo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    //création de l'exo 
                    
            }});
        }

        // TO DO
        /*
         * - plateforme de suivi de ses notes/level : 
         *                      demander langue si jamais il y en plusieurs
         *                      afficher lvl
         *                      afficher dans combien de temps le lvl est atteint --> appli pas prof
         * - plateforme d'exos : 
         *                      choix de la langue si plusieurs
         *                      exos
         *                      récap de toutes les fautes après l'exo
         */
        
        framebis.add(panel);
        framebis.pack();
        framebis.setLocationRelativeTo(null);
        framebis.setVisible(true);
        
        frame=framebis; //permet d'utiliser la frame dans une autre méthode
    }
}