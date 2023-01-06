package application.controleur;

import java.io. * ;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;

/**
 * Contient des méthodes qui traitent les fichiers de type Csv.
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

  /**
   * Permet d'écrire dans un nouveau csv ou à la fin d'un csv ce que l'on souhaite.
   * @param fichier le chemin relatif ou absolu du fichier dans lequel on veut écrire
   * @param truc ce que l'on souhaite écrire
   * @throws FileNotFoundException au cas où il y a un soucis avec le fichier
   * @throws IOException au cas où il y a un soucis avec l'encodage par exemple
   */
  public static void ecriture(String fichier, String truc)throws FileNotFoundException, IOException{
    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichier, true),StandardCharsets.UTF_8));
		out.write(truc);
		out.newLine();
    out.close();
  }

  /**
   * Retourne vrai si le fichier en entrée existe ou faux s'il n'existe pas.
   * @param fichier le chemin relatif ou absolu du fichier
   * @return existe (vrai) ou n'existe pas (faux)
   */
  public static boolean fileExiste (String fichier){
    File tmpDir = new File(fichier);
    return tmpDir.exists();
  }

  /**
   * Retourne une arraylist qui contient toutes les phrases à trou de l'exercice voulu.
   * @param fichierCsv le chemin du csv où l'on veut extraire ses phrases
   * @return arraylist où chaque élément est une ligne (donc phrase) du csv (l'exercice)
   * @throws FileNotFoundException au cas où le csv n'existe pas
   */
  public static ArrayList<String> liseurExo (String fichierCsv) throws FileNotFoundException{
    ArrayList<String> phrasesExo = new ArrayList<String>();
    FileInputStream file = new FileInputStream(fichierCsv);       
    Scanner scanneur = new Scanner(file,"utf-8");
    int lines = 0;
    while(scanneur.hasNextLine()){ 
        String ligne = scanneur.nextLine();
        if(lines==0){
            lines++;
            continue;
        }
        lines++;
        phrasesExo.add(ligne);
    }
    scanneur.close(); 
    return phrasesExo;
  }
  
  /**
   * Permet de récupérer la méthode de notation de l'exercice.
   * @param path le path du fichier qui contient l'exercice et la méthode de notation
   * @return la méthode de notation (permissive, intransigeante, aucune erreur)
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static String getteurDuretéNotation (String path) throws FileNotFoundException,IOException{
    String choixCorrection = "";
    FileInputStream file = new FileInputStream(path);       
    Scanner scanneur = new Scanner(file,"utf-8");
    int lines = 0;
    while(scanneur.hasNextLine()){ 
        String ligne = scanneur.nextLine();
        if(lines==0){
            choixCorrection = ligne;
        }else{break;}
        lines++;
    }
    scanneur.close(); 
    return choixCorrection;
  }

  /**
   * Permet de modifier le fichier contenant toutes les données relatives aux élèves.
   * On pourrait utiliser cette méthode aussi pour supprimer un élève ou un professeur des données si ce dernier décide de quitter l'application définitevement. Etant optionnel nous n'avons pas implémanté cette suppression mais elle est possible.
   * @param fichier le chemin du fichier à modifier (ici les données relatives aux élèves)
   * @param truc ce que l'on veut changer dans le csv (par exemple mettre à jour les points de l'élève après avoir fini un exercice)
   * @throws FileNotFoundException au cas où le fichier à modifier n'existe aps
   * @throws IOException au cas où il y ait des soucis d'encodages entre autre
   */
  public static void modificationCsvEleve(String fichier, String truc)throws FileNotFoundException, IOException{

    String csv = "";

    String[] trucsplit = truc.split(",");

    String aChanger = "";

    FileInputStream file = new FileInputStream(fichier);       
    Scanner scanneur = new Scanner(file,"utf-8");
    while(scanneur.hasNextLine()){ 
        String ligne = scanneur.nextLine();
        csv+=ligne+"\n";
        if(ligne.contains(trucsplit[0])){
          aChanger=ligne;
        }
    }
    scanneur.close(); 

    csv = csv.replaceAll(aChanger, truc);

    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fichier, false),StandardCharsets.UTF_8));
		out.write(csv);
    out.close();
  }
  
}