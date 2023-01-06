package application.système;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Classe représentant la correction d'une phrase dans le système.
 * Cette classe étend la classe abstraite {@link Correction} et permet de gérer
 * la correction des phrases utilisées pour les exercices.
 */
public class CorrectionQuestionTrou extends Correction {
    private ArrayList<String> reponses; // liste des résultats des réponses données par l'apprenant
    private int note=0; // note obtenue pour cette phrase

    private ArrayList<String> reponsesCorrigees;
    /**
     * Constructeur de la correction de la phrase.
     *
     * @param reponsesEleve liste des réponses données par l'apprenant
     * @param exo           l'exercice associé à cette phrase
     */
    public CorrectionQuestionTrou(QuestionTrou q, ArrayList<String> reponsesEleve, HashMap<String, Integer> methodeEval) {
        reponsesCorrigees = q.getReponseEleve(reponsesEleve);
        System.out.println(reponsesCorrigees);
        // Calcul de la note en fonction des réponses données par l'apprenant et de la méthode d'évaluation de l'exercice
        for (String rep : reponsesCorrigees) {
            note += methodeEval.get(rep);
        }
    }


    /**
    Retourne la note obtenue pour la phrase.
    @return la note obtenue pour la phrase
    */
    public Integer getNote() {
        return this.note;
    }
    /**
    
    Retourne les réponses de l'apprenant pour la phrase.
    @return les réponses de l'apprenant pour la phrase
    */
    public ArrayList<String> getReponses() {
        return reponses;
    }

    public ArrayList<String> getReponsesCorrigees() {
        return reponsesCorrigees;
    }

    /**
     * Retourne une chaîne de caractères représentant la corection sous la forme CorrectionQuestionTrou[note=%.2f, reponses=%s]
     * 
     * @return une chaîne de caractères représentant la correction
     */
    @Override
    public String toString() {
        return String.format("CorrectionQuestionTrou[note=%.2f, reponses=%s]", note, Arrays.toString(reponses.toArray()));
}

    
}