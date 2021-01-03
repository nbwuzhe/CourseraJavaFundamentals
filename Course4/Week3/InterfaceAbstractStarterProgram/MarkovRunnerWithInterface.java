
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov(int seed) {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        // String st = "yes-this-is-a-thin-pretty-pink-thistle";
        
        int size = 50;
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        runModel(emm, st, size, 531);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource("data/hawthorne.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        long start = System.nanoTime();
        MarkovModel emm2 = new MarkovModel(2);
        runModel(emm2, st, size, seed);
        long end = System.nanoTime();
        System.out.println("Elapsed time for MarkovModel is: " + (end-start)/1e6 + " ms");
        
        start = System.nanoTime();
        EfficientMarkovModel mm2 = new EfficientMarkovModel(2);
        runModel(mm2, st, size, seed);
        end = System.nanoTime();
        System.out.println("Elapsed time for EfficientMarkovModel is: " + (end-start)/1e6 + " ms");
    }
}
