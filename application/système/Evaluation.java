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

    private static HashMap<String, Integer> methodePermissive= new HashMap<>(){{
        put("NR",0);
        put("correct",1);
        put("incorrect",0);
    }};

    private static HashMap<String, Integer> methodeIntransigeante = new HashMap<>(){{
        put("NR",0);
        put("correct",1);
        put("incorrect",-1);
    }};

    private static HashMap<String, Integer> methodeAucuneErreur= new HashMap<>(){{
        put("NR",-1);
        put("correct",1);
        put("incorrect",-1);
    }};

    private static Map<String,HashMap<String, Integer>> choixCorrectionDict = new HashMap<>(){{
        put("permissive",methodePermissive);
        put("intransigeante",methodeIntransigeante);
        put("aucune erreur",methodeAucuneErreur);
    }};

    public static Integer getNiveauVal(String niv){
        return dictNiveaux.get(niv);
    }

    public static void evalue(Apprenant apprenant, Exercice exo){
        CorrectionExo correction = exo.corrige();
        String langue = exo.getLangue();
        Integer nivApprenant = apprenant.getIntNiveau(langue);
        Integer nivExercice = exo.getIntNiveau();
        int dif_niveau = Math.abs(nivApprenant-nivExercice);


        apprenant.addScore(langue, correction.getModifPointsApprenant());
    }

    public static HashMap<String, Integer> getChoixCorrectionDict(String choix){
        return choixCorrectionDict.get(choix);
    }

    
}
