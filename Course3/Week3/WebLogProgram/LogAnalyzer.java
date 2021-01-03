
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Tim Wu 
 * @version 0.1
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<>();
    }
        
    public void readFile(String filename) {
        // complete method
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }
     
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPList = new ArrayList<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!uniqueIPList.contains(ip)) {
                uniqueIPList.add(ip);
            }
        }
     
        return uniqueIPList.size();
    }
        
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPList= new ArrayList<>();
        for (LogEntry le : records) {
            Date dd = le.getAccessTime();
            String ip = le.getIpAddress();
            String strDate = dd.toString();
            if (someday.equals(strDate.substring(4, 10))) { // Index 4 - 10 of date contains month and date
                if (!uniqueIPList.contains(ip)) {
                    uniqueIPList.add(ip);
                }
            }
        }
        
        return uniqueIPList;
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPList = new ArrayList<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                if (!uniqueIPList.contains(ip)) {
                    uniqueIPList.add(ip);
                }
            }
        }
        
        return uniqueIPList.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> countMap = new HashMap<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!countMap.containsKey(ip)) {
                countMap.put(ip, 1);
            }
            else {
                countMap.put(ip, countMap.get(ip) + 1);
            }
        }
        
        return countMap;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> countMap) {
        int countMax = 0;
        for (String ip : countMap.keySet()) {
            int count = countMap.get(ip);
            if (countMax < count) {
                countMax = count;
            }
        }
        
        return countMax;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> countMap) {
        int maxCount = mostNumberVisitsByIP(countMap);
        ArrayList<String> ipList = new ArrayList<>();
        
        for (String ip : countMap.keySet()) {
            int count = countMap.get(ip);
            if (maxCount == count && !ipList.contains(ip)) {
                ipList.add(ip);
            }
        }
        
        return ipList;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> dayIPMap = new HashMap<>();
        ArrayList<String> ipList;
        
        for (LogEntry le : records) {
            Date dd = le.getAccessTime();
            String strDate = dd.toString();
            strDate = strDate.substring(4, 10); // Only preserve the date part (in "MMM DD" format)
            String ip = le.getIpAddress();
            
            if (!dayIPMap.containsKey(strDate)) {
                ipList = new ArrayList<>();
            }
            else {
                ipList = dayIPMap.get(strDate);
            }
            
            ipList.add(ip);
            dayIPMap.put(strDate, ipList);
        }
        
        return dayIPMap;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayIPMap) {
        int maxVisit = 0;
        String maxVisitDate = "";
        
        for (String date : dayIPMap.keySet()) {
            if (dayIPMap.get(date).size() > maxVisit) {
                maxVisit = dayIPMap.get(date).size();
                maxVisitDate = date;
            }
        }
        
        return maxVisitDate;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayIPMap, String date) {
        ArrayList<String> fullIPList = dayIPMap.get(date);
        HashMap<String, Integer> countMap = new HashMap<>();
        
        for (String ip : fullIPList) {
            if (!countMap.containsKey(ip)) {
                countMap.put(ip, 1);
            }
            else {
                countMap.put(ip, countMap.get(ip) + 1);
            }
        }
        
        return iPsMostVisits(countMap);
    }
}
