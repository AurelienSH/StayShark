package application.système;

import java.util.ArrayList;

/**
 * Classe représentant une phrase dans le système.
 * Cette classe implémente l'interface {@link Question}
 * et permet de gérer les phrases utilisées pour les exercices.
 */
public class QuestionTrou implements Question {

    //tous les tokens splités sur "#"
    private ArrayList<Morceau> tokens;

    //liste des réponses dans l'ordre
    private ArrayList<String> reponses = new ArrayList<>();

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
     * Constructeur vide de QuestionTrou.
     */
    public QuestionTrou(){}

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
    public Correction correction( ArrayList<String> reponseEleve, Exercice exo) {
        CorrectionQuestionTrou phraseCorrigée = new CorrectionQuestionTrou(this, reponseEleve, exo.getMethodeEval());
        return phraseCorrigée;
    }


    /**
     * Retourne la réponse de l'apprenant sous forme de liste.
     * 
     * @return la réponse de l'apprenant sous forme de liste
     */
    public ArrayList<String> getReponseEleve(ArrayList<String> reponsesNonCorrigees){
        ArrayList<String> reponsesEleve= new ArrayList<String>();
        int i = 0;
        for (String reponse : this.reponses){

            String reponseEleve = reponsesNonCorrigees.get(i);
            /* 
            correct = 1
            incorrect = 2
            NR = 0
            */
            if (!reponseEleve.equals("") && !reponseEleve.equals("?")){
                boolean correct = (reponse.equals(reponseEleve));
                if (correct){
                    reponsesEleve.add("correct");
                }
                else{
                    reponsesEleve.add("incorrect");
                }
            }
            else{
                reponsesEleve.add("NR");

            }
            i++;
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