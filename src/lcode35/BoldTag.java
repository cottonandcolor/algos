package lcode35;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by predave on 6/10/17.
 * "aaabbcc"
 ["aaa","aab","bc"]
 Output:
 "<b>aaa</b>b<b>bc</b>c"
 Expected:
 "<b>aaabbc</b>c"
 */
public class BoldTag {
    public String addBoldTag(String s, String[] dict) {
        boolean bold[] = new boolean[s.length()];


        if(s == null) return null;
        int dlen = 0;
        while(dlen  < dict.length) {
            String pat = dict[dlen];
            List<Integer> idx = KMPSearch(pat, s);
            for(Integer i : idx){
                int j = i;
                for(int m = 0 ; m < pat.length(); m++){
                    bold[j] = true;
                    j++;
                }
            }
            dlen++;
        }
        String out = "";
        int i = 0;
        while(i < s.length()){
            if(!bold[i]) {
                out = out + s.charAt(i);
            } else {
                out = out + "<b>" + s.charAt(i);
                i++;
                while(i < s.length() && bold[i]) {
                    out = out + s.charAt(i);
                    i++;
                }
                if(i < s.length()) {
                    out = out + "</b>" + s.charAt(i);
                } else {
                    out = out + "</b>" ;
                }
            }
            i++;
        }
        return out;
    }


    List<Integer> KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        List<Integer> indexes = new ArrayList<>();

        int lps[] = new int[M];
        int j = 0;

        computeLPSArray(pat, M, lps);

        int i = 0;
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                indexes.add((i-j));
                j = lps[j - 1];
            }

            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return indexes;
    }

    void computeLPSArray(String pat, int M, int lps[])
    {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < M)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            else
            {
                if (len != 0)
                {
                    len = lps[len-1];
                }
                else
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
    public static void main(String[] args){
        BoldTag bt = new BoldTag();
        String s = "aaabbcc";
        String[] dict = {"aaa","aab", "bc"};
        System.out.println(bt.addBoldTag(s,dict));
    }
}
