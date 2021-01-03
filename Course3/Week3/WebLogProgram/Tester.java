
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog2_log");
        System.out.println("There are " + la.countUniqueIPs() + " different IPs.");
    }
    
    public void testPrintAllHigherThanNum(int num) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog1_log");
        System.out.println("The following log entry are with status code > " + num + ":");
        la.printAllHigherThanNum(num);
    }
    
    public void testUniqueIPVisitsOnDay(String date) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog2_log");
        ArrayList<String> ipList = la.uniqueIPVisitsOnDay(date);
        // System.out.println(ipList);
        
        for (String ip : ipList) {
            System.out.println(ip);
        }
        
        System.out.println("Total IP number: " + ipList.size());
    }
    
    public void testCountUniqueIPsInRange(int low, int high) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog2_log");
        
        System.out.println("Number of log entry between " + low + " and " + high + " is " + la.countUniqueIPsInRange(low, high));
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog2_log");
        HashMap<String, Integer> countMap = la.countVisitsPerIP();
        
        System.out.println("Most number of visit: " + la.mostNumberVisitsByIP(countMap));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog2_log");
        HashMap<String, Integer> countMap = la.countVisitsPerIP();
        int maxCount = la.mostNumberVisitsByIP(countMap);
        System.out.println("Most number of visit: " + maxCount);
        System.out.println("Following IP address has most visit: \n" + la.iPsMostVisits(countMap));
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog1_log");
        HashMap<String, ArrayList<String>> dayIPMap = la.iPsForDays();
        System.out.println(dayIPMap);
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog2_log");
        HashMap<String, ArrayList<String>> dayIPMap = la.iPsForDays();
        String dayMostVisit = la.dayWithMostIPVisits(dayIPMap);
        System.out.println("Date with most visit: " + dayMostVisit);
    }
    
    public void testIPsWithMostVisitsOnDay(String date) {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("CountingVisitsData/weblog2_log");
        HashMap<String, ArrayList<String>> dayIPMap = la.iPsForDays();
        ArrayList<String> ipMostVisit = la.iPsWithMostVisitsOnDay(dayIPMap, date);
        System.out.println("IPs with most visit: " + ipMostVisit);
    }
}
