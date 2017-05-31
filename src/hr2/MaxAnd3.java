package hr2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 4/29/17.
 */
public class MaxAnd3 {


    static long[] input ;
    static int k ;
    static List<List<Long>> output =  new ArrayList<List<Long>>();  // 3 = n!/r! * n-r!
    static int numcombinations ;
    static long modulo = (long)(Math.pow(10.0,9.0) + 7);


    private static void combinations(int start, List<Long> arr) {

        //if(output.size() == numcombinations) {
       //     return;
       // }

        if(arr.size() == k) {
            output.add(new ArrayList<Long>(arr));
            return;
        }
        long prev = -1;
        for(int i = start ; i < input.length ; i++  ) {
            if(prev != input[i]) {
                arr.add(Long.valueOf(input[i]));
                combinations(i + 1, arr);
                arr.remove(arr.size() - 1);
                prev = input[i];
            }
        }
    }

    public static int fact(int n){
        if(n == 1) {
            return 1;
        }
        return n * fact(n-1);
    }


    static long[] solve(int n, int k, long[] a){
        // Complete this function
        long result[] = new long[2];
        long maxsum = -1;
        long count = 0;
        Set<String> valSet = new HashSet();

        for(List<Long> outList : output){
            long sum = -1;
            for(Long out : outList){
                if(sum == -1){
                    sum = out;
                } else {
                    sum = (sum & out)% (modulo);
                }
            }
            if(maxsum == -1){
                maxsum = sum;
                count = 1;
            }
            else if(sum == maxsum){
                count++;
            }
            else {
                if(sum > maxsum){
                    maxsum = sum ;
                    count=1;
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
        k = in.nextInt();
        input = new long[n];
        for(int a_i=0; a_i < n; a_i++){
            input[a_i] = in.nextLong()% (modulo);
        }

        long[] result;
        if(n == k){
            result = new long[2];
            long sum = -1;
            for(int i = 0 ; i < n ; i++){
                if(sum == -1){
                    sum = input[i];
                }
                else {
                    sum = (sum & input[i])% (modulo);
                }
            }
            result[0] = sum;
            result[1] = 1;

        }
        else {
            //numcombinations = fact(n) / fact(n - k) * fact(k);
            List<Long> arr = new ArrayList<Long>();

            combinations(0, arr);


            result = solve(n, k, input);
        }
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != n - 1 ? "\n" : ""));
        }
        System.out.println("");


    }

}
