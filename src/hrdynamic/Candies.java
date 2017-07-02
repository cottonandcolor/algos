package hrdynamic;

import java.util.Scanner;

/**
 * Created by predave on 6/28/17.
 */
public class Candies {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }
        long[] dp = new long[n];
        long max = 0;
        if(n == 1){
            System.out.println("1");
            return ;
        }

        dp[0] = (a[0] > a[1]) ?  2 :  1;
        max = dp[0];
        for(int i =1; i < n; i++){
            if(( a[i] > a[i-1]) )dp[i] = dp[i-1] +1;
            else if(  i < n-1 && a[i] > a[i+1] ) dp[i] = 2;
            else dp[i] = 1;
            max += dp[i];
        }
        for(int i = n-1; i > 0; i--){
            if(a[i] < a[i-1] && dp[i-1] <= dp[i]) {
                max+= (dp[i] - dp[i-1]) + 1;
                dp[i-1] = dp[i] + 1;

            }
        }
        System.out.println(max);
    }
}
