package application.système;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Classe représentant un apprenant dans le système.
 * Cette classe étend la classe abstraite {@link ActeurHumain}
 * et implémente les méthodes abstraites déclarées dans cette classe.
 */
public class Apprenant extends ActeurHumain implements Niveau {

    /** Niveaux de l'apprenant par langue */
    protected HashMap<String,String> langueNiveau = new HashMap<>();
    
    
    /** Map associant une langue à un score */
    protected HashMap<String, Float> langueScore = new HashMap<>();

    /**
     * Constructeur de l'apprenant.
     * 
     * @param login login de l'apprenant
     * @param nom nom de l'apprenant
     * @param prenom prénom de l'apprenant
     * @param langueScore chaîne de caractères représentant les langues et scores de l'apprenant
     */
    public Apprenant(String login, String nom, String prenom, String langueScore) {
        super(login, nom, prenom);

        // Sépare la chaîne de caractères en langues et scores
        String[] langueScoreList = langueScore.split("&");

        // Ajoute chaque langue et score à la map
        for (String ls : langueScoreList) {
            this.langueScore.put(ls.split(":")[0], Float.parseFloat(ls.split(":")[1]));
        }

        for (String langue : this.langueScore.keySet()){
            langueNiveau.put(langue, determinerNiveau(this.langueScore.get(langue)));
        }

    }

    /**
     * Retourne le niveau de l'apprenant dans la langue spécifiée.
     * Le niveau est déterminé en divisant le score par 8.
     * 
     * @param langue la langue à laquelle on veut connaître le niveau de l'apprenant
     * @return le niveau de l'apprenant dans la langue spécifiée
     */
    public String getNiveau(String langue) {
        String niveau = determinerNiveau(langueScore.get(langue));
        return niveau;
    }

    public int getIntNiveau(String langue){
        String nivLang = this.langueNiveau.get(langue);
        return Evaluation.getNiveauVal(nivLang);
    }
    // Méthode de classe pour déterminer le niveau de l'apprenant en fonction de ses points
    private static String determinerNiveau(float points) {
        if (points > 800) {
        return "C1";
        } else if (points > 400) {
        return "B2";
        } else if (points > 200) {
        return "B1";
        } else if (points > 100) {
        return "A2";
        } else {
        return "A1";
        }
    }

    /**
     * Retourne le score de l'apprenant dans la langue spécifiée.
     * 
     * @param langue la langue à laquelle on veut connaître le score de l'apprenant
     * @return le score de l'apprenant dans la langue spécifiée
     */
    public float getScore(String langue) {
        float score = (langueScore.get(langue));
        return score;
    }

    /**
     * Ajoute un score spécifié à la langue spécifiée.
     * 
     * @param langue la langue à laquelle on veut ajouter un score
     * @param mod le score à ajouter
     */
    public void addScore(String langue, float mod) {
        float newScore = langueScore.get(langue) + mod;
        langueScore.put(langue, newScore);
    }

    /**
     * Retourne une map associant les informations de l'apprenant
     * à des chaînes de caractères pour être enregistrées dans un fichier CSV.
     * 
     * @return une map associant les informations de l'apprenant à des chaînes de caractères
     */
    public HashMap<String, String> csv() {
        HashMap<String, String> csv = new HashMap<>();
        ArrayList<String> langueScoreList = new ArrayList<>();
        for (String langue : langueScore.keySet()) {
            langueScoreList.add(String.format("%s:%2f", langue, langueScore.get(langue)));
        }
        String langueScoreString = String.join("&", langueScoreList);
        csv.put("login", login);
        csv.put("nom", nom);
        csv.put("prénom", prenom);
        csv.put("LangueExpérience", langueScoreString);
        return csv;
    }

    public void faireExo(){

    }

    /**
     * Retourne une chaîne de caractères représentant l'apprenant sous la forme "Apprenant[login=<login>, nom=<nom>, prenom=<prenom>, langueScore=<langueScore>]"
     * 
     * @return une chaîne de caractères représentant l'apprenant
     */
    @Override
    public String toString() {
        return String.format("Apprenant[login=%s, nom=%s, prenom=%s, langueScore=%s]", login, nom, prenom, langueScore);
    }


}
