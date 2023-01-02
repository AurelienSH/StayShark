package application.système;

import java.util.ArrayList;

/**
 * La classe ParseurExoTrou implémente l'interface Parseur et permet de parser des chaînes de caractères en instances de la classe QuestionTrou.
 */
public class ParseurExoTrou implements Parseur {

    /**
     * Constructeur par défaut de la classe ParseurExoTrou.
     */
    public ParseurExoTrou() {
        // Constructeur vide par défaut
    }

    /**
     * Transforme une chaîne de caractères en une instance de la classe QuestionTrou.
     *
     * @param input la chaîne de caractères à parser
     * @return une instance de la classe QuestionTrou représentant la chaîne de caractères parsée
     */
    @Override
    public QuestionTrou parse(String input){

        QuestionTrou nouvelleQuestionTrou;

        boolean pair=input.startsWith("#", 0);

        int morceauVariableFinder;

        if (pair){
            morceauVariableFinder = 2; 
        }
        else{
            morceauVariableFinder = 1;
        }

        // Initialisation de tokens
        String[] tokensString = input.split("#");

        int l = tokensString.length;

        ArrayList<Morceau> tokens = new ArrayList<>();


        Morceau tmp_tok;


        // Séparation des cas pairs ou impairs pour la récupération des bonnes réponses
        // En vue de les réutiliser dans la tokens
        for (int i = 0; i < l; i+=1){
            if ((i+morceauVariableFinder)%2 == 0){
                tmp_tok = new MorceauVariable(tokensString[i]);
                tokens.add(tmp_tok);
            }
            else{
                tmp_tok = new Morceau(tokensString[i]);
                tokens.add(tmp_tok);
            }
        }

        nouvelleQuestionTrou = new QuestionTrou(tokens);

        return nouvelleQuestionTrou;
        
    }

    @Override
    public String toString() {
        return "ParseurExoTrou{}";
    }

}