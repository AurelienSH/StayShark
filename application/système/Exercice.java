package application.système;
import java.util.ArrayList;
import java.util.HashMap;
// import java.util.Scanner;
import java.util.Collections;

public class Exercice {

    // Langue de l'exercice
    String langue;

    // Chaine de caractère à montrer à l'apprenant
    String pourApprenant;

    // Liste des phrases de l'exo
    ArrayList<Phrase> questions = new ArrayList<Phrase>();

    ArrayList<String> reponses = new ArrayList<String>();

    // Points de l'exo
    int bareme;

    // Constructeur :
    // Prend une liste de chaines de caractères, une langue, un niveau et un bareme
    Exercice(ArrayList<String> texte, String langVal, int baremeVal){
        langue=langVal;
        bareme=baremeVal;

        // Création de la liste d'objets phrases
        for (String question : texte){
            Phrase phraseQuestion = new Phrase(question);
            reponses.addAll(phraseQuestion.getReponses());
            questions.add(phraseQuestion);
        }
        
    }

    

// Méthode d'affichage de l'exo (avec plusieurs questions) à l'élève
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
        for (Phrase phrase : questions){
            System.out.println(i+". "+phrase.stringEleve());
            i++; 
        }
    }



    //méthode de correction des exos (se déroule en parallèle de la demande de réponse de l'apprenant)
    public ArrayList<ArrayList<String>> getReponsesEleve(){
        ArrayList<ArrayList<String>> reponsesEleve = new ArrayList<ArrayList<String>>();
        // boucle pour chaque phrase de l'exo
        for (Phrase question : this.questions){
            ArrayList<String> reponses = question.getReponseEleve();
            reponsesEleve.add(reponses);
        }
        return reponsesEleve;
    }

    public ArrayList<CorrectionPhrase> corrige(HashMap<String,Float> methodeEval){
        ArrayList<CorrectionPhrase> phrasesCorrigees = new ArrayList<CorrectionPhrase>();
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
        for (Phrase p : questions){

            System.out.println(i+". "+p.stringCorrecte());
            i++;
        }
    }

}
/* Proposer aux professeurs de donner plusieurs propositions pour chaque réponses en séparant avec | parce que c'est mieux pour nous

// dicoExo {
//     IndiceQuestion: {
//         texte: contenu de la question, reponses: dicoReponse{
//             IndiceReponse: Bonne réponse} 
//         }
//     }


// methode*/