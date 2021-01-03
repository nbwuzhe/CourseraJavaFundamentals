
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        MarkovWord markovWord = new MarkovWord(5);
        markovWord.setRandom(844);
        markovWord.setTraining(st);
        int size = 50;
        for(int k=0; k < 3; k++){ 
            String out = markovWord.getRandomText(size); 
            printOut(out); 
        } 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

    public void testHashMap() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //String st = "this is a test yes this is really a test";
        //String st = "this is a test yes this is really a test yes a test this is wow";
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(3);
        markovWord.setRandom(371);
        markovWord.setTraining(st);
        int size = 50;
        for(int k=0; k < 3; k++){ 
            String out = markovWord.getRandomText(size); 
            printOut(out); 
        } 
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource("data/hawthorne.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        long start = System.nanoTime();
        MarkovWord mw2 = new MarkovWord(2);
        runModel(mw2, st, size, seed);
        long end = System.nanoTime();
        System.out.println("Elapsed time for MarkovWord is: " + (end-start)/1e6 + " ms");
        
        start = System.nanoTime();
        EfficientMarkovWord emw2 = new EfficientMarkovWord(2);
        runModel(emw2, st, size, seed);
        end = System.nanoTime();
        System.out.println("Elapsed time for EfficientMarkovWord is: " + (end-start)/1e6 + " ms");
    }
    
}
