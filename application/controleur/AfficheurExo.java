package application.controleur;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
        ArrayList<ArrayList<String>> reponsesEleve = exo.getReponsesEleve();

        ArrayList<JTextPane> listeAffichage = new ArrayList<>();

        int i = 0;
        // Ajoutez chaque mot dans le document avec la couleur appropriée
        for (QuestionTrou phrase : phrases) {
            JTextPane textPane = new JTextPane();
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
                listeAffichage.add(textPane);
                i++;
            }
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
    }
