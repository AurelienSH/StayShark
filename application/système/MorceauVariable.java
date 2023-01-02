package application.système;

/**
 * Classe représentant un morceau de texte variable.
 */
public class MorceauVariable extends Morceau {
    /**
     * Crée un nouveau morceau de texte variable à partir d'une chaîne de caractères.
     * 
     * @param txt chaîne de caractères représentant le texte du morceau
     */
    public MorceauVariable(String txt) {
        super(txt);
    }

    /**
     * Retourne la chaîne de caractères "..." comme question.
     * 
     * @return la chaîne de caractères "..."
     */
    @Override
    public String question() {
        return "...";
    }

    /**
     * Retourne une chaîne de caractères représentant le morceau variable sous la forme "MorceauVariable[texte=<texte>]"
     * 
     * @return une chaîne de caractères représentant le morceau variable
     */
    @Override
    public String toString() {
        return String.format("MorceauVariable[texte=%s]", texte);
    }

}
