package application.système;
import java.util.HashMap;
import java.util.ArrayList;

public class Apprenant extends ActeurHumain {
    HashMap<String, Float> langueScore = new HashMap<String, Float>();


    Apprenant(String login, String nom, String prenom, String langueScore){
        super(login, nom, prenom);

        String[] langueScore_list=langueScore.split("|");

        for (String ls : langueScore_list) {
            this.langueScore.put(ls.split(":")[0], Float.parseFloat(ls.split(":")[1]));
        }
        
    }

    public float getNiveau(String langue){
        float niveau=(langueScore.get(langue))/8;
        return niveau;
    }

    public float getScore(String langue){
        float score=(langueScore.get(langue));
        return score;
    }

    public void addScore(String langue, float mod){
        float newScore = langueScore.get(langue)+mod;
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

    public void faireExo(){

    }

    public String toString(){
        return("Etudiant"+prenom+nom);
    }
}
