/**
 * Created by predave on 4/14/17.
 */

    import java.io.*;
    import java.util.*;
    import java.text.*;
    import java.math.*;
    import java.util.regex.*;

    public class NextPermutation {
        public static String findNext(String input){
            char[] out = input.toCharArray();
            boolean modified = false;
            int i = input.length() - 1;
            //find pivot of first non decreasing sequence
            while(i > 0 && input.charAt(i) <= input.charAt(i-1)) {
                i--;
            }
            if(i >= 1 ) {
                char temp = input.charAt(i - 1);
                for(int k = input.length() - 1 ; k > i -1 ; k--) {
                    if (temp < input.charAt(k)) {
                        out[i - 1] = input.charAt(k);
                        out[k] = temp;
                        modified = true;
                        break;
                    }
                }
            }
            for(int j = input.length() - 1  ; j > i ; j--){

                char temp = out[i];

                    out[i++] = out[j];
                    out[j] = temp;

                    modified = true;


            }
            if(modified && !String.valueOf(out).equals(input)) return  String.valueOf(out) ;

            return "no answer";
        }

        public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            for (int i = 0 ; i < n ; i++ ){
                String input = in.next();
                System.out.println(findNext(input));
            }
        }

}
