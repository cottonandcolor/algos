package lcode39;

import java.util.*;

/**
 * Created by predave on 7/2/17.
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year");
 * Year:Month:Day:Hour:Minute:Second,
 * 4,7,10,13,16
 */
public class LogSystem {
    Map<Integer,String> tmap = new HashMap<>();

    Map<String,List<Integer>> imap = new HashMap<String,List<Integer>>();
    public LogSystem() {
        imap.put("Year", Arrays.asList(4,4));
        imap.put("Month", Arrays.asList(7,7));
        imap.put("Day", Arrays.asList(10,10));
        imap.put("Hour", Arrays.asList(13,13));
        imap.put("Minute", Arrays.asList(16,16));
        imap.put("Second", Arrays.asList(19,19));
    }

    public void put(int id, String timestamp) {
        tmap.put(id,timestamp);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> idx = imap.get(gra);
        List<Integer> res = new ArrayList<Integer>();
        for(Integer id : tmap.keySet()){
            String tstamp = tmap.get(id);
             if(tstamp.substring(0,idx.get(0)).compareTo(s.substring(0,idx.get(0))) >= 0 && tstamp.substring(0,idx.get(0)).compareTo(e.substring(0,idx.get(0))) <= 0)
            {
                res.add(id);
            }
        }
        return res;
    }
    /*
    ["LogSystem","put","put","retrieve"]
[[],[1,"2017:01:01:23:59:59"],[2,"2017:01:02:23:59:59"],["2017:01:01:23:59:58","2017:01:02:23:59:58","Second"]]
     */

    public static void main(String[] args){
        LogSystem obj = new LogSystem();
        obj.put(1, "2017:01:01:23:59:59");
        obj.put(2, "2017:01:02:23:59:59");
        //obj.put(3, "2016:01:01:00:00:00");
        List<Integer> res1 = obj.retrieve("2017:01:01:23:59:58", "2017:01:02:23:59:58", "Second"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
        //List<Integer> res2 = obj.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.

    }
}

/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */