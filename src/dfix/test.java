package dfix;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by predave on 5/25/17.
 *  "date": "2017-02-12T03:28:56",
 "id": "LC8_L1T_TOA/LC81270592017043LGN00"
 },
 {
 "date": "2017-02-28T03:28:51",
 "id": "LC8_L1T_TOA/LC81270592017059LGN00"
 },
 {
 "date": "2017-03-16T03:28:42",
 "id": "LC8_L1T_TOA/LC81270592017075LGN00"
 },
 {
 "date": "2017-04-01T03:28:34",
 "id": "LC8_L1T_TOA/LC81270592017091LGN00"
 },
 {
 "date": "2017-04-17T03:28:25",
 "id": "LC8_L1T_TOA/LC81270592017107LGN00"
 */
public class test {

    public static void main(String[] args){
        Date date1 = new Date("2016-12-10T03:29:17");
        Date date2 = new Date("2017-04-17T03:28:25");
        long count  = (date2.getTime() - date1.getTime()) /16 + 1;
        Calendar cal = Calendar.getInstance();
        //cal.setTimeInMillis(date2.getTime()+avg);


        //System.out.print(m.group(0));

    }
}
