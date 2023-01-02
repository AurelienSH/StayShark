package application.système;
import java.util.ArrayList;

public interface Question {
    /**
     * Retourne la question sous la forme d'une chaîne de caractères ou d'une liste de chaînes de caractères.
     * 
     * @return la question sous la forme d'une chaîne de caractères ou d'une liste de chaînes de caractères
     */
    public Object getQuestion();

    /**
     * Retourne la liste des réponses attendues pour la question.
     * 
     * @return la liste des réponses attendues pour la question
     */
    public ArrayList<String> getReponses();
    
    /**
     * Corrige une réponse donnée par un élève pour une question donnée dans un exercice donné.
     * 
     * @param reponseEleve la réponse donnée par l'élève
     * @param exo l'exercice contenant la question
     * @return une correction de la réponse de l'élève
     */
    public Correction correction(ArrayList<String> reponseEleve, Exercice exo);
}
