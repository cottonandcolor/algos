package hrcrackc;

/**
 * Created by predave on 5/9/17.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Anagram {
        public static int numberNeeded(String first, String second, int flen, int slen) {
            int tcount = 0;
            int  hlen = 0;
            Map<Character,Integer> charMap = new HashMap<Character, Integer>();

                hlen = flen;
                for(int i = 0 ; i < first.length() ; i++){
                    Character c = first.charAt(i);
                    Integer count = charMap.get(c);
                    if(count == null) {count = 1;}
                    else {count++;}
                    charMap.put(c, count);

                }

                for(int i = 0 ; i < slen; i++){
                    Character c = second.charAt(i);
                    Integer count = charMap.get(c);
                    if( count == null || count <= 0) {
                        tcount++;
                    }
                    else {
                        count--;
                        charMap.put(c, count);
                        hlen--;
                    }
                    if(hlen == 0){
                        tcount += slen - (i +1);
                        break;
                    }
                }



            tcount += hlen;
            //cccccccccccccccd
            //ddddddddddddddd
            return tcount;
        }

        public static void main(String[] args) {
            Stack stack = new Stack();
            stack.insertElementAt(1,1);
            Scanner in = new Scanner(System.in);
            String a = in.next();
            String b = in.next();
            int alen = a.length();
            int blen = b.length();
            if(alen < blen) {
                System.out.println(numberNeeded(a, b, alen, blen));
            }
            else {
                System.out.println(numberNeeded(b, a, blen, alen));
            }
        }


}
