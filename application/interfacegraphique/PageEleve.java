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
 * L'élève pourra accéder aux exercices et au suivi de sa progression.
 */
public class PageEleve extends Page {
    JFrame frame;
    /**
     * Page que l'élève verra dans l'application (contenant les exos et sa barre d'expérience jusqu'au prochain level)
     * @param frameAJeter Jframe (PageHome) qui sera tué avant la création de la frame pour l'élève
     * @param infoUser dictionnaire contenant le login en clé et en valeur les autres infos (nom, prénom, langue et leur points)
     */
    public PageEleve(JFrame frameAJeter, Map infoUser){
        
        frameAJeter.dispose(); // on jette la page Home

        // création de l'apprenant
        Apprenant eleve = new Apprenant(infoUser.get("login").toString(), infoUser.get("nom").toString(), infoUser.get("prénom").toString(), infoUser.get("langue").toString());

        // création de la frame et tout son style
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

        // création des boutons exercice et suivi de l'expérience
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

        String[] nbLangues = infoUser.get("langue").toString().split("&"); // liste des langues apprises par l'apprenant
        
        // Quand on clique sur le bouton exercice : création d'un panel qui contient le choix de la langue et du level de l'exercice par l'apprenant
        exo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //on se débarasse des boutons exercice et suivi
                vide.setVisible(false);
                realPanel.setVisible(false);

                // création du panel de choix
                JPanel exoPanelMere = new JPanel(new GridLayout(10,1));
                exoPanelMere.setBackground(Color.decode("#ffdfba"));
                exoPanelMere.setPreferredSize(new Dimension(600, 400));

                Map<String, String> infoExo = new HashMap<String,String>(); // dico qui va contenir le choix de la langue de l'exercice, son level et le choix de l'exercice

                // choix de la langue pour l'exercice
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

                // choix du level de l'exercice
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

                // bouton récupérateur des informations de choix et de langue de l'exercice
                JButton valider = new JButton("Valider");

                JPanel validerPanel = new JPanel();
                validerPanel.setBackground(Color.decode("#ffdfba"));
                validerPanel.setSize(new Dimension(100, 100));

                validerPanel.add(valider);

                // juste pour faire plus joli dans le panel
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

                // Quand on clique sur le bouton valider : création d'un panel qui contient le choix de l'exercice (si jamais les prosseurs de langue ont mis plusieurs exercices l'élève pourra choisir lequel faire)
                valider.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        //on se débarasse de l'affichage d'avant
                        exoPanelMere.setVisible(false);
                        vide.setVisible(true);

                        // création du panel de choix de l'exercice
                        JPanel choixExoPathPanel = new JPanel(new GridLayout(3,1,10,10));
                        choixExoPathPanel.setBackground(Color.decode("#ffdfba"));

                        JLabel choixexoLabel = new JLabel("Choisissez l'exercice que vous souhaitez faire : ",SwingConstants.CENTER);
                        choixexoLabel.setFont(new Font("Apple Casual", Font.BOLD, 18));

                        // création du choix de l'exercice à faire parmis tous les exercices qui existent
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

                        // bouton qui récupèrera le choix de l'exercice
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

                        // récupération de l'exercice choisi par l'apprenant
                        choixexosBox.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e) {
                                e.getSource();
                                String exochoisi = (String) choixexosBox.getSelectedItem();
                                infoExo.put("exo choisi",exochoisi);
                        }});

                        // Quand on clique sur valdier : création d'un panel qui contient l'exercice à faire
                        valider2.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e) {
                                // on se débarasse de l'affichage d'avant
                                choixExoPathPanel.setVisible(false);
                                vide.setVisible(false);

                                // création du panel qui contient l'exercice
                                JPanel exerciceATrouPanelMere = new JPanel(new GridLayout(3,1,10,10));
                                exerciceATrouPanelMere.setBackground(Color.decode("#ffdfba"));
                                
                                // création du panel qui contient l'énoncé
                                JPanel exerciceATrouPanel = new JPanel(new GridLayout(2,1,10,10));
                                exerciceATrouPanel.setBackground(Color.decode("#ffdfba"));

                                //chemin du fichier de l'exercice
                                String path = "./application/data/langues/"+infoExo.get("langue choisie")+"/"+Evaluation.getNiveauVal(infoExo.get("lvl choisi").toString())+"/"+infoExo.get("exo choisi");

                                try{
                                    // on récupère la dureté de notation choisie par le prof qui a créé l'exercice
                                    String notation = CsvReader.getteurDuretéNotation(path);
                                    HashMap methodeEval = Evaluation.getChoixCorrectionDict(notation);

                                    //on créé l'exercice
                                    Exercice exoTest = new Exercice(CsvReader.liseurExo(path), infoExo.get("langue choisie"), infoExo.get("lvl choisi"), methodeEval); // Aurélien gogogo

                                String motsTroués = AfficheurExo.randomReponses(exoTest); // contient tous les mots à placer dans les trous

                                ArrayList<ArrayList<String>> questionsTroués = AfficheurExo.listeQuestions(exoTest); //liste de liste des mots troués et non troués de chaque phrase

                                // création du panel qui contient les phrases à trou
                                JPanel questionsATrouPanel = new JPanel(new GridLayout(questionsTroués.size(),1));
                                questionsATrouPanel.setBackground(Color.decode("#ffdfba"));

                                // on créé les phrases troués en transformant les mots troués en Jtextfield et les mots non troués en label
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

                                // bouton qui va récupérer les réponses de l'élève
                                JButton validerATrou = new JButton("valider");
                                JPanel validerATrouPanel = new JPanel();
                                validerATrouPanel.setBackground(Color.decode("#ffdfba"));
                                validerATrouPanel.setSize(new Dimension(100, 100));

                                validerATrouPanel.add(validerATrou);

                                // énoncé de l'exercice
                                JLabel enonceATrou = new JLabel("Ecrivez le bon mot dans le bon trou dans les phrases suivantes en utilisant ces mots : ");
                                JLabel labelATrou = new JLabel(motsTroués);

                                exerciceATrouPanel.add(enonceATrou,BorderLayout.CENTER);
                                exerciceATrouPanel.add(labelATrou,BorderLayout.CENTER);
                                exerciceATrouPanelMere.add(exerciceATrouPanel,BorderLayout.CENTER);
                                exerciceATrouPanelMere.add(questionsATrouPanel,BorderLayout.CENTER);
                                exerciceATrouPanelMere.add(validerATrouPanel,BorderLayout.CENTER);
                                panelMere.add(exerciceATrouPanelMere,BorderLayout.CENTER);

                                // Quand on clique sur valider : création du panel qui contient la correction de l'exo
                                validerATrou.addActionListener(new ActionListener(){
                                    public void actionPerformed(ActionEvent e) {
                                        // on jette l'ancien affichage
                                        exerciceATrouPanelMere.setVisible(false);

                                        // création du panel qui contient la correction de l'exrcice
                                        JPanel correctionPanel = new JPanel(new GridLayout(3,1,10,10));
                                        correctionPanel.setBackground(Color.decode("#ffdfba"));

                                        JLabel correctionLabel = new JLabel("Voici la correction de l'exercice : ");
                                        correctionPanel.add(correctionLabel);

                                        // bouton qui permet de revenir à la page d'acceuil de l'élève (avec les boutons exercice et suivre)
                                        JButton quittercorrection = new JButton("Quitter");
                                        JPanel quittercorrectionPanel = new JPanel();
                                        quittercorrectionPanel.setBackground(Color.decode("#ffdfba"));
                                        quittercorrectionPanel.setSize(new Dimension(100, 100));

                                        quittercorrectionPanel.add(quittercorrection);

                                        ArrayList<String> reponsesEleve = new ArrayList<>(); // liste qui contient les réponses de l'apprenant

                                        // on récupère toutes les réponses de l'apprenant (chaque phrase est contenu dans un panel et toutes les phrases sont dans un gros panel d'où la méthode de passer par les component du panel principal des phrases pour accéder aux réponses)
                                        for (Component component : questionsATrouPanel.getComponents()) {
                                            if (component instanceof JPanel){
                                                reponsesEleve.clear(); // on réinitialise la variable
                                                for(Component panelDansPanel : ((JPanel) component).getComponents()){
                                                    if (panelDansPanel instanceof JTextField){ // on récupère les réponses pour chaque phrase
                                                        reponsesEleve.add(((JTextField) panelDansPanel).getText());}
                                                }
                                                exoTest.addReponseEleve(new ArrayList<String>(reponsesEleve));
                                            }
                                        }

                                        // liste de toutes les corrections pour chaque mot troué de chaque phrase
                                        ArrayList<JTextPane> listeRep = AfficheurExo.listeReponses(exoTest);

                                        // création du panel qui aura toutes les corrections
                                        JPanel listeRepPanel = new JPanel(new GridLayout(listeRep.size(),1,10,10));
                                        listeRepPanel.setBackground(Color.decode("#ffdfba"));

                                        for(JTextPane correctionJTextPane : listeRep){
                                            listeRepPanel.add(correctionJTextPane);
                                       }

                                       correctionPanel.add(listeRepPanel);
                                       correctionPanel.add(quittercorrectionPanel);

                                       // création de l'évaluation de l'apprenant et donc son changement de points
                                       Evaluation.evalue(eleve, exoTest);

                                       // changements de points que l'élève a dans la langue qu'il a appris à l'instant
                                       Map<String, String> infosUserChange = eleve.csv();   
                                       String aChangerCsv = infosUserChange.get("login")+","+infosUserChange.get("nom")+","+infosUserChange.get("prénom")+","+infosUserChange.get("LangueExpérience");
                                       try{CsvReader.modificationCsvEleve("./application/data/dataeleve.csv",aChangerCsv);}catch(Exception IOException){System.out.println("Problème au niveau du csv ");}
                                                                           
                                            panelMere.add(correctionPanel,BorderLayout.CENTER);

                                            // quand on clique sur quitter on revient à la page d'accueil de l'élève (avec les boutons exercice et suivre)
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

        // Quand on clique sur suivi : création d'un panel qui montre la barre d'xp de l'élève (dans combien de temps il augmente de niveau linguistique)
        suivi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // on se débarasse de la page d'accueil
                vide.setVisible(false);
                realPanel.setVisible(false);

                // création du panel qui contient les barre d'expérience de l'élève dans ses langues
                JPanel exoPanelMere = new JPanel(new GridLayout(7,1,10,10));
                exoPanelMere.setBackground(Color.decode("#ffdfba"));
                exoPanelMere.setPreferredSize(new Dimension(600, 400)); 

                JLabel languesExp = new JLabel("Progression avant le prochain level : ",SwingConstants.CENTER);
                languesExp.setFont(new Font("Apple Casual", Font.BOLD, 18));
                exoPanelMere.add(languesExp);
                
                // boucle qui créé la barre d'xp en fonction des points que l'élève a accumulé dans la langue qu'il apprend (créé une barre d'xp pour chaque langue)
                for(String langue : nbLangues){

                    JProgressBar barreExp1 = new JProgressBar(0,10); //A1
                    JProgressBar barreExp2 = new JProgressBar(0,100); //A2
                    JProgressBar barreExp3 = new JProgressBar(0,1000); //B1
                    JProgressBar barreExp4 = new JProgressBar(0,10000); //B2
                    JProgressBar barreExp5 = new JProgressBar(0,100000); //C1
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

                    // on récupère les points de l'élève dans la langue traitée
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

                // bouton qui permet de revenir à l'accueil et quitter cette page
                JButton quitter = new JButton("Quitter");
                exoPanelMere.add(quitter);

                // quand on clique sur quitter on revient à l'accueil de l'élève (avec le bouton exercice et suivi)
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