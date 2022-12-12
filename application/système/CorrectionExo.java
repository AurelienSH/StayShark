package application.système;
import java.util.ArrayList;
import java.util.HashMap;
public class CorrectionExo {
    ArrayList<CorrectionPhrase> phrasesCorrigees = new ArrayList<CorrectionPhrase>();

    CorrectionExo(Exercice exo, HashMap<String,Float> methodeEval){
        ArrayList<ArrayList<String>> reponsesEleve = exo.getReponsesEleve();
        for (ArrayList<String> reponse : reponsesEleve){
            CorrectionPhrase phraseCorrigee = new CorrectionPhrase(reponse, methodeEval);
            phrasesCorrigees.add(phraseCorrigee);
        }
    }
}
