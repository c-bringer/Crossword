import model.Grid;
import util.File;

public class Play
{
    public static void main(String[] args)
    {
        /*File file = new File();
        String word = file.getRandomWordWithLetter("....", 4);
        System.out.println(word);
        System.exit(1);
         */

        Grid grid = new Grid();
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
