package application.système;

public abstract class ActeurHumain {
/**
 * @param login;
 * @param nom;
 * @param prenom;
*/
    final String login;
    final String nom;
    final String prenom;

    ActeurHumain(String login, String nom, String prenom){
        this.login = login;
        this.nom=nom;
        this.prenom=prenom;

    }
}
