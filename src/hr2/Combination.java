package hr2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by predave on 5/1/17.
 */
public class Combination {

    static int[] input ;
    static int k ;
    static List<List<Integer>> output =  new ArrayList<List<Integer>>();  // 3 = n!/r! * n-r!
    static int numcombinations ;

    private static void combinations(int start, List<Integer> arr) {

        if(output.size() == numcombinations) {
            return;
        }

        if(arr.size() == k) {
            output.add(new ArrayList<Integer>(arr));
            return;
        }
        int prev = -1;
        for(int i = start ; i < input.length ; i++  ) {
            if(prev != input[i]) {
                arr.add(Integer.valueOf(input[i]));
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

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        k = scanner.nextInt();
        input = new int[n];
        for( int i = 0 ; i < n ; i++){
            input[i] = scanner.nextInt();
        }
        if(n == k){
            long sum = -1;
            for(int i = 0 ; i < n ; i++){
                if(sum == -1){
                    sum = input[i];
                }
                else {
                    sum = sum & input[i];
                }
            }

        }
        else {
            numcombinations = fact(n) / fact(n - k) * fact(k);
            List<Integer> arr = new ArrayList<Integer>();

            combinations(0, arr);

            for (List<Integer> oList : output) {
                System.out.println(oList);
                System.out.println("----");
            }
        }
    }
}
