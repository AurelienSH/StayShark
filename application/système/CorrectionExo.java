package application.système;
import java.util.ArrayList;
public class CorrectionExo {
    private ArrayList<Correction> phrasesCorrigees;
    private float note=0;
    private float modifPointsApprenant;

    CorrectionExo(Exercice exo){
        phrasesCorrigees = exo.corrige();
        for (Correction phraseCorrigee : phrasesCorrigees){
            note+=phraseCorrigee.getNote();
        }
        modifPointsApprenant = this.note*exo.bareme*exo.niveau;
    }

    public float getModifPointsApprenant() {
        return modifPointsApprenant;
    }
}
