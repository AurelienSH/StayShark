package application.système;
import java.util.ArrayList;


public interface Question {
    public Correction correction(ArrayList<String> reponseEleve, Exercice exo);
}
