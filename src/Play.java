import controller.GridController;
import util.FileManager;

public class Play
{
    public static void main(String[] args)
    {
        /*File file = new File();
        String word = file.getRandomWordWithLetter("....", 4);
        System.out.println(word);
        System.exit(1);
         */

        GridController grid = new GridController();
        grid.createGrid();
        grid.displayGrid();
        grid.setDefinition();
        grid.displayGrid();
        System.out.print("\n");
        grid.setDefinition();
        grid.displayGrid();
        System.out.print("\n");
        grid.setDefinition();
        grid.displayGrid();
        System.out.print("\n");
    }
}
