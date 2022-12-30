package application.système;
import java.util.ArrayList;
import java.util.Arrays;
public class CorrectionPhrase extends Correction {
    private ArrayList<String> reponses;
    private float note=0;

    CorrectionPhrase(ArrayList<String> reponsesEleve, Exercice exo){
        this.reponses=reponsesEleve;
        for (String rep : reponsesEleve){
            note+=exo.methodeEval.get(rep);
        }
    }

    public float getNote(){
        return this.note;
    }

    public ArrayList<String> getReponses(){
        return reponses;
    }

    @Override
    public String toString() {
        return String.format("CorrectionPhrase[note=%.2f, reponses=%s]", note, Arrays.toString(reponses.toArray()));
}

    
}