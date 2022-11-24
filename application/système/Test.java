package application.système;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> texte = new ArrayList<String>();
        texte.add("Je suis un #aveugle# au festival de #Cannes#.");
        texte.add("Eh oh du #bateau# est-ce que tu aimes les #gateaux#!");
        texte.add("Un deux #trois# quatre #cinq#.");
        texte.add("Les #patates# sont une #merveille# et pas du tout une #calamité#.");
        Exercice pouet = new Exercice(texte, "javanais", 3, 20);
        pouet.montrerExo();
        float truc=pouet.corrigerExo();
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        System.out.println(numberFormat.format(truc)+ "/20");
        pouet.afficherCorrection();

    }
}
