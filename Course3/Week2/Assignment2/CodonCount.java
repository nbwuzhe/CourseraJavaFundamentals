
/**
 * Write a description of CodonCount here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap<String, Integer> mapCodonCount;
    
    public CodonCount(){
        mapCodonCount = new HashMap<>();
    }
    
    public void buildCodonMap(int start, String dna) {
        mapCodonCount.clear();
        
        for (int m = start; m <= dna.length() - 3; m += 3) {
            String codon = dna.substring(m, m+3);
            if (!mapCodonCount.containsKey(codon)) {
                mapCodonCount.put(codon, 1);
            }
            else {
                mapCodonCount.put(codon, mapCodonCount.get(codon)+1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        int maxCount = 0;
        String codonWithMaxCount = "";
        for (String codon : mapCodonCount.keySet()) {
            if (mapCodonCount.get(codon) > maxCount) {
                maxCount = mapCodonCount.get(codon);
                codonWithMaxCount = codon;
            }
        }
        
        return codonWithMaxCount;
    }
    
    public void printCodonCounts(int start, int end) {
        for (String codon : mapCodonCount.keySet()) {
            if (mapCodonCount.get(codon) >= start && mapCodonCount.get(codon) <= end) {
                System.out.println(codon + "\t" + mapCodonCount.get(codon));
            }
        }
    }
    
    public void tester(int start, int end) {
        FileResource fr = new FileResource();
        // String dna = fr.asString();
        for (String dna : fr.lines()) {
            dna = dna.toUpperCase();
            
            for (int s = 0; s <= 2; s++) {
                buildCodonMap(s, dna);
                String mostCommonCodon = getMostCommonCodon();
                System.out.println("Reading frame starting with " + s + " results in " + mapCodonCount.size() + " unique codons");
                System.out.println("Most common codon is " + mostCommonCodon + " with count " + mapCodonCount.get(mostCommonCodon));
                System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
                printCodonCounts(start, end);
                System.out.println("\n");
            }
        }
    }
}
