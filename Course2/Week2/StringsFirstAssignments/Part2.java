
/**
 * Write a description of Part2 here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String endCodon) {
        String result;
        if (dna.equals(dna.toUpperCase())) {
            startCodon = startCodon.toUpperCase();
            endCodon = endCodon.toUpperCase();
        }
        else {
            startCodon = startCodon.toLowerCase();
            endCodon = endCodon.toLowerCase();
        }
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        
        int endIndex = dna.indexOf(endCodon, startIndex+3);
        if (endIndex == -1) {
            return "";
        }
        
        result = dna.substring(startIndex, endIndex+3);
        if (result.length() % 3 != 0) {
            return "";
        }
        
        return result;
    }
    
    public void testSimpleGene() {
        String dnaSeq1 ="TGACTGATGCAGTGACTGAGACTGTAAGTCTGAC";
        System.out.println("DNA Sequence: " + dnaSeq1);
        System.out.println("Gene: " + findSimpleGene(dnaSeq1, "ATG", "TAA"));
        
        String dnaSeq2 ="TGACTGATGCAGTGACTGAGACTGGTCTGAC";
        System.out.println("DNA Sequence: " + dnaSeq2);
        System.out.println("Gene: " + findSimpleGene(dnaSeq2, "ATG", "TAA"));
        
        String dnaSeq3 ="TGACTGCAGTGACTGAGACTGTAAGTCTGAC";
        System.out.println("DNA Sequence: " + dnaSeq3);
        System.out.println("Gene: " + findSimpleGene(dnaSeq3, "ATG", "TAA"));
        
        String dnaSeq4 ="TGACTGATGCAGTGACTGAGCTGTAAGTCTGAC";
        System.out.println("DNA Sequence: " + dnaSeq4);
        System.out.println("Gene: " + findSimpleGene(dnaSeq4, "ATG", "TAA"));
        
        String dnaSeq5 ="tgactgatgcagtgactgagactgtaagtcgac";
        System.out.println("DNA Sequence: " + dnaSeq5);
        System.out.println("Gene: " + findSimpleGene(dnaSeq5, "ATG", "TAA"));
    }
}
