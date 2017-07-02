package hrhour22;

import java.util.Scanner;

/**
 * Created by predave on 7/2/17.
 */
public class prog1 {
    static int smallestSizeSubsequence(int n, int[] A) {
        // Return the size of the smallest subsequence to remove

        int sum = 0;
        if(n == 1) {
            if(A[0]%2 == 0) return 0;
            else return -1;
        } else if( n==2 ){
            if((A[0] + A[1])%2 == 0) return 0;
            else return 1;
        } else{

            for(int i = 0; i < n; i++){
                sum = sum + A[i];
            }
        }
        if(sum % 2 == 0) return 0;
        else return 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for(int A_i = 0; A_i < n; A_i++){
            A[A_i] = in.nextInt();
        }
        int result = smallestSizeSubsequence(n, A);
        System.out.println(result);
    }
}
