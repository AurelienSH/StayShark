package application.système;

import java.util.ArrayList;

/**
 * Classe représentant la correction d'un exercice.
 * Cette classe contient une liste de corrections de phrases et permet de calculer la note finale de l'exercice.
 */
public class CorrectionExo {

    private ArrayList<Correction> phrasesCorrigees; // liste de corrections de phrases
    private float note=0; // note finale de l'exercice
    private float modifPointsApprenant; // modification des points de l'apprenant en fonction de la note de l'exercice et du bareme et du niveau de l'exercice

    /**
     * Constructeur de la correction de l'exercice.
     * 
     * @param exo l'exercice à corriger
     */
    public CorrectionExo(Exercice exo){
        phrasesCorrigees = exo.corrige(); // récupération de la liste de corrections de phrases
        for (Correction phraseCorrigee : phrasesCorrigees){
            note+=phraseCorrigee.getNote(); // calcul de la note finale de l'exercice
        }
        modifPointsApprenant = this.note*exo.bareme*exo.niveau; // calcul de la modification des points de l'apprenant
    }

    /**
     * Retourne la modification des points de l'apprenant en fonction de la note de l'exercice et du bareme et du niveau de l'exercice.
     * 
     * @return la modification des points de l'apprenant
     */
    public float getModifPointsApprenant() {
        return modifPointsApprenant;
    }

    /**
     * Retourne une représentation textuelle de la correction de l'exercice.
     * 
     * @return une représentation textuelle de la correction de l'exercice
     */
    @Override
    public String toString() {
        return String.format("CorrectionExo[note=%.2f, modifPointsApprenant=%.2f]", note, modifPointsApprenant);
    }
}
