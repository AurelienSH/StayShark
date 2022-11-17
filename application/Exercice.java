package application;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Collections;

public class Exercice {

    // Langue de l'exercice
    String langue;

    // Chaine de caractère à montrer à l'apprenant
    String pourApprenant;

    // Liste des phrases de l'exo
    ArrayList<Phrase> questions = new ArrayList<Phrase>();

    ArrayList<String> reponses = new ArrayList<String>();

    // Niveau de l'exo
    int niveau;

    // Points de l'exo
    int bareme;

    // Constructeur :
    // Prend une liste de chaines de caractères, une langue, un niveau et un bareme
    Exercice(ArrayList<String> texte, String langVal, int niveauVal, int baremeVal){
        langue=langVal;
        niveau=niveauVal;
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
    public float corrigerExo(){
        
        // compteur de reponses correctes
        float reponses_correctes = 0;

        // boucle pour chaque phrase de l'exo
        for (Phrase question : this.questions){
            // Affichage de la phrase en question
            System.out.println("");
            System.out.println("Pour la phrase :");
            System.out.println(question.stringEleve());

            // initialisation d'une boucle sur les mots de la phrase
            int i=1;
            for (String reponse : question.getReponses()){
                System.out.println("Quel est le mot numéro "+i+"?");

                // Utilisation d'un scanner pour demander les réponses dans le terminal
                Scanner in = new Scanner(System.in);
                String reponseEleve = in.nextLine();
                boolean correct = (reponse.equals(reponseEleve));
                if (correct){
                    reponses_correctes+=1;
            }
            i++;
        }
    }
        float note = (reponses_correctes/reponses.size()*20);

        
        return note;
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