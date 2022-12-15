package application.controleur;

import java.io. * ;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

//CSVReader.liseurCsv("path")

public abstract class CsvReader {
  public static void liseurCsv (String argument) throws FileNotFoundException{
    Scanner sc = new Scanner(new File (argument),"utf-8");
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
    System.out.println(larousse);
    sc.close();
    //closes the scanner  
  }
}