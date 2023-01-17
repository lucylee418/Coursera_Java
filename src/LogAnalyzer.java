
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


     public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le : records){
            String ip = le.getIpAddress();
            if (! counts.containsKey(ip)){
                counts.put(ip, 1);
            }
            else{
                counts.put(ip, counts.get(ip)+1);
            }
        }
        return counts;
     }


     public int mostNumberVisitsByIP(HashMap<String,Integer> myHash){
        int maxSoFar = 0;
        for (String k : myHash.keySet()){
            if (maxSoFar < myHash.get(k)){
                maxSoFar = myHash.get(k);
            }
        }
        return maxSoFar;
     }


     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> myHash){
        ArrayList<String> myArray = new ArrayList<String>();
        int maxOccur = mostNumberVisitsByIP(myHash);
        for (String k : myHash.keySet()){
            if (maxOccur == myHash.get(k)){
                myArray.add(k);
            }
        }
        return myArray;
     }


     public HashMap<String,Integer> iPsForDays(){
        HashMap<String,Integer> myHash = new HashMap<String,Integer>();
        for (LogEntry le : records) {
            String theDate = le.getAccessTime().toString().substring(4, 10);
            if (!myHash.containsKey(theDate)){
                myHash.put(theDate, 1);
            }
            else{
                myHash.put(theDate, myHash.get(theDate)+1);
            }
        }
        return myHash;
     }

     public String dayWithMostIPVisits(){
        // Uses records and maps days from web logs to an ArrayList of IP addresses that occurred on that day.
        HashMap<String, ArrayList<String>> myHash = new HashMap<String, ArrayList<String>>();  
        for (LogEntry le : records){
            ArrayList<String> inArray = new ArrayList<String>();
            String theDate = le.getAccessTime().toString().substring(4, 10);
            String myIP = le.getIpAddress();
            if (!myHash.containsKey(theDate)){
                inArray.add(myIP);
                myHash.put(theDate, inArray);
            }
            else{
                inArray = myHash.get(theDate);
                inArray.add(myIP);
                myHash.put(theDate, inArray);
            }
        }

        String theDate = "";
        int maxSoFar = 0;
        for (String k : myHash.keySet()){
            if (maxSoFar < myHash.get(k).size()){
                maxSoFar = myHash.get(k).size();
                theDate = k;
            }
        }
        return theDate;
     }


     public ArrayList<String> iPsWithMostVisitsOnDay(String myDate){
        HashMap<String, ArrayList<String>> myHash = new HashMap<String, ArrayList<String>>();  
        for (LogEntry le : records){
            ArrayList<String> inArray = new ArrayList<String>();
            String theDate = le.getAccessTime().toString().substring(4, 10);
            String myIP = le.getIpAddress();
            if (!myHash.containsKey(theDate)){
                inArray.add(myIP);
                myHash.put(theDate, inArray);
            }
            else{
                inArray = myHash.get(theDate);
                inArray.add(myIP);
                myHash.put(theDate, inArray);
            }
        }

        HashMap<String,Integer> freqIps = new HashMap<String,Integer>();
        for (String d : myHash.get(myDate)){
            if (!freqIps.containsKey(d)){
                freqIps.put(d, 1);
            }
            else{
                freqIps.put(d, freqIps.get(d)+1);
            }
        }

        int maxSoFar = 0;
        for (String i : freqIps.keySet()){
            if (maxSoFar < freqIps.get(i)){
                maxSoFar = freqIps.get(i);
            }
        }

        ArrayList<String> finalArray = new ArrayList<String>();
        for (String i : freqIps.keySet()){
            if (freqIps.get(i) == maxSoFar){
                finalArray.add(i);
            }
        }
        return finalArray;
     }



}
