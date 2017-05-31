package wsprint11;

import java.util.Scanner;

/**
 * Created by predave on 5/26/17.
 */
public class balancedArray {
    static long solve(int[] a){
        // Complete this function
        long leftSum = 0;
        long rightSum = 0;
        int len = a.length;
        for(int i = 0 ; i < len/2; i++){
            leftSum += a[i];
            rightSum += a[len - i -1];
        }
        return Math.abs(leftSum - rightSum);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        long result = solve(a);
        System.out.println(result);
    }
}
