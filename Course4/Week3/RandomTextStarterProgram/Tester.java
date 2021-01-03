
/**
 * Write a description of Tester here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import edu.duke.*;

public class Tester {
    public void testGetFollows(String key) {
        MarkovOne markov = new MarkovOne();
        //String st = "this is a test yes this is a test.";
        String st = "ttta";
        markov.setTraining(st);
        System.out.println(markov.getFollows(key));
    }
    
    public void testGetFollowsWithFile(String key) {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        System.out.println(markov.getFollows(key).size());
    }
    
}
