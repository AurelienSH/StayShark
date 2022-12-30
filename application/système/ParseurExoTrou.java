package application.système;
import java.util.ArrayList;

public class ParseurExoTrou implements Parseur {
    ParseurExoTrou(){
        
    }
    public Phrase parse(String input){

        Phrase nouvellePhrase;

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
            if (i+morceauVariableFinder%2 == 0){
                tmp_tok = new MorceauVariable(tokensString[i]);
                tokens.add(tmp_tok);
            }
            else{
                tmp_tok = new Morceau(tokensString[i]);
                tokens.add(tmp_tok);
            }
        }

        nouvellePhrase = new Phrase(tokens);

        return nouvellePhrase;
        
    }
}