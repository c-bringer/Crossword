package util;

import javax.swing.plaf.synth.SynthTextAreaUI;
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


    /**
     * Recupere la liste des mots du dictionnaire en lisant le contenu du fichier
     */
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


    /**
     * Affiche tous les mots
     */
    public void printWords()
    {
        System.out.println("Affichage des mots présents dans le dictionnaire");
        for(String m : listeDeMots) {
            System.out.println(m);
        }
    }


    /**
     * Affiche les mots qui commence par la lettre x
     * @param s String s
     */
    public void printWordsStartedBy(String s)
    {
        System.out.printf("Affichage des mots commençant par %s%n", s);
        for(String m : listeDeMots) {
            if(m.startsWith(s)) {
                System.out.println(m);
            }
        }
    }


    /**
     * Affiche les mots qui commence par la lettre x et de n taille
     * @param s String s
     * @param i Integer i
     */
    public void printWordsStartedByAndBySize(String s, int i)
    {
        System.out.printf("Affichage des mots commençant par %s et qui font %s lettres de long", s, i);
        for(String m : listeDeMots) {
            if(m.startsWith(s) && m.length() == i) {
                System.out.println(m);
            }
        }
    }


    /**
     * Affiche les mots de n lettres
     * @param nb Integer nb
     */
    public void printWordsNbLetters(int nb)
    {
        System.out.printf("Affichage des mots qui font %d lettres de long", nb);
        for(String m : listeDeMots) {
            if(m.length() == nb) {
                System.out.println(m);
            }
        }
    }


    /**
     * Retourne un mot au hasard qui fait n lettres de long
     * @param nb Integer nb
     * @return String
     */
    public String getRandomWordNbLetters(int nb)
    {
        List<String> words = new ArrayList<>();
        Random rand = new Random();

        for(String m : listeDeMots) {
            if(m.length() == nb) {
                words.add(m);
            }
        }

        return words.get(rand.nextInt(words.size()));
    }


    /**
     * Retourne la liste des mots de n lettres
     * @param nb Integer nb
     * @return List String
     */
    public List<String> getWordsNbLetters(int nb)
    {
        List<String> words = new ArrayList<>();

        for(String m : listeDeMots) {
            if(m.length() == nb) {
                words.add(m);
            }
        }

        return words;
    }


    /**
     * Retourne un mot au hasard qui contient des lettres
     * @param letters String letters
     * @param nb Integer nb
     * @return String word
     */
    public String getRandomWordWithLetter(String letters, int nb)
    {
        System.out.println("Lettres trouver sur le chemin : " + letters + "!");
        List<String> words = getWordsNbLetters(nb);
        List<String> acceptedWords = new ArrayList<>();
        Random rand = new Random();

        for(String m : words) {
            if(m.matches(letters)) {
                acceptedWords.add(m);
            }
        }

        return acceptedWords.get(rand.nextInt(acceptedWords.size()));
    }
}
