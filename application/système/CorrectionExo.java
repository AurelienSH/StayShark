package application.système;
import java.util.ArrayList;
public class CorrectionExo {
    ArrayList<Correction> phrasesCorrigees;
    float note=0;
    float modifPointsApprenant;

    CorrectionExo(Exercice exo){
        phrasesCorrigees = exo.corrige();
        for (Correction phraseCorrigee : phrasesCorrigees){
            note+=phraseCorrigee.getNote();
        }
        modifPointsApprenant = this.note*exo.bareme*exo.niveau;
    }
}
