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
    //setting comma as delimiter pattern
    while (sc.hasNext()) {
      //récupérer variable qui contient le login écrit
      //if le login entré == sc alors stop et ouvre le frame
      Map<Integer, String> larousse = new HashMap<Integer,String>();
      Pattern p_fin = Pattern.compile(".+[\n\r]+");
      String chiant = sc.next();
      Matcher input = p_fin.matcher(chiant);
      String temporaire = "";
      if (input.find()){
        System.out.print("bskgfksfdjlg");
        // temporaire="";
      }else{
        System.out.print(chiant);
        // temporaire += sc.next();
    }
      // 
    }
    sc.close();
    //closes the scanner  
  }
}