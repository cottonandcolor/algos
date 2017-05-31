package wsprint11;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by predave on 5/26/17.
 * 1 1 1 1 0 1 == 61
 *
 * 0 14 13
 4 2 15   27

b to the power k -1

 */
public class NumericString {

    static Map<String, Long> valMap = new HashMap<String, Long>();
    static long count = 0;
    static long getMagicNumber(String s, int k, int b, int m){
        // Complete this function
        for(int i = 0 ; i <= s.length() - k ; i++){
            splitAndConvert(s, i, i+k , b, m);
        }
        return count;

    }

    static void splitAndConvert(String s , int i , int j , int b , int m) {
        String str = s.substring(i,j);
        Long val = valMap.get(str);
        if(val != null ){
            count += val;
        } else {
            convertToBase10Modm(str, b, m);
        }
    }

    static void convertToBase10Modm(String input , int b, int m){
        long val = 0;
        int len = input.length() - 1;
        for(int i = len  ; i >= 0; i--) {
            val += (input.charAt(i) - '0') * (Math.pow(b , len - i )) ;
        }
        long tmp = val % m;
        count += tmp;
        valMap.put(input, tmp);

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int k = in.nextInt();
        int b = in.nextInt();
        int m = in.nextInt();
        long result = getMagicNumber(s, k, b, m);
        System.out.println(result);
    }
}
