
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

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
    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        String gene = findGene(dna);
        int currIndex;
        while(!gene.isEmpty()) {
            currIndex = dna.indexOf(gene);
            sr.add(gene);
            dna = dna.substring(currIndex+gene.length());
            gene = findGene(dna);
        }
        return sr;
    }
    
    public void testGetAllGenes() {
        String dnaSeq1 = "ACTGCGATATGTAGGTCATGGTAACGTGGTAAGTCTA";
        StorageResource sr = getAllGenes(dnaSeq1);
        for (String g : sr.data()) {
            System.out.println(g);
        }
    }
    
    public double cgRatio(String dna) {
        int countCG = 0;
        for (int m = 0; m < dna.length(); m++) {
            if (dna.charAt(m) == 'C' || dna.charAt(m) == 'G') {
                countCG ++;
            }
        }
        return (double) countCG/dna.length(); 
    }
    
    public int countCTG(String dna) {
        int encounterNum = 0;
        int currIndex = dna.indexOf("CTG");
        
        while(currIndex != -1) {
            encounterNum ++;
            currIndex = dna.indexOf("CTG", currIndex+3);
        }
        
        return encounterNum;
    }
    
    public void processGenes(StorageResource sr) {
        int numOfLongGenes = 0;
        int lengthThreshold = 60;
        
        int numOfHighCGRatio = 0;
        double cgRatioThreshold = 0.35;
        
        int longestLength = 0;
        for (String s : sr.data()) {
            if (s.length() > lengthThreshold) {
                System.out.println("Gene \"" + s + "\" is longer than " + lengthThreshold + " characters.");
                numOfLongGenes ++;
            }
            
            if (cgRatio(s) > cgRatioThreshold) {
                System.out.println("Gene \"" + s + "\" has a C-G-ratio > " + cgRatioThreshold + ".");
                numOfHighCGRatio ++;
            }
            
            if (s.length() > longestLength) {
                longestLength = s.length();
            }
        }
        
        System.out.println("Total Number of Genes: " + sr.size());
        System.out.println("Number of genes with a length > " + lengthThreshold + " is : " + numOfLongGenes);
        System.out.println("Number of genes with a C-G-ratio > " + cgRatioThreshold + " is : " + numOfHighCGRatio);
        System.out.println("Length of the longest gene is : " + longestLength);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println("Number of codon CTG: " + countCTG(dna));
        
        //String dnaSeq1 = "ACTGCGATATGCTACTATCACTACTACTATAGGTCATCGTAATGTGGTGCTGCTGCTGCTGCTCGCTGTAAGTCTA";
        //StorageResource sr = getAllGenes(dnaSeq1);
        //processGenes(sr);
        
        
    }
}
