package hrw32;

import java.util.Scanner;

/**
 * Created by predave on 5/16/17.
 */
public class duplication {
    static String str;
    static String duplication(int x){
        return String.valueOf(str.charAt(x));
    }

    static String preprocess(String s){
        if(s.length() > 1000) {
            str = s;
            return s;
        }
        char[] c = s.toCharArray();
        for(int i = 0 ; i < s.length(); i++){
            if(c[i] == '0'){
                c[i] = '1';
            }else if(c[i] == '1'){
                c[i] = '0';
            }
        }
        String newStr = s + String.valueOf(c);
        preprocess(newStr);
        return newStr;
    }

    public static void main(String[] args) {
        preprocess("0");
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int x = in.nextInt();
            String result = duplication(x);
            System.out.println(result);
        }
    }

}
