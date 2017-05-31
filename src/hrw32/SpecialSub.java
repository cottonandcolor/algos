package hrw32;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 5/20/17.
 */
public class SpecialSub {

    static List<Integer> countList = new ArrayList<Integer>() ;
    static Set<String> t = new HashSet<String>();
    static Set<String> G = new HashSet<String>();


    static Integer[] propertyOfString(String s){
        // Complete this function

        int index = 0;

        char c = s.charAt(0);
        t.add(String.valueOf(c));
        G.add(String.valueOf(c));
        countList.add(new Integer(1));

        for(int i = 1 ; i < s.length(); i++){
            Set<String> newStrings = new HashSet<String>();
            char ci = s.charAt(i);
            if(!t.contains(String.valueOf(ci))) {
                t.add(String.valueOf(ci));
                newStrings.add(String.valueOf(ci));
            }
            int prev = i - 1;
            //while(prev >= 0) {
                if (ci == s.charAt(prev)) {
                    String p = "" + ci + ci;
                    if (!t.contains(p)) {
                        t.add(p);
                        newStrings.add(p);
                    }
                }
                for(int j = 0 ; j < i -1 ; j++) {
                    String palin = getPalindrome(s.substring(j, i + 1));
                    if (palin != null && !palin.isEmpty()) {
                        if (!t.contains(palin)) {
                            t.add(palin);
                            newStrings.add(palin);
                        }
                    }
                }

                //prev--;
            //}

            addCountList(countList.get(countList.size() - 1) , newStrings);
        }

        Integer[] ret = new Integer[countList.size()];
        countList.toArray(ret);
        return ret;
    }

    private static String getPalindrome(String s) {
        StringBuilder ret = new StringBuilder("") ;
        int len = s.length();
        int start = 0;
        int end = len - 1;
        while (start < end){
                if(s.charAt(start) != s.charAt(end)){
                   return null;
                }
                start++;
                end--;
        }
        return s;
    }

    private static void addCountList(Integer prevCount, Set<String> newStrings) {
        for(String s: newStrings){
            String o = "";
            for(int i = 0; i < s.length() ; i++){
                o = o + s.charAt(i);
                if(!G.contains(o)) {
                    G.add(o);
                    prevCount++;
                }
            }
        }
        countList.add(prevCount);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        Integer[] result = propertyOfString(s);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
        }
        System.out.println("");


    }
}
