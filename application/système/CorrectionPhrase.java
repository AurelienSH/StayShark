package application.système;
import java.util.ArrayList;
import java.util.HashMap;
public class CorrectionPhrase extends Correction {
    private ArrayList<String> reponses;
    private float note=0;

    CorrectionPhrase(ArrayList<String> reponsesEleve, HashMap<String,Float> methodeEval){
        this.reponses=reponsesEleve;
        for (String rep : reponsesEleve){
            note+=methodeEval.get(rep);
        }
    }

    public float getNote(){
        return this.note;
    }

    public ArrayList<String> getReponses(){
        return reponses;
    }
    
}