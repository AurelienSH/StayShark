package data;

import java.io. * ;
import java.util.Scanner;

//CSVReader.liseurCsv("path")

public abstract class Test {
  public static void main (String[] args) {
    BufferedReader br = new BufferedReader(new FileReader(csvFile));
while ((line = br.readLine()) != null) {
    // use comma as separator
    String[] cols = line.split(cvsSplitBy);
    System.out.println("Coulmn 4= " + cols[4] + " , Column 5=" + cols[5]);
}
  }
}d