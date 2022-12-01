package application.système;

public interface Parseur {
    static Phrase parse(String input){
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
}
