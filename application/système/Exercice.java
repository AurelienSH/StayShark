package application.système;
import java.util.ArrayList;
import java.util.HashMap;
// import java.util.Scanner;
import java.util.Collections;

public class Exercice {

    // Langue de l'exercice
    final String langue;

    // Liste des questions de l'exo
    ArrayList<ArrayList<String>> questions = new ArrayList<>();

    // Liste des phrases de l'exo
    ArrayList<Phrase> phrases = new ArrayList<>();

    ArrayList<String> reponses = new ArrayList<>();

    // Points de l'exo
    final int bareme;

    // Constructeur :
    // Prend une liste de chaines de caractères, une langue, un niveau et un bareme
    Exercice(ArrayList<String> textePhrases, String langVal, int baremeVal){

        ParseurExoTrou parseur = new ParseurExoTrou();

        langue=langVal;
        bareme=baremeVal;

        // Création de la liste d'objets phrases
        for (String question : textePhrases){
            Phrase phraseQuestion = parseur.parse(question);
            phrases.add(phraseQuestion);
            questions.add(phraseQuestion.getQuestion());
            reponses.addAll(phraseQuestion.getReponses());
        }
        
    }

    public ArrayList<ArrayList<String>> getQuestions(){
        return questions;
    }

    public ArrayList<String> randomReponses(){
        ArrayList<String> randomReponses = new ArrayList<>(reponses);
        Collections.shuffle(randomReponses);
        return randomReponses;
    }

    //méthode de correction des exos (se déroule en parallèle de la demande de réponse de l'apprenant)
    public ArrayList<ArrayList<String>> getReponsesEleve(){
        ArrayList<ArrayList<String>> reponsesEleve = new ArrayList<ArrayList<String>>();
        // boucle pour chaque phrase de l'exo
        for (Phrase question : this.phrases){
            ArrayList<String> reponses = question.getReponseEleve();
            reponsesEleve.add(reponses);
        }
        return reponsesEleve;
    }

    public ArrayList<Correction> corrige(HashMap<String,Float> methodeEval){
        ArrayList<Correction> phrasesCorrigees = new ArrayList<Correction>();
        ArrayList<ArrayList<String>> reponsesEleve = this.getReponsesEleve();
        for (ArrayList<String> reponse : reponsesEleve){
            CorrectionPhrase phraseCorrigee = new CorrectionPhrase(reponse, methodeEval);
            phrasesCorrigees.add(phraseCorrigee);
        }
        return phrasesCorrigees;
    }

    public void afficherCorrection(){
        System.out.println("");
        int i=1;
        System.out.println("Voici la correction :");
        for (Phrase p : phrases){

            System.out.println(i+". "+p.stringCorrecte());
            i++;
        }
    }

    public String toString(){
        return("Exercice en"+langue+"\n");
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
        for (Phrase phrase : phrases){
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