package application.controleur;

import java.io. * ;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/*
 * TO DO
 * méthode : modifier csv
 * voir : http://super-csv.github.io/super-csv/examples_partial_reading.html
 * pour une meilleure lecture ptet?
 */

/**
 * Contient deux méthodes traitant les fichiers Csv.
 * Les deux méthodes : <br/>- une qui permet de convertir un csv en une HashMap où la clé est le login de l'utilisateur. <br/>- une qui dit par vrai ou faux si la string prise en paramètre existe dans le fichier indiqué en paramètre.
 * Il prend donc en entrée un csv et retourne une HashMap.
 * @author Elisabeth et Aurélien
 */
public abstract class CsvReader {
  /**
   * Prend en entrée un fichier csv et retourne un dictionnaire.
   * @param fichierCsv chemin du fichier csv
   * @return une HashMap où la clé est le login et la valeur est le nom, prénom et langue de l'utilisateur
   * @throws FileNotFoundException Au cas où le fichier n'existe pas
   */
  public static Map liseurCsv (String fichierCsv) throws FileNotFoundException{
      Map<String,String> dicoUsers = new HashMap<String,String>();
      FileInputStream file = new FileInputStream(fichierCsv);       
      Scanner scanneur = new Scanner(file,"utf-8");
      int lines = 0;
      while(scanneur.hasNextLine()){ 
          String ligne = scanneur.nextLine();
          if(lines==0){
              lines++;
              continue;
          }
          String[] words = ligne.split(",");
          lines++;
          dicoUsers.put(words[0],words[1]+","+words[2]+","+words[3]);
      }
      scanneur.close(); 
      return dicoUsers;
  }

  /**
   * Retourne vrai ou faux selon si la string (le login) existe dans le fichier ou non.
   * @param login String à chercher dans le fichier
   * @param fichier String qui est le chemin du fichier
   * @return vrai (si la string existe dans le fichier) ou faux (si la string n'existe pas dans le fichier)
   * @throws FileNotFoundException Au cas où le fichier n'existe pas
   */
  public static boolean loginExiste(String login, String fichier) throws FileNotFoundException {
    Map<String,String> bloub = CsvReader.liseurCsv(fichier);
    return bloub.containsKey(login);
}

  public static void inscription(String fichier)throws FileNotFoundException, IOException{
    FileWriter fstream = new FileWriter(fichier, true);
    BufferedWriter out = new BufferedWriter(fstream);
		out.write("something");
		out.newLine();
    out.close();
  }

  // public static int compteur(String fichier)throws FileNotFoundException, IOException{
  //   BufferedReader reader = new BufferedReader(new FileReader(fichier));
  //   int lines = 0;
  //   while (reader.readLine() != null) {
  //     lines++;} 
  //   reader.close();
  //   return lines;
  // }

}