package hralgos;

import java.util.Scanner;

/**
 * Created by predave on 6/5/17.
 */
public class AbsolutePermutation {
    /*
    2 1 , 2 1
    4 2    1 2 3 4
           3 4 1 2
    5 3    1 2 3 4 5
           4 5 4 3

     */
    public static String findAbsPerm(int n, int k){
        String str = "";
        if(k == 0) {
            for (int i = 1 ; i <= n; i++){
                str += i + " ";
            }
        } else if(n%(2*k) != 0) {
            return "-1";
        } else {
            for(int i = 0 ; i < n ; ++i){
                if((i / k)%2 == 0) str += (k+i+1) + " " ;
                else str += (1+i-k)  + " ";
            }
        }
        return str;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int k = in.nextInt();
            System.out.println(findAbsPerm(n,k));
        }


    }
}
