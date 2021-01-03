
/**
 * Write a description of Part1 here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {   
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestHour = null;
        for (CSVRecord currRow : parser) {
            if (coldestHour == null) {
                coldestHour = currRow;
            }
            
            double tempa = Double.parseDouble(currRow.get("TemperatureF"));
            double tempb = Double.parseDouble(coldestHour.get("TemperatureF"));
            
            if (tempa < tempb && tempa > -500) {
                coldestHour = currRow;
            }
        }
        
        return coldestHour;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        System.out.println(coldestRecord.get("TemperatureF") + " F° " + coldestRecord.get("DateUTC") );
    }
    
    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        FileResource fr;
        CSVParser parser;
        CSVRecord coldestDay = null;
        CSVRecord currDay;
        String coldestFile = null;
        String currFile;
        for (File f : dr.selectedFiles()) {
            fr = new FileResource(f);
            currFile = f.getName();
            parser = fr.getCSVParser();
            currDay = coldestHourInFile(parser);
            if (coldestDay == null || coldestFile == null) {
                coldestDay = currDay;
                coldestFile = currFile;
            }
            
            double tempa = Double.parseDouble(currDay.get("TemperatureF"));
            double tempb = Double.parseDouble(coldestDay.get("TemperatureF"));
            
            if (tempa < tempb && tempa > -500) {
                coldestDay = currDay;
                coldestFile = currFile;
            }
        }
        
        return coldestFile;
    }
    
    public void testFileWithColdestTemperature() {
        
        String fileName = fileWithColdestTemperature();
        String filePath = "nc_weather/2013/";
        System.out.println("Coldest day was in file " + fileName);
        
        FileResource fr = new FileResource(filePath + fileName);
        CSVParser parser = fr.getCSVParser();
        CSVRecord rec = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + rec.get("TemperatureF"));
        
        System.out.println("All the Temperatures on the coldest day were:");
        parser = fr.getCSVParser(); // Need to re-init CSV parser since we used it during calling the function coldestHourInFile.
        for (CSVRecord r : parser) {            
            System.out.println(r.get("DateUTC") + " " + r.get("TemperatureF"));
        }
        
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityHour = null;
        for (CSVRecord currRow : parser) {
            if (lowestHumidityHour == null) {
                lowestHumidityHour = currRow;
            }
            
            if (!currRow.get("Humidity").equals("N/A")) {
                double tempa = Double.parseDouble(currRow.get("Humidity"));
                double tempb = Double.parseDouble(lowestHumidityHour.get("Humidity"));
                
                if (tempa < tempb) {
                    lowestHumidityHour = currRow;
                }
            }
        }
        
        return lowestHumidityHour;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public String fileWithLowestHumidity() {
        DirectoryResource dr = new DirectoryResource();
        FileResource fr;
        CSVParser parser;
        CSVRecord lowestHumidDay = null;
        CSVRecord currDay;
        String lowestHumidFile = null;
        String currFile;
        for (File f : dr.selectedFiles()) {
            fr = new FileResource(f);
            currFile = f.getName();
            parser = fr.getCSVParser();
            currDay = lowestHumidityInFile(parser);
            if (lowestHumidDay == null || lowestHumidFile == null) {
                lowestHumidDay = currDay;
                lowestHumidFile = currFile;
            }
            
            if (!currDay.get("Humidity").equals("N/A")) {
                double tempa = Double.parseDouble(currDay.get("Humidity"));
                double tempb = Double.parseDouble(lowestHumidDay.get("Humidity"));
            
                if (tempa < tempb) {
                    lowestHumidDay = currDay;
                    lowestHumidFile = currFile;
                }
            }
        }
        
        return lowestHumidFile;
    }
    
    public void testFileWithLowestHumidity() {
        
        String fileName = fileWithLowestHumidity();
        String filePath = "nc_weather/2013/";
        System.out.println("Lowest humidity was in file " + fileName);
        
        FileResource fr = new FileResource(filePath + fileName);
        CSVParser parser = fr.getCSVParser();
        CSVRecord rec = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity on that day was " + rec.get("Humidity") + " at " + rec.get("DateUTC"));        
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        int numOfItems = 0;
        double totalTemperature = 0;
        
        for (CSVRecord currRow : parser) {
            String currHumidStr = currRow.get("Humidity");
            numOfItems ++;
            totalTemperature += Double.parseDouble(currRow.get("TemperatureF"));
        }
        
        if (numOfItems == 0)
            return 0;
        else
            return totalTemperature/numOfItems;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int numOfItems = 0;
        double totalTemperature = 0;
        
        for (CSVRecord currRow : parser) {
            String currHumidStr = currRow.get("Humidity");
            if (!currHumidStr.equals("N/A")) {
                double currHumid = Double.parseDouble(currHumidStr);
                
                if (currHumid >= value) {
                    numOfItems ++;
                    totalTemperature += Double.parseDouble(currRow.get("TemperatureF"));
                }
            }
        }
        
        if (numOfItems == 0)
            return 0;
        else
            return totalTemperature/numOfItems;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avgTemp == 0) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        }
    }
}
