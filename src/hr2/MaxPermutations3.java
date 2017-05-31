package hr2;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by predave on 4/29/17.
 *#define MAX 1010
 #define MOD 1000000007

 long long dp[MAX][MAX];

 void generate(){
 int n, k;
 for (n = 1; n < MAX; n++){
 for (k = 1; k < n; k++){
 if (n == 1 && k == 1) dp[n][k] = 1;
 else dp[n][k] = ((dp[n - 1][k - 1] * (n - (2 * (k - 1)))) + (dp[n - 1][k] * k * 2)) % MOD;
 }
 }
 }

 int main(){
 generate();
 int t, n, k, i, j;

 scanf("%d", &t);
 assert(t <= 100);
 while (t--){
 scanf("%d %d", &n, &k);
 assert(n >= 1 && n <= 1000 && k >= 1 && k <= n);

 long long res = 0;
 for (i = 1; i <= n - k; i++) res += dp[n][i];
 printf("%lld\n", res % MOD);
 }
 return 0;
 }
 *
 */
public class MaxPermutations3 {
    static long modulo = (long)(Math.pow(10.0,9.0) + 7);
    static int MAX = 1010;
    static long dp[][] = new long[MAX][MAX];

    static void printArr(int a[], int n)
    {
        for (int i=0; i<n; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    static public void generate(){
        for (int i = 1; i < MAX; i++){
            for (int j = 1; j < MAX; j++){
                if (i == 1 && j == 1) {
                    dp[i][j] = 1;
                }
                else {
                    //dp[i][j] = ((dp[i - 1][j - 1].multiply(BigInteger.valueOf(i - (2 * (j - 1))))).add((dp[i - 1][j].multiply(BigInteger.valueOf(2 * j))))).mod(modulo);
                    dp[i][j] = ((dp[i - 1][j - 1] * (i - (2 * (j - 1)))) + (dp[i - 1][j] * j * 2)) % modulo;

                }
                    //else dp[n][k] = ((dp[n - 1][k - 1] * (n - (2 * (k - 1)))) + (dp[n - 1][k] * k * 2)) % MOD;

            }
        }
    }

    static long counter = 0;



    private static int findResult(int[] arr, int k) {
        return 0;
    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            int k = in.nextInt();
            generate();
            // Find the number of ways to arrange 'n' people such that at least 'k' of them will be happy
            // The return value must be modulo 10^9 + 7
            long result = 0;//query(n, k);
            for (int i = 1; i <= n - k; i++) result= result + (dp[n][i]);
            System.out.println(result);
        }
    }
}
