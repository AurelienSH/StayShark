package application.controleur;
import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import application.système.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class AfficheurExo extends Afficheur {
    public static String randomReponses(Exercice exo){
        StringBuilder sb = new StringBuilder();
        for (String rep : exo.randomReponses()) {
            sb.append(rep);
            sb.append(", ");
        }
        String resultat = sb.toString();
        resultat =  resultat.substring(0,resultat.length()-2);
        return resultat; // Affiche une string qui mets les réponses séparées par dees virgules
        /* pour l'exo : 
            Je suis #des# prouts et #toi# ?
            Tu es un #pouet# ohlala hein !
            -->"des, pouet, toi"
            */
    }

    public static ArrayList<ArrayList<String>> listeQuestions(Exercice exo){
        return exo.getQuestions();
        /* pour l'exo : 
            Je suis #des# prouts et #toi# ?
            Tu es un #pouet# ohlala hein !*/ 
        // [["je suis ","..."," prouts et ","..."," ?"],[Tu es un ","..."," ohlala hein !"]]
    }

    public static ArrayList<JTextPane> listeReponses(Exercice exo){
        ArrayList<QuestionTrou> phrases = exo.getPhrases();

        CorrectionExo corr = exo.corrige();
        ArrayList<ArrayList<String>> reponsesEleve = corr.getValeursCorrigees();
        ArrayList<JTextPane> listeAffichage = new ArrayList<>();

        int i = 0;
        // Ajoutez chaque mot dans le document avec la couleur appropriée
        for (QuestionTrou phrase : phrases) {
            JTextPane textPane = new JTextPane();
            textPane.setBackground(Color.decode("#ffdfba"));
            StyledDocument doc = textPane.getStyledDocument();
            ArrayList<Morceau> listeMorceaux = phrase.getTokens();
            int j = 0;
            for (Morceau m : listeMorceaux) {
                SimpleAttributeSet attributes = new SimpleAttributeSet();
                if (m instanceof MorceauVariable) {

                    String reponseEleve = reponsesEleve.get(i).get(j);
                    if (reponseEleve.equals("NR")) {
                        StyleConstants.setForeground(attributes, Color.YELLOW);
                    } else if (reponseEleve.equals("correct")) {
                        StyleConstants.setForeground(attributes, Color.GREEN);
                    } else if (reponseEleve.equals("incorrect")) {
                        StyleConstants.setForeground(attributes, Color.RED);
                    }
                    j++;
                }

                try {
                    doc.insertString(doc.getLength(), m.reponse(), attributes);
                } catch (Exception BadLocationException) {
                    BadLocationException.printStackTrace();
                }
            }
            listeAffichage.add(textPane);
            i++;
        }
        return listeAffichage;
    }


        /*JLabel monLabel = new JLabel("Mon label");
        Highlighter h = new DefaultHighlighter();
        HighlightPainter p = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
        try {
            h.addHighlight(0, 1, p);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        monLabel.setHighlighter(h); */
        /* pour l'exo : 
            Je suis #des# prouts et #toi# ?
            Tu es un #pouet# ohlala hein !*/ 
        // [["je suis ","1_des"," prouts et ","0_toi"," ?"],[Tu es un ","1_pouet"," ohlala hein !"]] Les réponses où l'élève a eu bon sont précédées d'un 1_ (vert), les mauvaises d'un 0_ (rouge) et les NR d'un 2_ (jaune)

        /*
         * AfficheurCorrection -> ArrayList<AfficheurReeponse>
         * 
         * AfficheurReponse :
         * AfficheurReponse.text -> "je suis des prouts et toi ?"
         * AfficheurReponse.Indices -> [[8,11],[22,25]]
         * AfficheurReponse.Couleurs -> [["RED"],["YELLOW"]]
        */
        public static void main(String[] args) throws FileNotFoundException {
            String currentDirectory = System.getProperty("user.dir");
            System.out.println("Current working directory : " + currentDirectory);
            ArrayList<String> reponses = new ArrayList<>();
            reponses.add("b");
            reponses.add("b");

            HashMap methodeEval = new HashMap<String,Integer>();
            methodeEval.put("NR", 0);
            methodeEval.put("incorrect", -1);
            methodeEval.put("correct", 1);
            Exercice exo = new Exercice(CsvReader.liseurExo("./application/data/langues/Français/1/exo1.csv"), "Anglais", 0, "2", methodeEval);
            exo.addReponseEleve(reponses);
            exo.addReponseEleve(reponses);
            exo.addReponseEleve(reponses);
            reponses.add("b");
            exo.addReponseEleve(reponses);

            ArrayList<JTextPane> liste = listeReponses(exo);
            System.out.println (liste);
        }

}
