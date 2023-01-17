
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer{
     private ArrayList<LogEntry> records;
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }

     
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
            LogEntry myLog = WebLogParser.parseEntry(line);
            records.add(myLog);
         }
     }
       
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }


     public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
     }
     
     
     public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            if (statusCode > num){
                System.out.println(le);
            }
        }
     }


     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        // ArrayList of Strings of unique IP addresses that had access on the given day
        ArrayList<String> uniqIPs = new ArrayList<String>();
        // Accesses the web logs in records
        for (LogEntry le : records){
            String theDate = le.getAccessTime().toString().substring(4, 10);
            String theIP = le.getIpAddress();
            if ((!uniqIPs.contains(theIP)) && (theDate.equals(someday))){
                uniqIPs.add(theIP);
            }
        }
        return uniqIPs;
     }


     // returns the number of unique IP addresses in records that have a status code in the range from low to high, inclusive
     public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqIPs = new ArrayList<String>();
        for (LogEntry le : records){
            int statusCode = le.getStatusCode();
            String myIP = le.getIpAddress();
            if ((statusCode >= low) && (statusCode <= high) && !uniqIPs.contains(myIP)){
                uniqIPs.add(myIP);
            }
        }
        return uniqIPs.size();
     }



}
