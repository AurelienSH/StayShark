package application.système;

/**
 * L'interface Parseur définit une méthode de parsing de chaînes de caractères en instances de l'interface Question.
 */
public interface Parseur {
    /**
     * Transforme une chaîne de caractères en une instance de l'interface Question.
     *
     * @param input la chaîne de caractères à parser
     * @return une instance de l'interface Question représentant la chaîne de caractères parsée
     */
    public Question parse(String input);
}
