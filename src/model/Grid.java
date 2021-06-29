package model;

import util.File;

import java.util.Scanner;

public class Grid
{
    int height;
    int width;
    Case grid[][];
    Definition definitions[][];


    /**
     * Cree la grille sur n x n
     */
    public void createGrid()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Saisir le nombre de lignes dans la matrice: ");
        height = sc.nextInt();

        System.out.print("Saisir le nombre de colonne dans la matrice: ");
        width = sc.nextInt();

        grid = new Case[width][height];

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
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
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
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
        Scanner sc = new Scanner(System.in);
        int x, y;

        do {
            System.out.print("Position x de la definition: ");
            x = sc.nextInt() - 1;

            System.out.print("Position Y de la definition: ");
            y = sc.nextInt() - 1;
            if(x == grid[0].length - 1 && y == grid.length - 1) {
                System.out.println("Choisir une autre position");
            }
        }while(x == grid[0].length - 1 && y == grid.length - 1);


        grid[x][y].setLabel("D");
        displayGrid();

        choseDirection(x, y);
    }


    /**
     * Choisi une direction pour la definition
     * @param definitionX Integer definitionX
     * @param definitionY Integer definitionY
     */
    public void choseDirection(int definitionX, int definitionY)
    {
        Scanner sc = new Scanner(System.in);
        int direction;

        do {
            System.out.println("Dans quel direction voulez-vous placer un mot :");
            System.out.println("[0] Horizontal direct");
            System.out.println("[1] Horizontal indirect");
            System.out.println("[2] Vertical direct");
            System.out.println("[3] Vertical indirect");

            direction = sc.nextInt();
        }while(!countEmptyCases(direction, definitionX, definitionY));
    }


    /**
     * Compte le nombre de cases vide sur le chemin d'une definition
     * @param direction Integer direction
     * @param definitionX Integer definitionX
     * @param definitionY Integer definitionY
     * @return boolean result
     */
    public boolean countEmptyCases(int direction, int definitionX, int definitionY) {
        int i = definitionX, j = definitionY, totalEmptyCases = 0;
        boolean result = false;
        String casesOnTheWay = "";

        switch (direction) {
            case 0 -> {
                i++;
                if (i == grid[0].length || grid[i][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en horizontal directe");
                    break;
                }
                while (i < grid[0].length && !grid[i][j].getLabel().equals("D")) {
                    if(grid[i][j].getLabel().equals(" ")) {
                        casesOnTheWay = casesOnTheWay + ".";
                    } else {
                        casesOnTheWay = casesOnTheWay + grid[i][j].getLabel();
                    }
                    totalEmptyCases++;
                    i++;
                }
                System.out.println("En horizontal directe il y a " + totalEmptyCases + "\n");
                result = true;
            }
            case 1 -> {
                j++;
                if (j == grid.length || grid[i][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en horizontal indirecte");
                    break;
                }
                while (i < grid[0].length && !grid[i][j].getLabel().equals("D")) {
                    if(grid[i][j].getLabel().equals(" ")) {
                        casesOnTheWay = casesOnTheWay + ".";
                    } else {
                        casesOnTheWay = casesOnTheWay + grid[i][j].getLabel();
                    }
                    totalEmptyCases++;
                    i++;
                }
                System.out.println("En horizontal indirecte il y a " + totalEmptyCases + "\n");
                result = true;
            }
            case 2 -> {
                j++;
                if (j == grid.length || grid[i][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en vertical directe");
                    break;
                }
                while (j < grid.length && !grid[i][j].getLabel().equals("D")) {
                    if(grid[i][j].getLabel().equals(" ")) {
                        casesOnTheWay = casesOnTheWay + ".";
                    } else {
                        casesOnTheWay = casesOnTheWay + grid[i][j].getLabel();
                    }
                    totalEmptyCases++;
                    j++;
                }
                System.out.println("En vertical directe il y a " + totalEmptyCases + "\n");
                result = true;
            }
            case 3 -> {
                i++;
                if (i == grid[0].length || grid[i][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en vertical indirecte");
                    break;
                }
                while (j < grid.length && !grid[i][j].getLabel().equals("D")) {
                    if(grid[i][j].getLabel().equals(" ")) {
                        casesOnTheWay = casesOnTheWay + ".";
                    } else {
                        casesOnTheWay = casesOnTheWay + grid[i][j].getLabel();
                    }
                    totalEmptyCases++;
                    j++;
                }
                System.out.println("En vertical indirecte il y a " + totalEmptyCases + "\n");
                result = true;
            }
            default -> result = false;
        }

        System.out.println(casesOnTheWay);
        addWord(direction, definitionX, definitionY, totalEmptyCases, casesOnTheWay);
        return result;
    }


    /**
     * Ajoute une mot dans la grille
     * @param direction Integer direction
     * @param positionX Integer positionX
     * @param positionY Integer positionY
     * @param nbEmptyCases Integer nbEmptyCases
     * @param casesOnTheWay String casesOnTheWay
     */
    public void addWord(int direction, int positionX, int positionY, int nbEmptyCases, String casesOnTheWay) {
        File dico = new File();
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
}
