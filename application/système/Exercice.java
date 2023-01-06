package application.système;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

/**
 * Cette classe représente un exercice qui peut être composé de plusieurs questions.
 * Chaque question est représentée par une instance de l'interface `Question`.
 */
public class Exercice {

    // Langue de l'exercice
    final String langue;

    // Liste des questions de l'exo
    ArrayList<ArrayList<String>> questions = new ArrayList<>();

    // Liste des phrases de l'exo
    ArrayList<QuestionTrou> phrases = new ArrayList<>();

    ArrayList<ArrayList<String>> reponses = new ArrayList<>();

    ArrayList<String> randomReponses = new ArrayList<>();

    ArrayList<ArrayList<String>> reponsesEleve = new ArrayList<>();

    // Points de l'exo
    int bareme = 0;

    // Niveau de l'exo
    final String niveau;

    // Methode d'évaluation de l'exo
    final HashMap<String,Integer> methodeEval;

    /**
     * Crée un nouvel objet Exercice avec la liste des questions données, la langue, le niveau, le bareme et la méthode d'évaluation.
     *
     * @param textePhrases liste de chaînes de caractères représentant les questions de l'exercice
     * @param langVal la langue de l'exercice
     * @param niveauVal le niveau de difficulté de l'exercice
     * @param methodeEvalVal la méthode d'évaluation de l'exercice
     */
    public Exercice(ArrayList<String> textePhrases, String langVal,  String niveauVal, HashMap<String,Integer> methodeEvalVal){
        // Initialisation des champs de la classe
        methodeEval = methodeEvalVal;
        niveau = niveauVal;
        langue = langVal;

        // Création de la liste d'objets phrases
        ParseurExoTrou parseur = new ParseurExoTrou();
        for (String question : textePhrases){
            QuestionTrou phraseQuestion = parseur.parse(question);
            phrases.add(phraseQuestion);
            questions.add(phraseQuestion.getQuestion());
            reponses.add(phraseQuestion.getReponses());
            randomReponses.addAll(phraseQuestion.getReponses());
            bareme += (phraseQuestion.getReponses()).size();
        }

        // On fixe un seuil pour réussite à au moins la moitié des réponses
        bareme = bareme/2;


        Collections.shuffle(randomReponses);
    }


    /**
     * Récupère la liste des questions de l'exercice.
     *
     * @return La liste des questions de l'exercice.
     */
    public ArrayList<ArrayList<String>> getQuestions() {
        return questions;
    }

    public ArrayList<QuestionTrou> getPhrases(){
        return this.phrases;
    }

    public HashMap<String, Integer> getMethodeEval() {
        return methodeEval;
    }

    /**
     * Méthode permettant de mélanger l'ordre des réponses de l'exercice.
     *
     * @return une liste de réponses mélangées
     */
    public ArrayList<String> randomReponses() {
        return randomReponses;
    }
  

    /**
     * Renvoie la langue de l'exercice.
     * 
     * @return la langue de l'exercice
     */
    public String getLangue() {
        return this.langue;
    }
    /**
     * Renvoie le niveau de l'exercice.
     * 
     * @return le niveau de l'exercice
     */
    public String getNiveau() {
        return this.niveau;
    }

    public int getIntNiveau() {
        return Evaluation.getNiveauVal(this.niveau);
    }

    public ArrayList<ArrayList<String>> getReponses(){
        return this.reponses;}


    /**
     * Récupère les réponses saisies par l'apprenant pour chaque phrase de l'exercice.
     * @return Un ArrayList de ArrayList de String contenant les réponses saisies par l'apprenant pour chaque phrase de l'exercice.
     */
    public ArrayList<ArrayList<String>> getReponsesEleve() {
        return reponsesEleve;
    }

    public void addReponseEleve(ArrayList<String> reponse){
        reponsesEleve.add(reponse);
    }


    /**
     * Méthode qui corrige l'exercice en cours en retournant une liste de corrections
     * pour chaque question de l'exercice.
     * 
     * @return la liste de corrections de l'exercice
     */
    public CorrectionExo corrige() {
        ArrayList<CorrectionQuestionTrou> phrasesCorrigees = new ArrayList<>(); // liste des corrections

        // boucle sur les questions de l'exercice
        int i = 0;
        for (QuestionTrou phrase : this.phrases) {
            CorrectionQuestionTrou phraseCorrigee = new CorrectionQuestionTrou(phrase, this.reponsesEleve.get(i), this.methodeEval); // création de la correction de la question
            phrasesCorrigees.add(phraseCorrigee); // ajout de la correction à la liste
            i++;
        }

        CorrectionExo correction = new CorrectionExo(phrasesCorrigees, this);
        return correction;
    }


    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'exercice.
     *
     * @return une chaîne de caractères contenant les informations suivantes :
     *         - la langue de l'exercice
     *         - le niveau de l'exercice
     *         - le barème de l'exercice
     *         - la méthode d'évaluation de l'exercice
     *         - la liste des questions de l'exercice
     *         - la liste des réponses de l'exercice
     */
    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Langue de l'exercice : ").append(langue).append("\n");
    sb.append("Niveau de l'exercice : ").append(niveau).append("\n");
    sb.append("Bareme de l'exercice : ").append(bareme).append("\n");
    sb.append("Methode d'évaluation de l'exercice : ").append(methodeEval).append("\n");
    sb.append("Questions de l'exercice : \n");
    for (ArrayList<String> question : questions) {
        sb.append("\t").append(question).append("\n");
    }
    sb.append("Réponses de l'exercice : ").append(reponses).append("\n");
    return sb.toString();
}


    /* Méthode d'affichage de l'exo (avec plusieurs questions) à l'élève
    public void montrerExo (){
        ArrayList<String> randomreponses = new ArrayList<>(reponses);//copie de la liste avec les réponses
        Collections.shuffle(randomreponses); // liste avec les réponses dans un ordre aléatoire
        System.out.println("Voici les réponses à utiliser :");
        // On affiche les réponses à utiliser de manière random
        int i = 0;
        int longueur = randomreponses.size();
        for (String randomreponse : randomreponses){
            if(i == 0){
                System.out.print(randomreponse);
            }
            else if(i==longueur-1){
                System.out.println(", "+randomreponse+".");}
            else{
                    System.out.print(", "+randomreponse);}
                i++;
        }
        System.out.println("");
        //On affiche les questions à répondre
        i = 1;
        for (QuestionTrou phrase : phrases){
            System.out.println(i+". "+phrase.stringEleve());
            i++; 
        }
        
    }
    */ 
}
/* Proposer aux professeurs de donner plusieurs propositions pour chaque réponses en séparant avec | parce que c'est mieux pour nous

// dicoExo {
//     IndiceQuestion: {
//         texte: contenu de la question, reponses: dicoReponse{
//             IndiceReponse: Bonne réponse} 
//         }
//     }


// methode*/