package application.interfacegraphique;

public class PageProf extend Page {
    public void lisCsvProf(){
        CSVReader.liseurCsv("../data/médias/dataprof.csv")
    }
}