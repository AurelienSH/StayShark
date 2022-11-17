package application;

public class Apprenant implements ActeursHumains {
    String login;
    String nom;
    String prenom;
    int niveau;
    int score;
    String langue;

    public int getNiveau(){
        return niveau;
    }

    public void monterNiveau(){
        niveau+=1;
    }

    public int getScore(){
        return score;
    }
}
