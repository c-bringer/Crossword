package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class File
{
    private static final String FICHIER = "liste_francais.txt";
    private ArrayList<String> listeDeMots = null;

    public File()
    {
        super();
        listeDeMots = new ArrayList<>();
        openFile();
    }

    private void openFile()
    {
        try {
            InputStream flux = File.class.getResourceAsStream(FICHIER);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;

            while((ligne=buff.readLine()) != null) {
                listeDeMots.add(ligne);
            }

            buff.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public void printWords()
    {
        System.out.println("affichage des mots présents dans le dictionnaire");
        for(String m : listeDeMots) {
            System.out.println(m);
        }
    }

    public void printWordsStartedBy(String d)
    {
        System.out.printf("affichage des mots commençant par %s%n", d);
        for (String m : listeDeMots) {
            if (m.startsWith(d)) {
                System.out.println(m);
            }
        }
    }

    public void printWordsStartedByAndBySize(String d, int nbCasesLibre)
    {
        System.out.printf("affichage des mots commençant par %s et qui font %d lettres de long", d, nbCasesLibre);
        for (String m : listeDeMots) {
            if (m.startsWith(d) && m.length() == nbCasesLibre) {
                System.out.println(m);
            }
        }
    }

    public void printMotNbLettres(int nb)
    {
        System.out.printf("affichage des mots qui font %d lettres de long", nb);
        for (String m  : listeDeMots) {
            if (m.length()==nb){
                System.out.println(m);
            }
        }
    }

    public String getRandomWordNbLetters(int nb)
    {
        List<String> words = new ArrayList<>();
        Random rand = new Random();

        for (String m : listeDeMots) {
            if (m.length() == nb) {
                words.add(m);
            }
        }

        return words.get(rand.nextInt(words.size()));
    }

    public List<String> getWordsNbLetters(int nb)
    {
        List<String> words = new ArrayList<>();

        for (String m  : listeDeMots) {
            if (m.length() == nb) {
                words.add(m);
            }
        }

        return words;
    }

    public String getRandomWordWithLetter(String letters, int nb)
    {
        String word = letters;
        List<String> words = getWordsNbLetters(nb);
        List<Integer> positionDeLettres = new ArrayList<>();
        List<String> lettres = new ArrayList<>();

        for(int i = 0; i< word.length(); i++) {
            if(Character.toString(word.charAt(i))!=" ") {
                positionDeLettres.add(i);
                lettres.add(Character.toString(word.charAt(i)));
            }
        }

        for (String m  : words) {
            for(int i = 0; i < lettres.size(); i++) {
                if(Character.toString(m.charAt(positionDeLettres.get(i))).compareTo(lettres.get(i))==0)
                {
                    word = m;
                }
                else
                {
                    System.out.println("PAS DE MOTS TROUVER LA CON DE TES MORTS");
                }
            }
        }


        return word;
    }
}
