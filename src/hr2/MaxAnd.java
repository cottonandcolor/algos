package hr2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 4/29/17.
 */
public class MaxAnd {

    static long[] solve(int n, int k, long[] a){
        // Complete this function
        long result[] = new long[2];
        long maxsum = 0;
        long count = 0;
        Set<String> valSet = new HashSet();
        // 1, 2,3,4
        //4! / 3! 1!
        //

        for(int i = 0 ; i < n ; i++){
            for(int j = i+1 ; j < n ; j++){
                int index = 0;

                long sum = 0; //n -j >= k-1 ? a[i]: 0 ;
                String valStr = "";
                if(n-j >= k-1){
                    sum = a[i];
                    valStr += a[i];
                }
                while(index < k - 1  && index + j< n) {
                    sum &= a[index+j] ;
                    valStr += a[index+j];
                    index++;
                }

                if (sum == maxsum &&  !valSet.contains(valStr)) {
                    valSet.add(valStr);
                    count++;
                } else {

                    if(sum > maxsum ){
                        count = 1;
                        maxsum = sum;
                        valSet.add(valStr);
                    }
                }

            }
        }

        result[0] = maxsum;
        result[1] = count;

        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        long[] a = new long[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextLong();
        }
        long[] result = solve(n, k, a);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != n - 1 ? "\n" : ""));
        }
        System.out.println("");


    }

}
