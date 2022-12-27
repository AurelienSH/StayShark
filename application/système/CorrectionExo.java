package application.système;
import java.util.ArrayList;
import java.util.HashMap;
public class CorrectionExo {
    ArrayList<Correction> phrasesCorrigees;
    float note;

    CorrectionExo(Exercice exo, HashMap<String,Float> methodeEval){
        phrasesCorrigees = exo.corrige(methodeEval);
        for (Correction phraseCorrigee : phrasesCorrigees){
            note+=phraseCorrigee.getNote();
        }
    }
}
