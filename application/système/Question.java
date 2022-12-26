package application.système;
import java.util.ArrayList;
import java.util.HashMap;


public interface Question {
    public Correction correction(ArrayList<String> reponseEleve, HashMap<String,Float> methodeEval);
}
