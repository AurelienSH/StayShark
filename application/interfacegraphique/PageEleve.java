package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import application.système.*;
import application.controleur.*;
import java.util.ArrayList;

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

        // TO DO
        /*
         * 💤 PLUS LE TIME DONC SUPPRIME
         * - plateforme de suivi de ses notes/level : 
         *                     💤 demander langue si jamais il y en plusieurs
         *                     💤 afficher lvl
         *                     💤 afficher dans combien de temps le lvl est atteint --> appli pas prof
         * 💤
         * ⚠️faire un retour en arrière pour barre d'xp⚠️
         * - barre d'xp danc combien de temps on atteint le lvl ✔️
         * - plateforme d'exos : 
         *                      choix de la langue si plusieurs ✔️
         *                      choix du lvl✔️
         *                      exos ⚠️A VOIR⚠️
         *                      récap de toutes les fautes après l'exo ⚠️A VOIR⚠️
         * 
         * 
         * A FAIRE
         * faire récupération de l'exo du lvl à passer (liste des exos)
         * modifieur csv en fonction de hashmap
         * les phrases à trou pour récup les réponses à bien mettre
         * faire un jpanel correction quand valider exo
         * A la fin de la correction faire un bouton qui ramène l'élève au frame de exo/suivi
         * 
         */
        
        frameAJeter.dispose();
		JFrame framebis = new JFrame("StayShark");
        Page.basefenetre(framebis,800,600);
        JPanel panelMere = new JPanel();
        panelMere.setBackground(Color.decode("#ffdfba"));
        JPanel realPanel = new JPanel();
        realPanel.setBackground(Color.decode("#ffdfba"));
        realPanel.setPreferredSize(new Dimension(800, 400));
        JPanel vide = new JPanel();
        vide.setBackground(Color.decode("#ffdfba"));
        vide.setPreferredSize(new Dimension(800, 200));

        ImageIcon image = new ImageIcon("./application/data/medias/exercice.png");
        JButton exo = new JButton("",image);
        exo.setPreferredSize(new Dimension(390, 150));
        exo.setBackground(Color.decode("#ffdfba"));
        ImageIcon image2 = new ImageIcon("./application/data/medias/suiviEleve.png");
        JButton suivi = new JButton("",image2);
        suivi.setPreferredSize(new Dimension(390, 150));
        suivi.setBackground(Color.decode("#ffdfba"));

        realPanel.add(exo,BorderLayout.CENTER);
        realPanel.add(suivi,BorderLayout.CENTER);

        String[] nbLangues = infoUser.get("langue").toString().split("&");
        
        exo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                vide.setVisible(false);
                realPanel.setVisible(false);

                Map<String, String> infoExo = new HashMap<String,String>();

                JPanel exoPanelMere = new JPanel(new GridLayout(7,1));
                exoPanelMere.setBackground(Color.decode("#ffdfba"));
                exoPanelMere.setPreferredSize(new Dimension(600, 400));

                JLabel choixlangueLabel = new JLabel("Choisissez la langue de votre exercice : ",SwingConstants.CENTER);
                choixlangueLabel.setFont(new Font("Apple Casual", Font.BOLD, 18));
                String[] choixlangueArray = new String[nbLangues.length+1];
                choixlangueArray[0] = "";
                if(nbLangues.length!=1){
                    int i = 1;
                    for(String word : nbLangues){
                        String[] langueExp = word.split(":");
                        choixlangueArray[i] = langueExp[0]; 
                        i++;
                    }
                }else{
                    String[] langueExp = nbLangues[0].split(":");
                    choixlangueArray[1] = langueExp[0];
                    }
                
                JComboBox<String> choixlangue = new JComboBox<>(choixlangueArray);
                choixlangue.setBackground(Color.decode("#ffb3ba"));
                choixlangue.setForeground(Color.WHITE);
                choixlangue.setFont(new Font("Apple Casual", Font.BOLD, 12));
                DefaultListCellRenderer listRenderer1 = new DefaultListCellRenderer();
                listRenderer1.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
                choixlangue.setRenderer(listRenderer1);

                JPanel choixLanguePanel = new JPanel();
                choixLanguePanel.setBackground(Color.decode("#ffdfba"));
                choixLanguePanel.setSize(new Dimension(120, 100));

                choixLanguePanel.add(choixlangue);

                JLabel choixlvlLabel = new JLabel("Choisissez la difficulté de votre exercice : ",SwingConstants.CENTER);
                choixlvlLabel.setFont(new Font("Apple Casual", Font.BOLD, 18));

                String choix[] = { "","1","2","3","4","5"};
                JComboBox<String> choixlvl = new JComboBox<>(choix);
                choixlvl.setBackground(Color.decode("#ffb3ba"));
                choixlvl.setForeground(Color.WHITE);
                choixlvl.setFont(new Font("Apple Casual", Font.BOLD, 12));
                DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
                listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
                choixlvl.setRenderer(listRenderer);

                JPanel choixLvlPanel = new JPanel();
                choixLvlPanel.setBackground(Color.decode("#ffdfba"));
                choixLvlPanel.setSize(new Dimension(120, 100));

                choixLvlPanel.add(choixlvl);

                JButton valider = new JButton("Valider");

                JPanel validerPanel = new JPanel();
                validerPanel.setBackground(Color.decode("#ffdfba"));
                validerPanel.setSize(new Dimension(100, 100));

                validerPanel.add(valider);

                JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
                sep.setForeground(Color.decode("#ffdfba"));
                sep.setBackground(Color.decode("#ffdfba"));

                exoPanelMere.add(sep);
                exoPanelMere.add(choixlangueLabel,BorderLayout.CENTER);
                exoPanelMere.add(choixLanguePanel,BorderLayout.CENTER);
                exoPanelMere.add(choixlvlLabel,BorderLayout.CENTER);
                exoPanelMere.add(choixLvlPanel,BorderLayout.CENTER);
                exoPanelMere.add(validerPanel,BorderLayout.CENTER);
                panelMere.add(exoPanelMere,BorderLayout.CENTER);
                exoPanelMere.setVisible(true);

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
                valider.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        choixlangueLabel.setVisible(false);
                        choixLanguePanel.setVisible(false);
                        choixlvlLabel.setVisible(false);
                        choixLvlPanel.setVisible(false);
                        validerPanel.setVisible(false);
                        JPanel testPanel = new JPanel(new GridLayout(3,1,10,10));
                        //récupérer quel exo élève veut dans le lvl pour le path
                        // String path = "./application/data/langues/"+infoExo.get("langue choisie")+"/"+infoExo.get("lvl choisi")+"/"+"exo1.csv"; //à changer
                        String path = "exo1.csv";
                        HashMap methodeEval = new HashMap<String,Integer>();
                        methodeEval.put("NR", 0);
                        methodeEval.put("incorrect", -1);
                        methodeEval.put("correct", 1);
                        try{
                            Exercice exoTest = new Exercice(CsvReader.liseurExo(path), infoExo.get("langue choisie"), 0, infoExo.get("lvl choisi"), methodeEval);

                        
                        String motsTroués = AfficheurExo.randomReponses(exoTest);
                        ArrayList<ArrayList<String>> questionsTroués = AfficheurExo.listeQuestions(exoTest);
                        //l'exo : skfjkfjh sjfhgkjfg #texfield# jkdfhkjds

                        JButton validerTest = new JButton("valider");
                        JLabel labelTest = new JLabel(motsTroués);
                        JLabel labelTest2 = new JLabel(questionsTroués.get(0).get(0));
                        JTextField reponseTest = new JTextField();
                        System.out.println(motsTroués);
                        testPanel.add(labelTest,BorderLayout.CENTER);
                        testPanel.add(labelTest2,BorderLayout.CENTER);
                        testPanel.add(validerTest,BorderLayout.CENTER);
                        exoPanelMere.add(testPanel,BorderLayout.CENTER);
                        
                        valider.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e) {
                            String reponseEleveTest = reponseTest.getText();
                            System.out.println(reponseEleveTest);
                            /* 
                             * for question in questionTrouées :
                             *  Elisabeth affiche une phrase à trous (avec des TextField)
                             *  Elles stocke les TextFields dans une ArrayList d'ArrayList de TextFields
                             * 
                            */
                            ArrayList<ArrayList<String>> stockReponsesEleve = new ArrayList<>(); //mettre toutes les réponses où élève répond (textfields) --> Aurélien récupère
                            /* 
                             * for question in questionTrouées :
                             *  On crée une arrayList vide
                             *  for trou in question
                             *      On remplit l'arraylist de la réponse
                             *  On ajoute l'arrayList à stockReponsesEleve
                             *   
                            */
                            
                            /* 
                             * Aurélien crée un objet correction à partir des réponses
                             * Il doit renvoyer, les indices et couleurs des endroits à changer
                             */
                            CorrectionExo c = new CorrectionExo();
                            AfficheurExo.listeReponses(exoTest, c);
                            
                            /*
                            * AfficheurCorrection -> ArrayList<AfficheurReeponse>
                            * 
                            * AfficheurReponse :
                            * AfficheurReponse.text -> "je suis des prouts et toi ?"
                            * AfficheurReponse.Indices -> [[8,11],[22,25]]
                            * AfficheurReponse.Couleurs -> [["RED"],["YELLOW"]]
                            */

                            //affichage correction : on montre toute les phrases et mots troués sont en vert (quand bon) et rouge(quand pas bon)
                                /*JLabel monLabel = new JLabel("Mon label");
                                Highlighter h = new DefaultHighlighter();
                                HighlightPainter p = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
                                try {
                                    h.addHighlight(0, 1, p);
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                monLabel.setHighlighter(h); */

                                /*
                                 * Apprenant a;
                                 * a.csv(); //{login : 57467, nom : Dupont, prénom : Clause, LangueExperienc : "Anglais:123&Français:18"}
                                 * --> faire une méthode qui modifie le csv avec ça
                                 */
                        }});
                            }
                            catch(Exception FileNotFoundException){
                                StackTraceElement[] stackTrace = FileNotFoundException.getStackTrace();
                                for (StackTraceElement line : stackTrace){
                                System.out.println("Erreur dans la méthode " + line.getMethodName() + " ligne " + line.getLineNumber());
                                }
                        }
                }});
                
        }});

        suivi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                vide.setVisible(false);
                realPanel.setVisible(false);

                JPanel exoPanelMere = new JPanel(new GridLayout(7,1));
                exoPanelMere.setBackground(Color.decode("#ffdfba"));
                exoPanelMere.setPreferredSize(new Dimension(600, 400));

                JProgressBar barreExp1 = new JProgressBar(0,10);
                JProgressBar barreExp2 = new JProgressBar(0,100);
                JProgressBar barreExp3 = new JProgressBar(0,1000);
                JProgressBar barreExp4 = new JProgressBar(0,10000);
                JProgressBar barreExp5 = new JProgressBar(0,100000);
                barreExp1.setBounds(35,40,165,30);
                barreExp2.setBounds(35,40,165,30);
                barreExp3.setBounds(35,40,165,30);
                barreExp4.setBounds(35,40,165,30);
                barreExp5.setBounds(35,40,165,30);
                barreExp1.setStringPainted(true);
                barreExp2.setStringPainted(true); 
                barreExp3.setStringPainted(true); 
                barreExp4.setStringPainted(true); 
                barreExp5.setStringPainted(true);  

                JLabel languesExp = new JLabel("Progression avant le prochain level : ",SwingConstants.CENTER);
                languesExp.setFont(new Font("Apple Casual", Font.BOLD, 18));
                exoPanelMere.add(languesExp);
                
                for(String langue : nbLangues){
                    String[] langueExp = langue.split(":");
                    int expEleve = Integer.parseInt(langueExp[1]);

                    JLabel langueBarre = new JLabel(langueExp[0]+" : ",SwingConstants.CENTER);
                    exoPanelMere.add(langueBarre);
                    langueBarre.setFont(new Font("Apple Casual", Font.BOLD, 18));

                    if(expEleve<=10){
                        barreExp1.setValue(expEleve);
                        exoPanelMere.add(barreExp1);
                    }else if(expEleve<=100){
                        barreExp2.setValue(expEleve);
                        exoPanelMere.add(barreExp2);
                    }else if(expEleve<=1000){
                        barreExp3.setValue(expEleve);
                        exoPanelMere.add(barreExp3);
                    }else if(expEleve<=10000){
                        barreExp4.setValue(expEleve);
                        exoPanelMere.add(barreExp4);
                    }else{
                        barreExp5.setValue(expEleve);
                        exoPanelMere.add(barreExp5);
                    }
                }

                panelMere.add(exoPanelMere,BorderLayout.CENTER);
                exoPanelMere.setVisible(true);
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