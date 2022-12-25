package application.controleur;

import application.interfacegraphique.PageHome;

//javac -encoding utf8 ./application/controleur/Executeur.java --> pour que ça affiche bien les caractères
// java application.controleur.Executeur

/**
 * Executeur de l'application StayShark --> sera bientôt obsolète si on le fait sous forme de .exe ehe
 * @deprecated bientôt donc pas vraiment le cas mais en avance kwe
 */
public class Executeur {
    public static void main(String[] args) 
    {
        PageHome home = new PageHome();
      }
}
