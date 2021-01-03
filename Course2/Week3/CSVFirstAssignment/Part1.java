
/**
 * Write a description of Part1 here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                return (country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int countryNum = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                countryNum++;
            }
        }
        return countryNum;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }
}
