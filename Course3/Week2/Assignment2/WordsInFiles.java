
/**
 * Write a description of WordsInFiles here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import java.io.*;
import java.util.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsMap;
    
    public WordsInFiles() {
        wordsMap = new HashMap<>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource (f);
        String fn = f.getName();
        
        for (String word : fr.words()) {
            if (!wordsMap.containsKey(word)) {
                ArrayList<String> fnList = new ArrayList<>();
                fnList.add(fn);
                wordsMap.put(word, fnList);
            }
            else {
                ArrayList<String> tempList = wordsMap.get(word);
                if (!tempList.contains(fn)) { // See if file name is already in the array list
                    tempList.add(fn);
                    wordsMap.put(word, tempList);
                }
            }
        }
    }
    
    private void buildWordFileMap() {
        wordsMap.clear();
        
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxFileNum = 0;
        
        for (String fn : wordsMap.keySet()) {
            if (wordsMap.get(fn).size() > maxFileNum) {
                maxFileNum = wordsMap.get(fn).size();
            }
        }
        
        return maxFileNum;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordList = new ArrayList<>();
        
        for (String word : wordsMap.keySet()) {
            if (number == wordsMap.get(word).size()) {
                if (!wordList.contains(word)) {
                    wordList.add(word);
                }
            }
        }
        
        return wordList;
    }
    
    private void printFilesIn(String word) {
        ArrayList<String> fnList = wordsMap.get(word);
        for(String fn : fnList) {
            System.out.println(fn);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int maxNum = maxNumber();

        ArrayList<String> wordList = wordsInNumFiles(maxNum);
        System.out.println("The greatest number of files a word appears in is " + maxNum);
        System.out.println("And there are " + wordList.size() + " such words: \n");
        
        wordList = wordsInNumFiles(4);
        System.out.println("There are " + wordList.size() + " words that appeared in 4 files.\n");
        
        String word = "sea";
        System.out.println("\"" + word + "\" appears in:");
        printFilesIn(word);
        System.out.println("\n");
        
        word = "tree";
        System.out.println("\"" + word + "\" appears in:");
        printFilesIn(word);
        System.out.println("\n");
        
        /* 
        for (String word : wordList) {
            System.out.println("\"" + word + "\" appears in:");
            printFilesIn(word);
            System.out.println("\n");
        }
        */
    }
}
