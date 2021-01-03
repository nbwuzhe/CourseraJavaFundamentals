import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> consideredCategory;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    // private static String dataSourceDirectory = "data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        usedWords = new ArrayList<>();
        myMap = new HashMap<>();
        consideredCategory = new ArrayList<>();
        String [] catList = {"adjective", "noun", "color",
            "country", "name", "animal",
            "timeframe", "verb", "fruit"};
            
        for (String category: catList) {
            ArrayList<String> list = readIt(source+"/" + category + ".txt");
            myMap.put(category, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)) {
            if (!consideredCategory.contains(label)) {
                consideredCategory.add(label);
            }
            
            return randomFrom(myMap.get(label));
        }
        else if (label != "number") {
            return ""+myRandom.nextInt(50)+5;
        }
        else {
            return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        // Added by Tim Wu
        while(usedWords.contains(sub)) {
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWords.add(sub);
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    /*
     * This method returns the total number of words in all the ArrayLists in the HashMap. 
     */
    private int totalWordsInMap() {
        int totalWordNumber = 0;
        
        for (String category : myMap.keySet()) {
            totalWordNumber += myMap.get(category).size();
        }
        
        return totalWordNumber;
    }
    
    /*
     * This method returns the total number of words in the ArrayLists of the categories
     * that were used for a particular GladLib. 
     * e.g.  If only noun, color, and adjective were the categories used in a GladLib,
     * then only calculate the sum of all the words in those three ArrayLists. 
     */
    private int totalWordsConsidered() {
        int totalWordNumber = 0;
        for (String category : consideredCategory) {
            totalWordNumber += myMap.get(category).size();
        }
        
        return totalWordNumber;
    }
    
    public void makeStory(){
        // Tim Wu added
        usedWords.clear();
        
        System.out.println("\n");
        // Tim Wu modified.
        // String story = fromTemplate("data/madtemplate.txt");
        String story = fromTemplate("data/madtemplate2.txt");
        
        printOut(story, 60);
        
        System.out.println("\n");
        System.out.println("Total number of words that were possible to pick from is " + totalWordsInMap());
        System.out.println("Total number of words that were considered is " + totalWordsConsidered());
    }
    


}

