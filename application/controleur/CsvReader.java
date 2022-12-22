package application.controleur;

import java.io. * ;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

/**
 * CsvReader est une classe qui contient une méthode qui permet de convertir un csv en une HashMap où la clé est le login de l'utilisateur.
 * Il prend donc en entrée un csv et retourne une HashMap.<br/>
 * Pour l'utiliser : CSVReader.liseurCsv("pathToCsv")
 * @author Elisabeth et Aurélien
 */
public abstract class CsvReader {
  /**
   * Méthode qui prend en entrée un fichier csv et retourne un dictionnaire.
   * @param fichierCsv chemin du fichier csv
   * @return une HashMap où la clé est le login et la valeur est le nom, prénom et langue de l'utilisateur
   * @throws FileNotFoundException
   */
  public static Map liseurCsv (String fichierCsv) throws FileNotFoundException{

    Scanner liseurCsv; // le liseur
    Map<String, String> larousse; //le dico retourné
    Pattern p_fin; // le pattern de fin de ligne
    String temporaire; // stocke la valeur (prénom, nom, langues) de la clé
    String login; // stocke la clé (le login) de l'utilisateur

    liseurCsv = new Scanner(new File (fichierCsv),"utf-8");
    liseurCsv.useDelimiter(","); //on parse le csv
    larousse = new HashMap<String,String>();
    p_fin = Pattern.compile(".+[\n\r]+");
    temporaire = "";
    login = "";

    //boucle qui s'arrête quand le token n'a pas de token après lui; 
    //à chaque fin de ligne : ajoute dans le dictionnaire la clé (login) et sa valeur (prénom, nom, langues)
    while (liseurCsv.hasNext()) {
      String tokenLu = liseurCsv.next();
      Matcher input = p_fin.matcher(tokenLu);
      if (tokenLu.equals("Login") || tokenLu.equals("Nom") || tokenLu.equals("Prenom") || tokenLu.contains("Langues")){
        continue;
      }else if (input.find()){
        temporaire += tokenLu;
        larousse.put(login,temporaire);
        temporaire="";
      }else if (Character.isDigit(tokenLu.charAt(0))){
      login = "";
      login = tokenLu;
    } else if (!tokenLu.equals(input.find())){
        temporaire += tokenLu + " ";
    }
    }
    
    liseurCsv.close(); // on ferme le scanner
    return larousse; // on retourne le dico
  }

  public static boolean loginExiste(String login, String fichier) throws FileNotFoundException {
    Map<String,String> bloub = CsvReader.liseurCsv(fichier);
    return bloub.containsKey(login);
}

}