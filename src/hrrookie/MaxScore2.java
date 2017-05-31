package hrrookie;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by predave on 5/6/17.
 */
public class MaxScore2 {
    static BigInteger rsum = BigInteger.ZERO;
    static BigInteger score = BigInteger.ZERO;
    static long modulo = (long) (Math.pow(10, 9) + 7);
    static long getMaxScore(long[] a, int index){
        // Complete this function
        if(index < 0){
            return score.longValue();
        } else {
            score = score.add(rsum.mod(BigInteger.valueOf(a[index])));

            rsum = rsum.add(BigInteger.valueOf(a[index]));
        }
        index = index  -1;
        return Math.max(getMaxScore(a, index), getMaxScore(a, index));
    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] a = new long[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextLong();
            //System.out.println((a[a_i] + a[a_i])%modulo);
        }
        long maxScore = getMaxScore(a, n-1);
        System.out.println(maxScore);
    }
}
