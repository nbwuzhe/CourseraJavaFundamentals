
/**
 * Write a description of BabyBirths here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoyBirths = 0;
        int totalGirlBirths = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)) {
            int currBirth = Integer.parseInt(record.get(2));
            String gender = record.get(1);
            totalBirths += currBirth;
            
            if (gender.equals("M")) {
                totalBoyBirths += currBirth;
            }
            else {
                totalGirlBirths += currBirth;
            }
        }
        
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Boy's Total Births = " + totalBoyBirths);
        System.out.println("Girl's Total Births = " + totalGirlBirths);
    }
    
    public int getRank(int year, String name, String gender) {
        String fn = "us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fn);
        boolean isFound = false;
        int rank = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (gender.equals(record.get(1))) {
                rank ++;
                
                if (name.equals(record.get(0))) {
                    isFound = true;
                    break;
                }
            }
        }
        
        if (isFound) {
            return rank;
        }
        else {
            return -1;
        }
    }
    
    public String getName(int year, int rank, String gender) {
        String fn = "us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fn);
        int currRank = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (gender.equals(record.get(1))) {
                currRank ++;
                if (currRank == rank) {
                    return (record.get(0));
                }
            }
        }
        return "NO NAME";
    }
    
    /*
     * Function to find what would your name be if you were born in a different year?
     * @ parameters
     * String name: Your name
     * int year: the year that name was born
     * int newYear: The year you would look into
     * String named gender (F for female and M for male).
     */
    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        return getName(newYear, rank, gender);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 99999999;
        int highestRankYear = -1;
        for (File f : dr.selectedFiles()) {
            String fn = f.getName();
            int currYear = Integer.parseInt(fn.substring(3,7)); // Extract year from the file name
            int currRank = getRank(currYear, name, gender);
            if (currRank != -1 && currRank < highestRank) {
                highestRank = currRank;
                highestRankYear = currYear;
            }
        }
        
        return highestRankYear;
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int totalEncounter = 0;
        for (File f : dr.selectedFiles()) {
            String fn = f.getName();
            int currYear = Integer.parseInt(fn.substring(3,7)); // Extract year from the file name
            int currRank = getRank(currYear, name, gender);
            if (currRank != -1) {
                totalRank += currRank;
                totalEncounter ++;
            }
        }
        
        if (totalEncounter > 0){
            return (double)totalRank / totalEncounter;
        }
        else {
            return -1.0;
        }
        
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String fn = "us_babynames/us_babynames_by_year/yob" + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fn);
        boolean isFound = false;
        int totalBirthHigherRank = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (gender.equals(record.get(1))) {
                
                if (name.equals(record.get(0))) {
                    isFound = true;
                    break;
                }
                
                totalBirthHigherRank += Integer.parseInt(record.get(2));
            }
        }
        
        if (isFound) {
            return totalBirthHigherRank;
        }
        else {
            return -1;
        }

    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testGetRank() {
        int year = 2012;
        String name = "Mason";
        String gender = "M";
        System.out.println(name + " with gender " + gender + " ranked " + getRank(year, name, gender));
    }
    
    public void testGetName() {
        int year = 2013;
        int rank = 12;
        String gender = "F";
        System.out.println("Name on rank " + rank + " with gender " + gender + " is " + getName(year, rank, gender));
    }

    public void testWhatIsNameInYear() {
        String name = "Isabella";
        int year = 2012;
        int newYear = 2014;
        String gender = "F";
        System.out.println(name + " born in " + year + " would be " + whatIsNameInYear(name, year, newYear, gender) + " if was born in " + newYear);        
    }

}
