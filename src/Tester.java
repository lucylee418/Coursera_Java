import java.util.*;
// import javax.print.event.PrintJobAdapter;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // Create a LogAnalyzer object
        LogAnalyzer la = new LogAnalyzer();
        // call readFile on the data file short-test_log
        la.readFile("short-test_log");
        // call printAll to print all the web logs.
        la.printAll();
    }

    public void testUniqIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are "+ uniqueIPs+ " IPs");
    }


    public void testtHigherThanNum(int num){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(num);
    }


    public void testuUiqueIPVisitsOnDay(String someday){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        ArrayList<String> visitArray = la.uniqueIPVisitsOnDay(someday);
        System.out.println("Size: "+visitArray.size());
    }


    public void testCountUniqueIPsInRange(int low, int high){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int myCounts = la.countUniqueIPsInRange(low, high);
        System.out.println("Counts: "+myCounts);
    }


    public static void main (String[] args){
        Tester tt = new Tester();
        // tt.testLogEntry();
        // tt.testLogAnalyzer();
        // tt.testUniqIP();
        // tt.testtHigherThanNum(400);
        // tt.testuUiqueIPVisitsOnDay("Mar 17");
        tt.testCountUniqueIPsInRange(200, 299);
    }




}
