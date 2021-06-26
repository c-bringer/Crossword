import model.Grid;

public class Play
{
    public static void main(String[] args)
    {
        //File f = new File();
		//f.printWordsStartedBy("a");
		//f.printMotsNbLettreDebut("b", 4);
        Grid grille = new Grid();
        grille.createGrid();
        grille.displayGrid();
        grille.setDefinition();
        grille.displayGrid();
        System.out.print("\n");
        grille.setDefinition();
        grille.displayGrid();
        System.out.print("\n");
    }
}
