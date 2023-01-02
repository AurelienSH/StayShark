package application.système;

/**
 * Classe représentant un morceau de texte.
 */
public class Morceau {
    // Texte du morceau
    protected String texte;

    /**
     * Crée un nouveau morceau de texte à partir d'une chaîne de caractères.
     * 
     * @param txt chaîne de caractères représentant le texte du morceau
     */
    public Morceau(String txt) {
        texte = txt;
    }

    /**
     * Retourne le texte du morceau.
     * 
     * @return le texte du morceau
     */
    public String reponse() {
        return texte;
    }

    /**
     * Retourne le texte du morceau.
     * 
     * @return le texte du morceau
     */
    public String question() {
        return texte;
    }

    /**
     * Retourne une chaîne de caractères représentant le morceau sous la forme "Morceau[texte=<texte>]"
     * 
     * @return une chaîne de caractères représentant le morceau
     */
    @Override
    public String toString() {
        return String.format("Morceau[texte=%s]", texte);
    }

}
