package application.système;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Phrase implements Question {

    private String[] tokens; //tous les tokens splités sur "#"

    private ArrayList<Integer> indices_reponses = new ArrayList<Integer>(); // les indices des mots à remplir dans la liste "tokens"

    private ArrayList<String> reponses = new ArrayList<String>(); // les bonnes réponses dans l'ordre



    // input : la phrase du prof écrite avec les "#"
    Phrase(String input){

        // Un booléen nous permettant de savoir si les mots à trous sont sur les indices pairs ou impair 
        // (vu que les mots à remplir sont 1 token sur deux)
        // Les indices seront pair si le premier mot de la phrase n'est pas un mot à trou, impair sinon
        boolean pair=input.startsWith("#", 0);

        // Initialisation de tokens
        tokens = input.split("#");

        int l = tokens.length;


        // Séparation des cas pairs ou impairs pour la récupération des bonnes réponses
        // En vue de les réutiliser dans la tokens
        if (pair){
            for (int i = 0; i < l; i+=2){
                indices_reponses.add(i);
                reponses.add(tokens[i]);
            }
        }
        else{
            for (int i = 1; i < l; i+=2){
                indices_reponses.add(i);
                reponses.add(tokens[i]);
            }
        }
    }



    public String[] getTokens(){
        return tokens;
    }
    // Méthode retournant la chaîne de caractères que verra l'apprenant
    public String stringEleve(){
        ArrayList<String> stringEleve = new ArrayList<String>();

        // Ajout de tous les tokens dans une nouvelle liste
        for (String truc : tokens){
            stringEleve.add(truc);
        }

        // Remplacement des mots à trous par "..."
        for (int indice : indices_reponses){
            stringEleve.set(indice,"...");
        }

        // Utilisation d'un StringBuffer pour réécrire la chaine de caractère
        StringBuffer joiner = new StringBuffer();

        for (String token : stringEleve){
            joiner.append(token);
            joiner.append(" ");
        }

        // Transformation en chaine de caractère

        String str = joiner.toString();

        return str;
    }

    public String stringCorrecte(){
        ArrayList<String> stringCorrecte = new ArrayList<String>();

        // Ajout de tous les tokens dans une nouvelle liste
        for (String truc : tokens){
            stringCorrecte.add(truc);
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
        return this.reponses;
    }

    // Getter de la phrase sous forme de liste
    public String[] gettokens(){
        return this.tokens;
    }

    // Getter des indices de réponses
    public ArrayList<Integer> getIndicesReponses(){
        return indices_reponses;
    }
    
    // Correction méchante après récupération de la réponse de l'élève
    // On peut imaginer un moyen de modifier les points par mots par ex "Non Reconnu = 0, Faux = -1, Vrai = +1 modulable"
    CorrectionPhrase correction(ArrayList<String> reponseEleve, HashMap<String,Float> methodeEval){

        CorrectionPhrase phraseCorrigée = new CorrectionPhrase(reponseEleve, methodeEval);
        /* Si on voulait faire une réponse détaillée
        A faire pour chaque mot à trou 
        Faut faire une boucle sur les reponses et conserver les erreurs */
        return phraseCorrigée;
    }


    public ArrayList<String> getReponseEleve(){
        ArrayList<String> reponsesEleve= new ArrayList<String>();
        for (String reponse : this.reponses){
            System.out.println("Quel est le mot numéro "+i+"?");

            // Utilisation d'un scanner pour demander les réponses dans le terminal
            Scanner in = new Scanner(System.in);
            String reponseEleve = in.nextLine();
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
                reponsesEleve.add("NR");
            }
            in.close();
        }
        return reponsesEleve;
        }
    
    }
    
//récupérer les réponses de l'élève dans une liste de listes
//vérification (reponses) --> print dedans ou po (voir si correction??)