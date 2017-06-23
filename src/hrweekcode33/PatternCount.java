package hrweekcode33;

import java.util.Scanner;

/**
 * Created by predave on 6/13/17.
 */
public class PatternCount {
    static int patternCount(String s){
        // Complete this function
        int len = s.length();
        if(len <= 2) return 0;
        int firstone = 0;
        int zeros =0;
        int lastone = 0;
        int count = 0;

        for(int i =0 ; i < len; i++){
            if(s.charAt(i) == '0' && firstone != 0){
                zeros++;
            } else if(s.charAt(i) == '1' ){
               if(firstone == 0 ) firstone ++;
                else if(lastone == 0 && zeros != 0) lastone ++;

            } else {
                firstone=0;
                lastone=0;
                zeros=0;
            }
            if(firstone == 1 && lastone ==1 && zeros != 0){
                count++;
                lastone=0;
                zeros=0;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = patternCount(s);
            System.out.println(result);
        }
    }
}
