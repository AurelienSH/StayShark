package application.système;
import java.util.HashMap;

public class Apprenant extends ActeurHumain {
    String login;
    String nom;
    String prenom;
    HashMap<String, Integer> langueScore = new HashMap<String, Integer>();


    Apprenant(String str1, String str2, String str3, String str4){
        super(str1, str2, str3);

        String[] langueScore_list=str4.split("|");

        for (String ls : langueScore_list) {
            langueScore.put(ls.split(":")[0], Integer.parseInt(ls.split(":")[1]));
        }
        
    } 

    public int getNiveau(String langue){
        int niveau=(langueScore.get(langue))/8;
        return niveau;
    }

    public int getScore(String langue){
        int score=(langueScore.get(langue));
        return score;
    }
}
