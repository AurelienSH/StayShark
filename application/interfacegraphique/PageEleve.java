package application.interfacegraphique;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import application.système.*;
import application.controleur.*;
import java.util.ArrayList;
import java.io.File;

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
         * - barre d'xp danc combien de temps on atteint le lvl ✔️
         * - plateforme d'exos : 
         *                      choix de la langue si plusieurs ✔️
         *                      choix du lvl✔️
         *                      exos ✔️
         *                      récap de toutes les fautes après l'exo ⚠️A VOIR⚠️
         * 
         */
        
        frameAJeter.dispose();

        Apprenant eleve = new Apprenant(infoUser.get("login").toString(), infoUser.get("nom").toString(), infoUser.get("prénom").toString(), infoUser.get("langue").toString());

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
        
        // Panel qui contient le choix de la langue et du level de l'exo //
        exo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                vide.setVisible(false);
                realPanel.setVisible(false);

                Map<String, String> infoExo = new HashMap<String,String>();

                JPanel exoPanelMere = new JPanel(new GridLayout(10,1));
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

                String choix[] = { "","A1","A2","B1","B2","C1"};
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

                // Panel qui contient le choix de l'exo //
                valider.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        exoPanelMere.setVisible(false);
                        vide.setVisible(true);

                        JPanel choixExoPathPanel = new JPanel(new GridLayout(3,1,10,10));
                        choixExoPathPanel.setBackground(Color.decode("#ffdfba"));

                        JLabel choixexoLabel = new JLabel("Choisissez l'exercice que vous souhaitez faire : ",SwingConstants.CENTER);
                        choixexoLabel.setFont(new Font("Apple Casual", Font.BOLD, 18));

                        File folder = new File("./application/data/langues/"+infoExo.get("langue choisie")+"/"+Evaluation.getNiveauVal(infoExo.get("lvl choisi").toString())+"/");
                        File[] listOfFiles = folder.listFiles();
                        String choixexos[] = new String[listOfFiles.length+1];
                        choixexos[0] = "";
                        int i = 1;
                        for(File file : listOfFiles){
                            String fileString = file.toString();
                            choixexos[i] = fileString.substring(fileString.length()-8,fileString.length());
                            i++;
                        }
                        JComboBox<String> choixexosBox = new JComboBox<>(choixexos);
                        choixexosBox.setBackground(Color.decode("#ffb3ba"));
                        choixexosBox.setForeground(Color.WHITE);
                        choixexosBox.setFont(new Font("Apple Casual", Font.BOLD, 12));
                        DefaultListCellRenderer listRendererExos = new DefaultListCellRenderer();
                        listRendererExos.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
                        choixexosBox.setRenderer(listRendererExos);

                        JPanel choixexoPanel = new JPanel();
                        choixexoPanel.setBackground(Color.decode("#ffdfba"));
                        choixexoPanel.setSize(new Dimension(120, 100));

                        choixexoPanel.add(choixexosBox);

                        JButton valider2 = new JButton("Valider");

                        JPanel validerPanel2 = new JPanel();
                        validerPanel2.setBackground(Color.decode("#ffdfba"));
                        validerPanel2.setSize(new Dimension(100, 100));

                        validerPanel2.add(valider2);

                        choixExoPathPanel.add(choixexoLabel,BorderLayout.CENTER);
                        choixExoPathPanel.add(choixexoPanel,BorderLayout.CENTER);
                        choixExoPathPanel.add(validerPanel2,BorderLayout.CENTER);
                        panelMere.add(choixExoPathPanel,BorderLayout.CENTER);
                
                        choixExoPathPanel.setVisible(true);

                        choixexosBox.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e) {
                                e.getSource();
                                String exochoisi = (String) choixexosBox.getSelectedItem();
                                infoExo.put("exo choisi",exochoisi);
                        }});

                        // Panel qui contient l'exo en lui-même //
                        valider2.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e) {
                                choixExoPathPanel.setVisible(false);
                                vide.setVisible(false);

                                JPanel exerciceATrouPanelMere = new JPanel(new GridLayout(3,1,10,10));
                                exerciceATrouPanelMere.setBackground(Color.decode("#ffdfba"));
                                

                                JPanel exerciceATrouPanel = new JPanel(new GridLayout(2,1,10,10));
                                exerciceATrouPanel.setBackground(Color.decode("#ffdfba"));


                                String path = "./application/data/langues/"+infoExo.get("langue choisie")+"/"+Evaluation.getNiveauVal(infoExo.get("lvl choisi").toString())+"/"+infoExo.get("exo choisi");

                                //implanter le vrai dico correction Aurélien si tu as besoin j'ai fait une méthode qui te retourne la 1ere ligne du csv (qui contient la méthode d'éval --> CsvReader.getteurDuretéNotation(path))
                                try{ // exemple ici d'utilisation
                                    String notation = CsvReader.getteurDuretéNotation(path);
                                
                                HashMap methodeEval = Evaluation.getChoixCorrectionDict(notation);
                                    Exercice exoTest = new Exercice(CsvReader.liseurExo(path), infoExo.get("langue choisie"), infoExo.get("lvl choisi"), methodeEval); // Aurélien gogogo


                                String motsTroués = AfficheurExo.randomReponses(exoTest);

                                ArrayList<ArrayList<String>> questionsTroués = AfficheurExo.listeQuestions(exoTest);

                                JPanel questionsATrouPanel = new JPanel(new GridLayout(questionsTroués.size(),1));
                                questionsATrouPanel.setBackground(Color.decode("#ffdfba"));

                                Integer i = 1;
                                for(ArrayList<String> innerList : questionsTroués) {
                                    JPanel test = new JPanel();
                                    test.setBackground(Color.decode("#ffdfba"));

                                    JLabel nombre = new JLabel(i.toString()+". ");
                                    test.add(nombre);
                                    i++;
                                    for(String words : innerList) {
                                        if(words.contains("...")){
                                            JTextField motATrouver = new JTextField("?",words.length());
                                            test.add(motATrouver);
                                        }else{
                                            JLabel mots = new JLabel(words);
                                            test.add(mots);
                                        }
                                    }
                                    questionsATrouPanel.add(test);
                                }



                                JButton validerATrou = new JButton("valider");
                                JPanel validerATrouPanel = new JPanel();
                                validerATrouPanel.setBackground(Color.decode("#ffdfba"));
                                validerATrouPanel.setSize(new Dimension(100, 100));

                                validerATrouPanel.add(validerATrou);

                                JLabel enonceATrou = new JLabel("Ecrivez le bon mot dans le bon trou dans les phrases suivantes en utilisant ces mots : ");
                                JLabel labelATrou = new JLabel(motsTroués);

                                exerciceATrouPanel.add(enonceATrou,BorderLayout.CENTER);
                                exerciceATrouPanel.add(labelATrou,BorderLayout.CENTER);
                                exerciceATrouPanelMere.add(exerciceATrouPanel,BorderLayout.CENTER);
                                exerciceATrouPanelMere.add(questionsATrouPanel,BorderLayout.CENTER);
                                exerciceATrouPanelMere.add(validerATrouPanel,BorderLayout.CENTER);
                                panelMere.add(exerciceATrouPanelMere,BorderLayout.CENTER);

                                // Panel qui contient la correction de l'exo
                                validerATrou.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent e) {
                                        exerciceATrouPanelMere.setVisible(false);

                                        JPanel correctionPanel = new JPanel(new GridLayout(3,1,10,10));
                                        correctionPanel.setBackground(Color.decode("#ffdfba"));

                                        JLabel correctionLabel = new JLabel("Voici la correction de l'exercice : ");
                                        correctionPanel.add(correctionLabel);

                                        JButton quittercorrection = new JButton("Quitter");
                                        JPanel quittercorrectionPanel = new JPanel();
                                        quittercorrectionPanel.setBackground(Color.decode("#ffdfba"));
                                        quittercorrectionPanel.setSize(new Dimension(100, 100));

                                        quittercorrectionPanel.add(quittercorrection);

                                        /* 
                                        * for question in questionTrouées :
                                        *  Elisabeth affiche une phrase à trous (avec des TextField)
                                        *  Elles stocke les TextFields dans une ArrayList d'ArrayList de TextFields qui est stockReponsesEleve (c'est fait ehe)
                                        * 
                                        */
                                        ArrayList<ArrayList<String>> stockReponsesEleve = new ArrayList<>();
                                        ArrayList<String> reponsesEleve = new ArrayList<>();
                                        for (Component component : questionsATrouPanel.getComponents()) {
                                            if (component instanceof JPanel){
                                                reponsesEleve.clear();
                                                for(Component panelDansPanel : ((JPanel) component).getComponents()){
                                                    if (panelDansPanel instanceof JTextField){
                                                        reponsesEleve.add(((JTextField) panelDansPanel).getText());}
                                                }
                                                exoTest.addReponseEleve(new ArrayList<String>(reponsesEleve));
                                            }
                                        }

                                        ArrayList<JTextPane> listeRep = AfficheurExo.listeReponses(exoTest);

                                        JPanel listeRepPanel = new JPanel(new GridLayout(listeRep.size(),1,10,10));
                                        listeRepPanel.setBackground(Color.decode("#ffdfba"));

                                        for(JTextPane bloub : listeRep){
                                            listeRepPanel.add(bloub);
                                       }

                                       correctionPanel.add(listeRepPanel);
                                       correctionPanel.add(quittercorrectionPanel);

                                       Evaluation.evalue(eleve, exoTest);

                                       Map<String, String> infosUserChange = eleve.csv();   
                                       String aChangerCsv = infosUserChange.get("login")+","+infosUserChange.get("nom")+","+infosUserChange.get("prénom")+","+infosUserChange.get("LangueExpérience");
                                       try{CsvReader.modificationCsvEleve("./application/data/dataeleve.csv",aChangerCsv);}catch(Exception IOException){System.out.println("Problème au niveau du csv ");}
                                                                           
                                       
                                        /* 
                                        * Aurélien crée un objet correction à partir des réponses
                                        * Il doit renvoyer, les indices et couleurs des endroits à changer
                                        */

                                        // ArrayList<JTextPane> listeRep = AfficheurExo.listeReponses(exoTest); // Création des phrases avec les jolies couleurs

                                        // for(JTextPane bloub : listeRep){
                                        //     // correctionPanel.add(bloub);
                                        //     System.out.println(bloub);
                                        // }
                                        
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

                                            panelMere.add(correctionPanel,BorderLayout.CENTER);

                                            quittercorrection.addActionListener(new ActionListener(){
                                                public void actionPerformed(ActionEvent e) {
                                                    correctionPanel.setVisible(false);
                                                    vide.setVisible(true);
                                                    realPanel.setVisible(true);
                                            }});
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
                
        }});

        suivi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                vide.setVisible(false);
                realPanel.setVisible(false);

                JPanel exoPanelMere = new JPanel(new GridLayout(7,1,10,10));
                exoPanelMere.setBackground(Color.decode("#ffdfba"));
                exoPanelMere.setPreferredSize(new Dimension(600, 400)); 

                JLabel languesExp = new JLabel("Progression avant le prochain level : ",SwingConstants.CENTER);
                languesExp.setFont(new Font("Apple Casual", Font.BOLD, 18));
                exoPanelMere.add(languesExp);
                
                for(String langue : nbLangues){

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

                JButton quitter = new JButton("Quitter");
                exoPanelMere.add(quitter);

                quitter.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        vide.setVisible(true);
                        realPanel.setVisible(true);
                        exoPanelMere.setVisible(false);
                }});

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