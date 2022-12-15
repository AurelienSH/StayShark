package application.système;
import java.util.ArrayList;
import java.util.HashMap;
public class CorrectionExo {
    ArrayList<CorrectionPhrase> phrasesCorrigees;

    CorrectionExo(Exercice exo, HashMap<String,Float> methodeEval){
        phrasesCorrigees = exo.corrige(methodeEval);
    }
}
