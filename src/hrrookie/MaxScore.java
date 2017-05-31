package hrrookie;

import java.util.Scanner;

/**
 * Created by predave on 5/6/17.
 */
public class MaxScore {
    static long rsum = 0;
    static long score = 0;
    static long modulo = (long) (Math.pow(10, 9) + 7);
    static long getMaxScore(long[] a, int index){
        // Complete this function
        if(index < 0){
            return score;
        } else {
            score += (rsum%modulo) % (a[index]%modulo);
            rsum = rsum%modulo + a[index] % modulo;
        }
        index = index  -1;
        return getMaxScore(a, index) % modulo;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] a = new long[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextLong();
        }
        long maxScore = getMaxScore(a, n-1);
        System.out.println(maxScore);
    }
}
