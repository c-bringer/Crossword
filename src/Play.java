import model.Grille;
import util.File;

public class Play
{
    public static void main(String[] args)
    {
        //File f = new File();
		//f.printWordsStartedBy("a");
		//f.printMotsNbLettreDebut("b", 4);
        Grille grille = new Grille();
        grille.creerGrille();
        grille.afficherGrille();
        grille.placerDefinition();
        grille.afficherGrille();
        System.out.print("\n");
        grille.placerDefinition();
        grille.afficherGrille();
        System.out.print("\n");
    }
}
