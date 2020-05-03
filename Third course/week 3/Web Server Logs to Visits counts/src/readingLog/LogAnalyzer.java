package readingLog;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {

    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename) {
        // complete method
        FileResource resource = new FileResource("test/" + filename);
        for (String line : resource.lines()) {
            LogEntry log = WeblogParser.parseEntry(line);
            records.add(log);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry log : records) {
            String ipAddresse = log.getIpAddress();
            if (!uniqueIPs.contains(ipAddresse)) {
                uniqueIPs.add(ipAddresse);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry log : records) {
            int status = log.getStatusCode();
            if (status > num) {
                System.out.println(log);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPsVisitsOnDay = new ArrayList<String>();
        for (LogEntry log : records) {
            String date = log.getAccessTime().toString();
            String ipAddresse = log.getIpAddress();
            if (date.contains(someday) && !uniqueIPsVisitsOnDay.contains(ipAddresse)) {
                uniqueIPsVisitsOnDay.add(ipAddresse);
            }
        }
        return uniqueIPsVisitsOnDay;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
        int total = 0;
        for (LogEntry log : records) {
            String ipAddresse = log.getIpAddress();
            int status = log.getStatusCode();
            if (status >= low && status <= high && !uniqueIPsInRange.contains(ipAddresse)) {
                total += 1;
                uniqueIPsInRange.add(ipAddresse);
            }
        }
        return total;
    }

    // The number of times this IP address visited the website
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry log : records) {
            String ip = log.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    // The maximum number of visits to this website by a single IP address.
    public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int max = 0;
        for (String w : map.keySet()) {
            int curr = map.get(w);
            if (curr > max) {
                max = curr;
            }
        }
        return max;
    }

    // An ArrayList of Strings of IP addresses that all have the maximum number of
    // visits to this website.
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        ArrayList<String> MostVisit = new ArrayList<String>();
        int max = mostNumberVisitsByIP(map);
        for (String w : map.keySet()) {
            int curr = map.get(w);
            if (curr == max) {
                MostVisit.add(w);
            }
        }
        return MostVisit;
    }

    // A HashMap<String, ArrayList<String>> that uses records and maps days from web
    // logs to an ArrayList of IP addresses that occurred on that day (including
    // repeated IP addresses)
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipsForDays = new HashMap<String, ArrayList<String>>();

        for (LogEntry log : records) {
            String date = log.getAccessTime().toString().substring(4, 10);
            if (!ipsForDays.containsKey(date)) {
                ArrayList<String> eachday = new ArrayList<String>();
                eachday.add(log.getIpAddress());
                ipsForDays.put(date, eachday);
            } else {
                ipsForDays.get(date).add(log.getIpAddress());
            }
        }
        return ipsForDays;
    }

    // The day that has the most IP address visits
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsForDays) {
        int max = 0;
        String maxDate = null;
        for (String ip : ipsForDays.keySet()) {
            if (ipsForDays.get(ip).size() > max) {
                max = ipsForDays.get(ip).size();
                maxDate = ip;
            }
        }
        return maxDate;
    }

    // An ArrayList<String> of IP addresses that had the most accesses on the given
    // day.
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsForDays, String date) {
        ArrayList<String> iPsWithMostVisitsOnDay = ipsForDays.get(date);
        HashMap<String, Integer> counts = new HashMap<>();
        for (String ip : iPsWithMostVisitsOnDay) {
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return iPsMostVisits(counts);

    }

}