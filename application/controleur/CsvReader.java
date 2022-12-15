package application.controleur;

import java.io. * ;
import java.util.Scanner;

//CSVReader.liseurCsv("path")

public abstract class CsvReader {
  public static void liseurCsv (String argument) {
    Scanner sc = new Scanner(new File(argument),"utf-8");
    //parsing a CSV file into the constructor of Scanner class 
    sc.useDelimiter(",");
    //setting comma as delimiter pattern
    while (sc.hasNext()) {
      //récupérer variable qui contient le login écrit
      //if le login entré == sc alors stop et ouvre le frame
      System.out.print(sc.next()+"\t");
    }
    sc.close();
    //closes the scanner  
  }
}