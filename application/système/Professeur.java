package application.système;

/**
 * Classe représentant un professeur dans le système.
 * Cette classe étend la classe abstraite {@link ActeurHumain}
 * et implémente les méthodes abstraites déclarées dans cette classe.
 */
public class Professeur extends ActeurHumain {

    /**
     * Constructeur du professeur.
     * 
     * @param login login du professeur
     * @param nom nom du professeur
     * @param prenom prénom du professeur
     */
    public Professeur(String login, String nom, String prenom) {
        super(login, nom, prenom);
    }

    /**
     * Retourne le niveau de l'apprenant spécifié dans la langue spécifiée.
     * 
     * @param apprenant l'apprenant pour lequel on veut connaître le niveau
     * @param langue la langue à laquelle on veut connaître le niveau de l'apprenant
     * @return le niveau de l'apprenant dans la langue spécifiée
     */
    public String voirNiveau(Apprenant apprenant, String langue) {
        return apprenant.getNiveau(langue);
    }

    /**
     * Retourne une chaîne de caractères représentant le professeur sous la forme "Professeur[login=<login>, nom=<nom>, prenom=<prenom>]"
     * 
     * @return une chaîne de caractères représentant le professeur
     */
    @Override
    public String toString() {
        return String.format("Professeur[login=%s, nom=%s, prenom=%s]", login, nom, prenom);
    }

}
