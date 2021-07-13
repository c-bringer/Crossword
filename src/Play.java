import model.Grid;

public class Play
{
    public static void main(String[] args)
    {
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

        //grid.deleteWord();
        grid.displayGrid();
        System.out.print("\n");
    }
}
