package application.système;

import java.util.ArrayList;

/**
 * Classe représentant une phrase dans le système.
 * Cette classe implémente l'interface {@link Question}
 * et permet de gérer les phrases utilisées pour les exercices.
 */
public class QuestionTrou implements Question {

    private ArrayList<Morceau> tokens; //tous les tokens splités sur "#"

    private ArrayList<String> reponses = new ArrayList<>(); //liste des réponses dans l'ordre

    /**
     * Constructeur de la phrase.
     * 
     * @param tokens liste des morceaux de la phrase
     */
    public QuestionTrou(ArrayList<Morceau> tokens) {
        this.tokens = tokens;
        for (Morceau m : tokens) {
            if (m instanceof MorceauVariable) {
                reponses.add(m.reponse());
            }
        }
    }

    /**
     * Retourne la liste de tokens de la phrase.
     * 
     * @return la liste de tokens de la phrase
     */
    public ArrayList<Morceau> getTokens() {
        return tokens;
    }


    /**
     * Retourne la question telle qu'elle sera vue par l'apprenant.
     * 
     * @return la question telle qu'elle sera vue par l'apprenant
     */
    public ArrayList<String> getQuestion() {
        ArrayList<String> stringEleve = new ArrayList<>();

        // Ajout de tous les tokens dans une nouvelle liste
        for (Morceau m : tokens) {
            stringEleve.add(m.question());
        }

        return stringEleve;
    }


    /**
     * Retourne la chaîne de caractères correcte de la phrase.
     * 
     * @return la chaîne de caractères correcte de la phrase
     */
    public String stringCorrecte() {
        ArrayList<String> stringCorrecte = new ArrayList<>();

        // Ajout de tous les tokens dans une nouvelle liste
        for (Morceau m : tokens) {
            stringCorrecte.add(m.reponse());
        }

        // Utilisation d'un StringBuffer pour réécrire la chaîne de caractère
        StringBuffer joiner = new StringBuffer();

        for (String token : stringCorrecte) {
            joiner.append(token);
            joiner.append(" ");
        }

        // Transformation en chaîne de caractères
        String str = joiner.toString();

        return str;
    }


    /**
     * Retourne les réponses de la phrase sous forme de liste.
     * 
     * @return les réponses de la phrase sous forme de liste
     */
    public ArrayList<String> getReponses() {
        return this.reponses;
    }

    
    /**
     * Effectue la correction de la phrase en fonction de la réponse de l'apprenant
     * et de l'exercice associé.
     * 
     * @param reponseEleve la réponse de l'apprenant
     * @param exo l'exercice associé à la phrase
     * @return la correction de la phrase
     */
    @Override
    public Correction correction(ArrayList<String> reponseEleve, Exercice exo) {
        CorrectionQuestionTrou phraseCorrigée = new CorrectionQuestionTrou(reponseEleve, exo);
        return phraseCorrigée;
    }


    /**
     * Retourne la réponse de l'apprenant sous forme de liste.
     * 
     * @return la réponse de l'apprenant sous forme de liste
     */
    public ArrayList<String> getReponseEleve(){
        ArrayList<String> reponsesEleve= new ArrayList<String>();
        for (String reponse : this.reponses){

            String reponseEleve = "oh"; //machin.getReponseFromInterface();
            /* 
            correct = 1
            incorrect = 2
            NR = 0
            */
            if (reponseEleve != "" || reponseEleve != "?"){
                boolean correct = (reponse.equals(reponseEleve));
                if (correct){
                    reponsesEleve.add("correct");
                }
                else{
                    reponsesEleve.add("incorrect");
                }
            }
            else{
                reponsesEleve.add("");
            }
        }
        return reponsesEleve;
        }

    /**
     * Retourne une chaîne de caractères représentant la phrase sous la forme "QuestionTrou[tokens=<tokens>, reponses=<reponses>]"
     * 
     * @return une chaîne de caractères représentant la phrase
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QuestionTrou [");
        if (tokens != null) {
            builder.append("tokens=");
            builder.append(tokens);
            builder.append(", ");
        }
        if (reponses != null) {
            builder.append("reponses=");
            builder.append(reponses);
        }
        builder.append("]");
        return builder.toString();
    }
    

    
}