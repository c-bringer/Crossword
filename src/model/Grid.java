package model;

import util.File;

import java.util.Scanner;

public class Grid
{
    int height;
    int width;
    Case grid[][];

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

    public void displayGrid() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                System.out.printf(" %s |", grid[x][y].getLabel());
            }
            System.out.print("\n");
        }
    }

    public void setDefinition() {
        Scanner sc = new Scanner(System.in);
        int positionX, positionY;

        do
        {
            System.out.print("Position x de la definition: ");
            positionX = sc.nextInt() - 1;

            System.out.print("Position Y de la definition: ");
            positionY = sc.nextInt() - 1;
            if(positionX == grid[0].length - 1 && positionY == grid.length - 1) {
                System.out.println("Choisir une autre position");
            }
        }while(positionX == grid[0].length - 1 && positionY == grid.length - 1);


        grid[positionX][positionY].setLabel("D");
        displayGrid();

        choseDirection(positionX, positionY);
    }

    public void choseDirection(int definitionX, int definitionY) {
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

    public boolean countEmptyCases(int direction, int definitionX, int definitionY) {
        int i = definitionX, j = definitionY, totalCasesVides = 0;
        boolean result = false;
        String casesSurLeChemin = null;

        switch (direction) {
            case 0 -> {
                i++;
                if (i == grid[0].length || grid[i][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en horizontal directe");
                    break;
                }
                while (i < grid[0].length && !grid[i][j].getLabel().equals("D")) {
                    casesSurLeChemin = casesSurLeChemin + grid[i][j].getLabel();
                    totalCasesVides++;
                    i++;
                }
                System.out.println("En horizontal directe il y a " + totalCasesVides + "\n");
                result = true;
            }
            case 1 -> {
                i++;
                j++;
                if (j == grid.length || grid[i - 1][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en horizontal indirecte");
                    break;
                }
                while (i < grid[0].length && !grid[i][j].getLabel().equals("D")) {
                    casesSurLeChemin = casesSurLeChemin + grid[i][j].getLabel();
                    totalCasesVides++;
                    i++;
                }
                totalCasesVides++;
                System.out.println("En horizontal indirecte il y a " + totalCasesVides + "\n");
                result = true;
            }
            case 2 -> {
                j++;
                if (j == grid.length || grid[i][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en vertical directe");
                    break;
                }
                while (j < grid.length && !grid[i][j].getLabel().equals("D")) {
                    casesSurLeChemin = casesSurLeChemin + grid[i][j].getLabel();
                    totalCasesVides++;
                    j++;
                }
                System.out.println("En vertical directe il y a " + totalCasesVides + "\n");
                result = true;
            }
            case 3 -> {
                i++;
                j++;
                if (i == grid[0].length || grid[i][j - 1].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en vertical indirecte");
                    break;
                }
                while (j < grid.length && !grid[i][j].getLabel().equals("D")) {
                    casesSurLeChemin = casesSurLeChemin + grid[i][j].getLabel();
                    totalCasesVides++;
                    j++;
                }
                totalCasesVides++;
                System.out.println("En vertical indirecte il y a " + totalCasesVides + "\n");
                result = true;
            }
            default -> result = false;
        }

        addWord(direction,definitionX,definitionY,totalCasesVides, casesSurLeChemin.toString());
        return result;
    }

    public void addWord(int direction, int positionX, int positionY, int nbCasesVides, String casesSurLeChemin) {
        File dico = new File();
        String word = dico.getRandomWordWithLetter(casesSurLeChemin, nbCasesVides);

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
