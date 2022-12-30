package application.système;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Phrase implements Question {

    private ArrayList<Morceau> tokens; //tous les tokens splités sur "#"

    private ArrayList<String> reponses = new ArrayList<>(); //liste des réponses dans l'ordre

    // input : la phrase du prof écrite avec les "#"
    Phrase(ArrayList<Morceau> tokens){
        this.tokens=tokens;
        for (Morceau m : tokens){
            if (m instanceof MorceauVariable){
                reponses.add(m.reponse());
            }
        }
    }



    public ArrayList<Morceau> getTokens(){
        return tokens;
    }


    // Méthode retournant la chaîne de caractères que verra l'apprenant
    public ArrayList<String> getQuestion(){
        ArrayList<String> stringEleve = new ArrayList<String>();

        // Ajout de tous les tokens dans une nouvelle liste
        for (Morceau m : tokens){
            stringEleve.add(m.question());
        }

        return stringEleve;
    }

    public String stringCorrecte(){
        ArrayList<String> stringCorrecte = new ArrayList<String>();

        // Ajout de tous les tokens dans une nouvelle liste
        for (Morceau m : tokens){
            stringCorrecte.add(m.reponse());
        }

        // Utilisation d'un StringBuffer pour réécrire la chaine de caractère
        StringBuffer joiner = new StringBuffer();

        for (String token : stringCorrecte){
            joiner.append(token);
            joiner.append(" ");
        }

        // Transformation en chaine de caractère

        String str = joiner.toString();

        return str;
    }

    // Getter des réponses sous forme de liste
    public ArrayList<String> getReponses(){
        ArrayList<String> reponses = new ArrayList<>();
        for (Morceau m : tokens){
            if (m instanceof MorceauVariable){
                reponses.add(m.reponse());
            }
        }
        return reponses;
    }
    
    // Correction méchante après récupération de la réponse de l'élève
    // On peut imaginer un moyen de modifier les points par mots par ex "Non Reconnu = 0, Faux = -1, Vrai = +1 modulable"
    public Correction correction(ArrayList<String> reponseEleve, Exercice exo){

        CorrectionPhrase phraseCorrigée = new CorrectionPhrase(reponseEleve, exo);
        /* Si on voulait faire une réponse détaillée
        A faire pour chaque mot à trou 
        Faut faire une boucle sur les reponses et conserver les erreurs */
        return phraseCorrigée;
    }


    public ArrayList<String> getReponseEleve(){
        ArrayList<String> reponsesEleve= new ArrayList<String>();
        for (String reponse : this.reponses){

            String reponseEleve = machin.getReponseFromInterface();
            /* 
            correct = 1
            incorrect = 2
            NR = 0
            */
            if (reponseEleve != ""){
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
    
    }
    
//récupérer les réponses de l'élève dans une liste de listes
//vérification (reponses) --> print dedans ou po (voir si correction??)