package model;

import util.File;

import java.util.Scanner;

public class Grille
{
    int hauteur;
    int largeur;
    Case grille[][];

    public void creerGrille()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Saisir le nombre de lignes dans la matrice: ");
        hauteur = sc.nextInt();

        System.out.print("Saisir le nombre de colonne dans la matrice: ");
        largeur = sc.nextInt();

        grille = new Case[largeur][hauteur];

        for(int y = 0; y < hauteur; y++) {
            for(int x = 0; x < largeur; x++) {
                grille[x][y] = new Case();
                grille[x][y].setLabel(" ");
            }
        }
    }

    public void afficherGrille() {
        for(int y = 0; y < hauteur; y++) {
            for(int x = 0; x < largeur; x++) {
                System.out.printf(" %s |",grille[x][y].getLabel());
            }
            System.out.print("\n");
        }
    }

    public void placerDefinition() {
        Scanner sc = new Scanner(System.in);
        int positionX, positionY;

        do
        {
            System.out.print("Position x de la definition: ");
            positionX = sc.nextInt() - 1;

            System.out.print("Position Y de la definition: ");
            positionY = sc.nextInt() - 1;
            if(positionX == grille[0].length - 1 && positionY == grille.length - 1) {
                System.out.println("Choisir une autre position");
            }
        }while(positionX == grille[0].length - 1 && positionY == grille.length - 1);


        grille[positionX][positionY].setLabel("D");
        afficherGrille();

        choisirDirection(positionX, positionY);
    }

    public void choisirDirection(int definitionX, int definitionY) {
        Scanner sc = new Scanner(System.in);
        int direction;

        do {
            System.out.println("Dans quel direction voulez-vous placer un mot :");
            System.out.println("[0] Horizontal direct");
            System.out.println("[1] Horizontal indirect");
            System.out.println("[2] Vertical direct");
            System.out.println("[3] Vertical indirect");

            direction = sc.nextInt();
        }while(!calculerCasesVides(direction, definitionX, definitionY));
    }

    public boolean calculerCasesVides(int direction, int definitionX, int definitionY) {
        int i = definitionX, j = definitionY, totalCasesVides = 0;
        boolean result = false;
        String casesSurLeChemin = null;

        switch (direction) {
            case 0 -> {
                i++;
                if (i == grille[0].length || grille[i][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en horizontal directe");
                    break;
                }
                while (i < grille[0].length && !grille[i][j].getLabel().equals("D")) {
                    casesSurLeChemin = casesSurLeChemin + grille[i][j].getLabel();
                    totalCasesVides++;
                    i++;
                }
                System.out.println("En horizontal directe il y a " + totalCasesVides + "\n");
                result = true;
            }
            case 1 -> {
                i++;
                j++;
                if (j == grille.length || grille[i - 1][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en horizontal indirecte");
                    break;
                }
                while (i < grille[0].length && !grille[i][j].getLabel().equals("D")) {
                    casesSurLeChemin = casesSurLeChemin + grille[i][j].getLabel();
                    totalCasesVides++;
                    i++;
                }
                totalCasesVides++;
                System.out.println("En horizontal indirecte il y a " + totalCasesVides + "\n");
                result = true;
            }
            case 2 -> {
                j++;
                if (j == grille.length || grille[i][j].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en vertical directe");
                    break;
                }
                while (j < grille.length && !grille[i][j].getLabel().equals("D")) {
                    casesSurLeChemin = casesSurLeChemin + grille[i][j].getLabel();
                    totalCasesVides++;
                    j++;
                }
                System.out.println("En vertical directe il y a " + totalCasesVides + "\n");
                result = true;
            }
            case 3 -> {
                i++;
                j++;
                if (i == grille[0].length || grille[i][j - 1].getLabel().equals("D")) {
                    System.out.println("Il n'y a pas de cases libre en vertical indirecte");
                    break;
                }
                while (j < grille.length && !grille[i][j].getLabel().equals("D")) {
                    casesSurLeChemin = casesSurLeChemin + grille[i][j].getLabel();
                    totalCasesVides++;
                    j++;
                }
                totalCasesVides++;
                System.out.println("En vertical indirecte il y a " + totalCasesVides + "\n");
                result = true;
            }
            default -> result = false;
        }

        ajouterMot(direction,definitionX,definitionY,totalCasesVides, casesSurLeChemin.toString());
        return result;
    }

    public void ajouterMot(int direction, int positionX, int positionY, int nbCasesVides, String casesSurLeChemin) {
        File dico = new File();
        String word = dico.getRandomWordWithLetter(casesSurLeChemin, nbCasesVides);

        switch (direction) {
            case 0 -> {
                positionX++;
                for (int i = 0; i < word.length(); i++) {
                    grille[positionX][positionY].setLabel(Character.toString(word.charAt(i)));
                    positionX++;
                }
            }
            case 1 -> {
                positionY++;
                for (int i = 0; i < word.length(); i++) {
                    grille[positionX][positionY].setLabel(Character.toString(word.charAt(i)));
                    positionX++;
                }
            }
            case 2 -> {
                positionY++;
                for (int i = 0; i < word.length(); i++) {
                    grille[positionX][positionY].setLabel(Character.toString(word.charAt(i)));
                    positionY++;
                }
            }
            case 3 -> {
                positionX++;
                for (int i = 0; i < word.length(); i++) {
                    grille[positionX][positionY].setLabel(Character.toString(word.charAt(i)));
                    positionY++;
                }
            }
        }
    }
}
