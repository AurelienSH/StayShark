package application.système;

/**
 * Classe abstraite représentant un acteur humain dans le système.
 * Cette classe ne peut pas être instanciée directement,
 * une classe qui étend cette classe doit être déclarée et
 * implémenter toutes les méthodes abstraites déclarées.
 */
public abstract class ActeurHumain {

    /** Login de l'acteur */
    protected final String login;
    /** Nom de l'acteur */
    protected final String nom;
    /** Prénom de l'acteur */
    protected final String prenom;

    /**
     * Constructeur de l'acteur humain.
     * 
     * @param login login de l'acteur
     * @param nom nom de l'acteur
     * @param prenom prénom de l'acteur
     */
    public ActeurHumain(String login, String nom, String prenom) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
    }
}
