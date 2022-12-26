package application.système;

public class Morceau {
    String texte;
    Morceau(String txt){
        texte=txt;
    }

    public String reponse(){
        return texte;
    }

    public String question(){
        return texte;
    }
}
