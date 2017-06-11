package infinitum18;

import java.util.Scanner;

/**
 * Created by predave on 6/10/17.
 */
public class ParityParty1 {
    static int countP(int n, int k, int a, int b, int c)
    {
        if (n == 0 || k == 0 || k > n)
            return 0;
        if (k == 1 || k == n)
            return 1;

        // S(n+1, k) = k*S(n, k) + S(n, k-1)
        return  k*countP(n-1, k, a, b , c) + countP(n-1, k-1, a , b , c);
    }
    static int countP1(int n, int k, int a, int b, int c)
    {
        // Table to store results of subproblems
        int dp[][] = new int[n+1][k+1];

        // Base cases
        for (int i=0; i<=n; i++)
            dp[i][0] = 0;
        for (int i=0; i<=k; i++)
            dp[0][k] = 0;

        // Fill rest of the entries in dp[][]
        // in bottom up manner
        for (int i=1; i<=n; i++)
            for (int j=1; j<=k; j++)
                if (j == 1 || i == j)
                    dp[i][j] = 1;
                else
                    dp[i][j] = j*dp[i-1][j] + dp[i-1][j-1];

        return dp[n][k];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int result = 0;
        int totalPeople = a + b + c;
        while(totalPeople > 0) {
            if(c != 0) {
                result += countP1(n, c, a, b, c);
            } if(a != 0) { //odd
                int numodd = n/2;
                if(n%2 != 0) {
                    numodd++;
                }
                result += countP1(numodd, a, a, b, c);

            } if(b != 0) {//even
                result += countP1(n/2, b, a, b, c);
            }
            totalPeople--;
        }
        System.out.println(result);
    }
}
