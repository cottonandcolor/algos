package lcode38;

/**
 * Created by predave on 6/24/17.
 */
public class NumPermutationsWithKInv2 {

    public long kInversePairs(int n, int k) {
            long[][] dp = new long[n][k+1];
            dp[0][0]=1;
            for(int i=1;i<n;i++){
                for(int j=0;j<=k;j++){
                    for(int m=j;m>=0&&m>=(j-i);m--){
                        dp[i][j]+=dp[i-1][m];
                    }
                    dp[i][j]=dp[i][j]%1000000007;
                }
            }
            return (int)dp[n-1][k];

    }




    public static void main(String[] args){
     System.out.print(new NumPermutationsWithKInv2().kInversePairs(1000,1000));
    }
}
