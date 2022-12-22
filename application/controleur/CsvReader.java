package application.controleur;

import java.io. * ;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

/**
 * CsvReader est une classe qui permet de convertir un csv en une HashMap.
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
    Scanner sc = new Scanner(new File (fichierCsv),"utf-8");
    //parsing a CSV file into the constructor of Scanner class 
    sc.useDelimiter(",");
    Map<String, String> larousse = new HashMap<String,String>();
    Pattern p_fin = Pattern.compile(".+[\n\r]+");
    String temporaire = "";
    String login = "";
    //setting comma as delimiter pattern
    while (sc.hasNext()) {
      //récupérer variable qui contient le login écrit
      //if le login entré == sc alors stop et ouvre le frame
      String chiant = sc.next();
      Matcher input = p_fin.matcher(chiant);
      if (chiant.equals("Login") || chiant.equals("Nom") || chiant.equals("Prenom") || chiant.contains("Langues")){
        continue;
      }else if (input.find()){
        temporaire += chiant;
        larousse.put(login,temporaire);
        temporaire="";
      }else if (Character.isDigit(chiant.charAt(0))){
      login = "";
      login = chiant;
    } else if (!chiant.equals(input.find())){
        temporaire += chiant + " ";
    }
    }
    
    sc.close();
    return larousse;
    //closes the scanner  
  }
}