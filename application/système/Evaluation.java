package application.système;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe permettant d'évaluer les performances d'un apprenant sur un exercice donné.
 */
public abstract class Evaluation {

        /**
         * Map associant à chaque niveau de compétence sa valeur entière correspondante.
         */
        private static Map<String,Integer> dictNiveaux = new HashMap<String,Integer>(){{
            put("A1",1);
            put("A2",2);
            put("B1",3);
            put("B2",4);
            put("C1",5);
        }};

        /**
         * Map associant à chaque résultat de correction de réponse sa valeur de modification de points.
         * La méthode de correction est permissive.
         */
        private static HashMap<String, Integer> methodePermissive= new HashMap<>(){{
            put("NR",0); // "NR" signifie "Not Rated", c'est-à-dire non noté
            put("correct",1);
            put("incorrect",0);
        }};

        /**
         * Map associant à chaque résultat de correction de réponse sa valeur de modification de points.
         * La méthode de correction est intransigeante.
         */
        private static HashMap<String, Integer> methodeIntransigeante = new HashMap<>(){{
            put("NR",0);
            put("correct",1);
            put("incorrect",-1);
        }};

        /**
         * Map associant à chaque résultat de correction de réponse sa valeur de modification de points.
         * La méthode de correction est "aucune erreur".
         */
        private static HashMap<String, Integer> methodeAucuneErreur= new HashMap<>(){{
            put("NR",-1);
            put("correct",1);
            put("incorrect",-1);
        }};

        /**
         * Map associant à chaque choix de méthode de correction sa Map de modification de points correspondante.
         */
        private static Map<String,HashMap<String, Integer>> choixCorrectionDict = new HashMap<>(){{
            put("permissive",methodePermissive);
            put("intransigeante",methodeIntransigeante);
            put("aucune erreur",methodeAucuneErreur);
        }};

        /**
         * Méthode permettant de récupérer la valeur entière correspondant au niveau de compétence passé en paramètre.
         * @param niv le niveau de compétence.
         * @return la valeur entière correspondant au niveau de compétence.
         */
        public static Integer getNiveauVal(String niv){
            return dictNiveaux.get(niv);
        }


        /**
         * Évalue les performances de l'apprenant sur l'exercice passé en paramètre.
         * @param apprenant l'apprenant à évaluer.
         * @param exo l'exercice sur lequel évaluer l'apprenant.
         */
        public static void evalue(Apprenant apprenant, Exercice exo){
            CorrectionExo correction = exo.corrige();
            String langue = exo.getLangue();
            Integer nivApprenant = apprenant.getIntNiveau(langue);
            Integer nivExercice = exo.getIntNiveau();
            int dif_niveau = nivExercice-nivApprenant;
            Integer pointsApprenant = correction.getModifPointsApprenant();
            if (pointsApprenant != -1) {
                apprenant.addScore(langue, pointsApprenant *10^dif_niveau); // ajout d'une valorisation pour la réussite de niveaux plus élevés que le sien
            }
            else{
                apprenant.addScore(langue, pointsApprenant * Math.max(5 * Math.abs(dif_niveau),1)); // On pénalise peu l'échec a un niveau supérieur que le sien, mais beaucoup sinon
            }
        }

        /**
         * Récupère la Map associant à chaque résultat de correction de réponse sa valeur de modification de points correspondante.
         * @param choix le choix de méthode de correction.
         * @return la Map associant à chaque résultat de correction de réponse sa valeur de modification de points correspondante.
         */
        public static HashMap<String, Integer> getChoixCorrectionDict(String choix){
            return choixCorrectionDict.get(choix);
        }

    
}
