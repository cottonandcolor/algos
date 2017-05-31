package hrrookie;

import java.util.Scanner;

/**
 * Created by predave on 5/6/17.
 */
public class MaxScore4 {
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

    static long getMaxScore(long[] a){
        int n = a.length;
        int m = 1 << n ;
        long v[] = new long[m];
        long ss[] = new long[m];

       for( int i = 0 ; i < n; i ++) {
           int ml = 1 << i ;
           for (int k  = ml ; k < 2*ml ; k++ ) {
               int it = k - ml ;
               long s = a[i] + ss[it] ;
               ss[k] = s;
               long rb = v[it] + s % a[i];

               int j = 0;
               while (it > 0 ) {
                   if ((it & 1) != 0 ){
                       long r = v[(int)(k - (1L << j))] + s % a[j];
                       if (r > rb) {
                           rb = r;
                       }
                   }
                   j += 1;
                   it = it >> 1;
               }
               v[k] = rb;
           }
       }

    return v[m - 1];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] a = new long[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextLong();
        }
        long maxScore = getMaxScore(a);
        System.out.println(maxScore);
    }
}
