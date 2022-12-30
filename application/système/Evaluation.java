package application.système;

public abstract class Evaluation {
    public static void evalue(Apprenant apprenant, Exercice exo){
        CorrectionExo correction = new CorrectionExo(exo);
        apprenant.addScore(exo.getLangue(), correction.getModifPointsApprenant());
    }
}
