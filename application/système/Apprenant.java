package application.système;
import java.util.HashMap;
import java.util.ArrayList;

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

    public void addScore(String langue, int mod){
        int newScore = langueScore.get(langue)+mod;
        langueScore.put(langue,newScore);
    }

    public HashMap<String, String> csv(){
        HashMap<String,String> csv = new HashMap<>();
        ArrayList<String> langueScoreList = new ArrayList<>();
        for (String langue : langueScore.keySet()){
            langueScoreList.add(String.format("%s:%d",langue,langueScore.get(langue)));
        }
        String langueScoreString;
        langueScoreString = String.join("|",langueScoreList);
        csv.put("login",login);
        csv.put("nom",nom);
        csv.put("prénom",prenom);
        csv.put("LangueExpérience",langueScoreString);
        return csv;
    }
}
