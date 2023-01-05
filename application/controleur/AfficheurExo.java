package application.controleur;
import java.util.ArrayList;
import application.système.*;;

public class AfficheurExo extends Afficheur {
    public static String randomReponses(Exercice exo){
        StringBuffer sb = new StringBuffer();
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

    public static ArrayList<ArrayList<String>> listeReponses(Exercice exo, CorrectionExo corr){
        ArrayList<ArrayList<String>> b = new ArrayList<>();
        return b;
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


}
