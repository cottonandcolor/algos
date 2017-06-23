package hr101hack50;

import java.util.*;

/**
 * Created by predave on 6/20/17.
 */
public class HardQuestions {
    static int maxScoreOfVincent(int n, String s, String t) {
        // Complete this function
        if(s == null || t == null) return 0;
        int v = 0;
        int c = 0;
        for(int i = 0; i < n; i++){
            if(s.charAt(i) =='.') continue;
            if(s.charAt(i) != t.charAt(i)) v++;
        }
        return v;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //  Return the maximum score of Vincent.
        int n = in.nextInt();
        String s = in.next();
        String t = in.next();
        int result = maxScoreOfVincent(n, s, t);
        System.out.println(result);
    }
}
