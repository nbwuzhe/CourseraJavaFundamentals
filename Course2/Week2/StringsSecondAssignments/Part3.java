
/**
 * Write a description of Part3 here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
public class Part3 {
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
    
    public int countGenes(String dna) {
        int encounterNumber = 0;
        int currIndex;
        String gene = findGene(dna);
        
        while(!gene.isEmpty()) {
            currIndex = dna.indexOf(gene);
            if (currIndex != -1){
                encounterNumber ++;
                dna = dna.substring(currIndex + gene.length());
                gene = findGene(dna);
            }
        }
        
        return encounterNumber;
    }
    
    public void testCountGenes() {
        String dnaSeq1 = "ATGTAAGATGCCCTAGT";
        System.out.println(countGenes(dnaSeq1));
    }
}
