package lcodemock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by predave on 7/6/17.
 */
public class ExpressionAddOPerators {

    public static void main(String[] args){
        String s = "123";

        List<String> res = getOPerators(s, 6);
    }

    private static List<String> getOPerators(String s, int val) {
        List<String>  res = new ArrayList<>();
        if(s.length() == 0) return res;

        solve(res, s, "", 0, 0, val);
        return res;
    }

    private static void solve(List<String> res, String s, String p, int k, long cur, int target) {
        if(cur == target ) {
            res.add(p);
            return;
        }

        for(int i = k ; i < s.length(); i++){
                int c = s.charAt(i) - '0';
                if(i == 0) solve(res, s, p + s.charAt(i), i+1, c, target);
                else {
                    solve(res, s, p + "+" + s.charAt(i), i + 1, cur + c, target);
                    solve(res, s, p + "-" + s.charAt(i), i + 1, cur - c, target);
                    solve(res, s, p + "*" + s.charAt(i), i + 1, cur * c, target);
                }
        }


    }
}
