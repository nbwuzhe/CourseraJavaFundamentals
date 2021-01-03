
/**
 * Write a description of Part1 here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String result;
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        
        int endIndex = dna.indexOf("TAA", startIndex+3);
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
        System.out.println("Gene: " + findSimpleGene(dnaSeq1));
        
        String dnaSeq2 ="TGACTGATGCAGTGACTGAGACTGGTCTGAC";
        System.out.println("DNA Sequence: " + dnaSeq2);
        System.out.println("Gene: " + findSimpleGene(dnaSeq2));
        
        String dnaSeq3 ="TGACTGCAGTGACTGAGACTGTAAGTCTGAC";
        System.out.println("DNA Sequence: " + dnaSeq3);
        System.out.println("Gene: " + findSimpleGene(dnaSeq3));
        
        String dnaSeq4 ="TGACTGATGCAGTGACTGAGCTGTAAGTCTGAC";
        System.out.println("DNA Sequence: " + dnaSeq4);
        System.out.println("Gene: " + findSimpleGene(dnaSeq4));
    }
}
