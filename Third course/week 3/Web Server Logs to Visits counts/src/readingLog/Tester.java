package readingLog;

import java.util.*;

public class Tester {

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer l1 = new LogAnalyzer();
        l1.readFile("short-test_log");
        l1.printAll();
    }

    public void testUniqIP() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        int uniqueIPs = log.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog1_log");
        log.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        ArrayList<String> uniqueIPVisitsOnDay = new ArrayList<String>();
        uniqueIPVisitsOnDay.clear();
        uniqueIPVisitsOnDay = log.uniqueIPVisitsOnDay("Sep 27");
        System.out.println(uniqueIPVisitsOnDay.size());
    }

    public void testcountUniqueIPsInRange() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        int count = log.countUniqueIPsInRange(200, 299);
        System.out.println(count);
    }

    public void testCounts() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog3-short_log");
        HashMap<String, Integer> counts = log.countVisitsPerIP();
        System.out.println(counts);
        System.out.println(log.mostNumberVisitsByIP(counts));
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        HashMap<String, Integer> counts = log.countVisitsPerIP();
        System.out.println(log.mostNumberVisitsByIP(counts));
    }

    public void testIPsMostVisits() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        HashMap<String, Integer> counts = log.countVisitsPerIP();
        ArrayList<String> ipsMostVisits = log.iPsMostVisits(counts);
        for (String ip : ipsMostVisits) {
            System.out.println(ip);
        }
    }

    public void testIPsForDays() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipsForDays = log.iPsForDays();
        for (String ip : ipsForDays.keySet()) {
            System.out.println(ip + " has this day " + ipsForDays.get(ip).size());
        }
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipsForDays = log.iPsForDays();
        String date = log.dayWithMostIPVisits(ipsForDays);
        System.out.println(date);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipsForDays = log.iPsForDays();
        ArrayList<String> iPsWithMostVisitsOnDay = log.iPsWithMostVisitsOnDay(ipsForDays, "Sep 30");
        for (String ip : iPsWithMostVisitsOnDay) {
            System.out.println(ip);
        }
    }
}