package model;

import model.Case;
import model.Definition;
import util.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Grid
{
    private int height;
    private int width;
    private final Scanner sc = new Scanner(System.in);
    Case[][] grid;
    List<Definition> definitions = new ArrayList<>();

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * Cree la grille sur n x n
     */
    public void createGrid()
    {
        do {
            System.out.print("Saisir le nombre de lignes dans la matrice: ");

            while(!sc.hasNextInt()) {
                System.out.println("Veuillez faire un choix entre 1 et 10.");
                sc.next();
            }

            height = sc.nextInt();
        } while(height < 1 || height > 10);

        do {
            System.out.print("Saisir le nombre de colonne dans la matrice: ");

            while(!sc.hasNextInt()) {
                System.out.println("Veuillez faire un choix entre 1 et 10.");
                sc.next();
            }

            width = sc.nextInt();
        } while(width < 1 || width > 10);

        this.setHeight(height);
        this.setWidth(width);

        grid = new Case[this.getWidth()][this.getHeight()];

        for(int y = 0; y < this.getHeight(); y++) {
            for(int x = 0; x < this.getWidth(); x++) {
                grid[x][y] = new Case();
                grid[x][y].setLabel(" ");
            }
        }
    }


    /**
     * Affiche la grille
     */
    public void displayGrid()
    {
        for(int y = 0; y < this.getHeight(); y++) {
            for(int x = 0; x < this.getWidth(); x++) {
                System.out.printf(" %s |", grid[x][y].getLabel());
            }
            System.out.print("\n");
        }
    }


    /**
     * Place une definition
     */
    public void setDefinition()
    {
        int x, y;

        do {
            System.out.print("Position x de la definition: ");

            while(!sc.hasNextInt()) {
                System.out.println("Veuillez saisir un entier.");
                sc.next();
            }

            x = sc.nextInt() - 1;

            System.out.print("Position Y de la definition: ");

            while(!sc.hasNextInt()) {
                System.out.println("Veuillez saisir un entier.");
                sc.next();
            }

            y = sc.nextInt() - 1;

            if(x == grid[0].length - 1 && y == grid.length - 1) {
                System.out.println("Choisir une autre position");
            }
        } while(x == grid[0].length - 1 && y == grid.length - 1);

        grid[x][y].setLabel("D");
        displayGrid();

        choseDirection(x, y);
    }


    /**
     * Choisi une direction pour la definition
     * @param definitionX Integer definitionX
     * @param definitionY Integer definitionY
     */
    private void choseDirection(int definitionX, int definitionY)
    {
        int direction;

        do {
            System.out.println("Dans quel direction voulez-vous placer un mot :");
            System.out.println("[0] Horizontal direct");
            System.out.println("[1] Horizontal indirect");
            System.out.println("[2] Vertical direct");
            System.out.println("[3] Vertical indirect");

            direction = sc.nextInt();
        }while(!countEmptyCases(direction, definitionX, definitionY));

        definitions.add(new Definition(definitionX, definitionY, direction));
    }


    /**
     * Compte le nombre de cases vide sur le chemin d'une definition
     * @param direction Integer direction
     * @param definitionX Integer definitionX
     * @param definitionY Integer definitionY
     * @return boolean result
     */
    private boolean countEmptyCases(int direction, int definitionX, int definitionY)
    {
        int i = definitionX, j = definitionY, totalEmptyCases = 0;
        boolean result = false;
        String casesOnTheWay = "";

        switch (direction) {
            case 0 -> {
                i++;
                if (i == grid[0].length || checkIfFoundDefinition(i, j)) {
                    System.out.println("Il n'y a pas de cases libre en horizontal directe");
                    break;
                }
                while (i < grid[0].length && !checkIfFoundDefinition(i, j)) {
                    if(grid[i][j].getLabel().equals(" ")) {
                        casesOnTheWay += ".";
                    } else {
                        casesOnTheWay += grid[i][j].getLabel();
                    }
                    totalEmptyCases++;
                    i++;
                }
                System.out.println("En horizontal directe il y a " + totalEmptyCases + "\n");
                result = true;
                addWord(direction, definitionX, definitionY, totalEmptyCases, casesOnTheWay);
            }
            case 1 -> {
                j++;
                if (j == grid.length || checkIfFoundDefinition(i, j)) {
                    System.out.println("Il n'y a pas de cases libre en horizontal indirecte");
                    break;
                }
                while (i < grid[0].length && !checkIfFoundDefinition(i, j)) {
                    if(grid[i][j].getLabel().equals(" ")) {
                        casesOnTheWay += ".";
                    } else {
                        casesOnTheWay += grid[i][j].getLabel();
                    }
                    totalEmptyCases++;
                    i++;
                }
                System.out.println("En horizontal indirecte il y a " + totalEmptyCases + "\n");
                result = true;
                addWord(direction, definitionX, definitionY, totalEmptyCases, casesOnTheWay);
            }
            case 2 -> {
                j++;
                if (j == grid.length || checkIfFoundDefinition(i, j)) {
                    System.out.println("Il n'y a pas de cases libre en vertical directe");
                    break;
                }
                while (j < grid.length && !checkIfFoundDefinition(i, j)) {
                    if(grid[i][j].getLabel().equals(" ")) {
                        casesOnTheWay += ".";
                    } else {
                        casesOnTheWay += grid[i][j].getLabel();
                    }
                    totalEmptyCases++;
                    j++;
                }
                System.out.println("En vertical directe il y a " + totalEmptyCases + "\n");
                result = true;
                addWord(direction, definitionX, definitionY, totalEmptyCases, casesOnTheWay);
            }
            case 3 -> {
                i++;
                if (i == grid[0].length || checkIfFoundDefinition(i, j)) {
                    System.out.println("Il n'y a pas de cases libre en vertical indirecte");
                    break;
                }
                while (j < grid.length && !checkIfFoundDefinition(i, j)) {
                    if(grid[i][j].getLabel().equals(" ")) {
                        casesOnTheWay += ".";
                    } else {
                        casesOnTheWay += grid[i][j].getLabel();
                    }
                    totalEmptyCases++;
                    j++;
                }
                System.out.println("En vertical indirecte il y a " + totalEmptyCases + "\n");
                result = true;
                addWord(direction, definitionX, definitionY, totalEmptyCases, casesOnTheWay);
            }
        }

        return result;
    }


    /**
     * Retourne les cases déjà complétées sur la ligne
     * @param x Integer x
     * @param y Integer y
     * @param direction Integer direction
     * @return String casesOnTheWay
     */
    private String getCasesOnTheWay(int x, int y, int direction)
    {
        String casesOnTheWay = "";

        switch (direction) {
            case 0 -> {
                x++;
                while (x < grid[0].length && !checkIfFoundDefinition(x, y)) {
                    if(grid[x][y + 1].getLabel().matches("[a-zA-Z]") &&
                            grid[x][y - 1].getLabel().matches("[a-zA-Z]")) {
                        casesOnTheWay += grid[x][y].getLabel();
                    } else {
                        casesOnTheWay += ".";
                    }
                    x++;
                }
            }
            case 1 -> {
                y++;
                while (x < grid[0].length && !checkIfFoundDefinition(x, y)) {
                    if(grid[x][y].getLabel().equals(" ")) {
                        casesOnTheWay += ".";
                    } else {
                        casesOnTheWay += grid[x][y].getLabel();
                    }
                    x++;
                }
            }
            case 2 -> {
                y++;
                while (y < grid.length && !checkIfFoundDefinition(x, y)) {
                    if(grid[x][y].getLabel().equals(" ")) {
                        casesOnTheWay += ".";
                    } else {
                        casesOnTheWay += grid[x][y].getLabel();
                    }
                    y++;
                }
            }
            case 3 -> {
                x++;
                while (y < grid.length && !checkIfFoundDefinition(x, y)) {
                    if(grid[x][y].getLabel().equals(" ")) {
                        casesOnTheWay += ".";
                    } else {
                        casesOnTheWay += grid[x][y].getLabel();
                    }
                    y++;
                }
            }
        }

        return  casesOnTheWay;
    }


    /**
     * Vérifie si il y a une définition à un emplacement donné
     * @param x Integer x
     * @param y Integer y
     * @return Boolean result
     */
    private boolean checkIfFoundDefinition(int x, int y)
    {
        boolean result = false;

        for (Definition definition : definitions) {
            if (definition.getX() == x && definition.getY() == y) {
                result = true;
                break;
            }
        }

        return result;
    }


    /**
     * Retourne une definition a partir de coordonees
     * @param x Integer x
     * @param y Integer y
     * @return Definition def
     */
    private Definition getDefinition(int x, int y)
    {
        Definition def = null;

        for(Definition definition : definitions) {
            if (definition.getX() == x && definition.getY() == y) {
                def = definition;
                break;
            }
        }

        return def;
    }


    /**
     * Ajoute une mot dans la grille
     * @param direction Integer direction
     * @param positionX Integer positionX
     * @param positionY Integer positionY
     * @param nbEmptyCases Integer nbEmptyCases
     * @param casesOnTheWay String casesOnTheWay
     */
    private void addWord(int direction, int positionX, int positionY, int nbEmptyCases, String casesOnTheWay)
    {
        FileManager dico = new FileManager();
        String word = dico.getRandomWordWithLetter(casesOnTheWay, nbEmptyCases);

        switch (direction) {
            case 0 -> {
                positionX++;
                for (int i = 0; i < word.length(); i++) {
                    grid[positionX][positionY].setLabel(Character.toString(word.charAt(i)));
                    positionX++;
                }
            }
            case 1 -> {
                positionY++;
                for (int i = 0; i < word.length(); i++) {
                    grid[positionX][positionY].setLabel(Character.toString(word.charAt(i)));
                    positionX++;
                }
            }
            case 2 -> {
                positionY++;
                for (int i = 0; i < word.length(); i++) {
                    grid[positionX][positionY].setLabel(Character.toString(word.charAt(i)));
                    positionY++;
                }
            }
            case 3 -> {
                positionX++;
                for (int i = 0; i < word.length(); i++) {
                    grid[positionX][positionY].setLabel(Character.toString(word.charAt(i)));
                    positionY++;
                }
            }
        }
    }


    /*
    public void deleteWord()
    {
        System.out.println("Supprimer la definition en :");
        System.out.println("Saisir la position x : ");

        while(!sc.hasNextInt()) {
            System.out.println("Veuillez faire un choix entre 1 et " + width);
            sc.next();
        }

        int x = sc.nextInt() - 1;

        System.out.println("Saisir la position y : ");

        while(!sc.hasNextInt()) {
            System.out.println("Veuillez faire un choix entre 1 et " + height);
            sc.next();
        }

        int y = sc.nextInt() - 1;

        if(checkIfFoundDefinition(x, y)) {
            Definition def = getDefinition(x, y);
            String str = getCasesOnTheWay(def.getX(), def.getY(), def.getDirection());

            System.out.println(str);

            switch (def.getDirection()) {
                case 0 -> {
                    x++;
                    for(int i = 0; i < str.length(); i++) {
                        if(!Character.toString(str.charAt(i)).equals(".")) {
                            grid[x][y].setLabel(" ");
                        }
                        x++;
                    }
                }
                case 1 -> {
                    y++;
                    while(!grid[x][y].getLabel().equals("D")) {
                        grid[x][y].setLabel(" ");
                        x++;
                    }
                }
                case 2 -> {
                    y++;
                    while(!grid[x][y].getLabel().equals("D")) {
                        grid[x][y].setLabel(" ");
                        y++;
                    }
                }
                case 3 -> {
                    x++;
                    while(!grid[x][y].getLabel().equals("D")) {
                        grid[x][y].setLabel(" ");
                        y++;
                    }
                }
                default -> System.out.println("Cette direction n'existe pas");
            }
        } else {
            System.out.println("Aucune definition trouvee");
        }
    }
     */
}

/*
Suppr mot
def double
cas pas de def sur lettre
def sur derniere ligne/col prendre les different cas possible
 */