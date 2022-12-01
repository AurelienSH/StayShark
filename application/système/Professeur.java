package application.système;

public class Professeur extends ActeurHumain {
    Professeur(String str1, String str2, String str3){
        super(str1,str2,str3);
    }
    
    Integer voirNiveau(Apprenant bob, String langue){
        int niveau = bob.getNiveau(langue);
        return niveau;
    }
}
