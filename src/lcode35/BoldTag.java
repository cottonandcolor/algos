package lcode35;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by predave on 6/10/17.
 */
public class BoldTag {
    public String addBoldTag(String inp, String[] dict) {
        if(inp == null) return null;
        String s = new String(inp);
        int dlen = 0;
        while(dlen  < dict.length){
            String[] res = s.split(dict[dlen]);
            String out = new String();

            if(res.length > 1  || res[0].length() != s.length()) {
                for (int j = 0; j < res.length; j++) {
                    out = out + res[j] + "<b>" + dict[dlen] + "</b>" ;
                }

            }
            if(!out.isEmpty())
                s = out;
            dlen++;

        }

        return s;
    }


    public static void main(String[] args){
        BoldTag bt = new BoldTag();
        String s = "tabcxyz123123abc123";
        String[] dict = {"abc","123"};
        System.out.println(bt.addBoldTag(s,dict));
    }
}
