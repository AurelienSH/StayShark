package application.système;
import java.util.HashMap;
import java.util.Map;
public abstract class Evaluation {
    private static Map<String,Integer> dictNiveaux = new HashMap<String,Integer>(){{
        put("A1",1);
        put("A2",2);
        put("B1",3);
        put("B2",4);
        put("C1",5);
    }};

    public static Integer getNiveauVal(String niv){
        return dictNiveaux.get(niv);
    }

    public static void evalue(Apprenant apprenant, Exercice exo){
        CorrectionExo correction = new CorrectionExo(exo);
        String langue = exo.getLangue();
        Integer nivApprenant = apprenant.getIntNiveau(langue);
        Integer nivExercice = exo.getIntNiveau();
        int dif_niveau = Math.abs(nivApprenant-nivExercice);


        apprenant.addScore(langue, correction.getModifPointsApprenant());
    }

    
}
