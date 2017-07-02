package lcode38;

/**
 * Created by predave on 6/24/17.
 */
public class NumPermutationsWithKInv {
     int M = 10001;

    long[][] memo = new long[M][M];
    long modulo = 1000000007;
    public long kInversePairs(int n, int k) {
     return numberOfPermWithKInversion(n,k,memo);
    }
      long numberOfPermWithKInversion(int N, int K, long[][] memo)
    {
        if (N == 0)
            return 0;
        if (K == 0)
            return 1;

        if (memo[N][K] != 0)
            return memo[N][K];

        long sum = 0;
        for (int i = 0; i <= K; i++)
        {
            if (i <= N - 1)
                sum = sum % 1000000007 + numberOfPermWithKInversion(N-1, K-i, memo) % 1000000007;
        }

        memo[N][K] = sum;

        return sum;
    }



    public static void main(String[] args){
     System.out.print(new NumPermutationsWithKInv().kInversePairs(4,5));
    }
}
