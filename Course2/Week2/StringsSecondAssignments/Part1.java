
/**
 * Write a description of Part1 here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex+3);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodon() {
        //                        v                    v
        String dnaSeq1 = "ACTGCGATACGTAGGTCATCGTAACGTGGTAAGTCTA";
        if (findStopCodon(dnaSeq1, 8, "TAA") != 29) {
            System.out.println("Wrong result in DNA Sequence 1");
        }
        
        //                        v           v       
        String dnaSeq2 = "ACTGCGATACGTAGGTCATCTAACGTGGTAAGTCTA";
        if (findStopCodon(dnaSeq2, 8, "TAA") != 20) {
            System.out.println("Wrong result in DNA Sequence 2");
        }
        
        System.out.println("End of the testFindStopCodon.");
    }
    
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        int stopIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        
        if (stopIndex == dna.length()) {
            return "";
        }
        
        return dna.substring(startIndex, stopIndex+3);        
    }
    
    public void testFindGene() {
        //                        v  v
        String dnaSeq1 = "ACTGCGATATGTAGGTCATCGTAACGTGGTAAGTCTA";
        System.out.println("DNA sequence: " + dnaSeq1);
        System.out.println("Gene: " + findGene(dnaSeq1));
        
        // 
        String dnaSeq2 = "ACTGCGATTAGGTCATCGTATGGTAAGTCTA";
        System.out.println("DNA sequence: " + dnaSeq2);
        System.out.println("Gene: " + findGene(dnaSeq2));
        
        //                        v           v      v
        String dnaSeq3 = "ACTGCGATATGGTCATGACGTAGCGTGTAAGTCTA";
        System.out.println("DNA sequence: " + dnaSeq3);
        System.out.println("Gene: " + findGene(dnaSeq3));
        
        System.out.println("End of testFindGene.");
    }
    
    public void printAllGenes(String dna) {
        String gene = findGene(dna);
        int currIndex;
        while(!gene.isEmpty()) {
            currIndex = dna.indexOf(gene);
            System.out.println(gene);
            dna = dna.substring(currIndex+gene.length());
            gene = findGene(dna);
        }
    }
}
