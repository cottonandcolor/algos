package hrdynamic;

import java.util.Arrays;

/**
 * Created by predave on 6/26/17.
 */
public class Equal {

    public static long getNumOper(long delta){
        long noper = 0;
        if(delta >= 5){
            noper = delta/5;
            delta = delta %5;
        }
        if(delta >= 2){
            noper += delta/2;
            delta = delta%2;
        }
        noper += delta;
        return noper;
    }

    public static void main(String[] args){
        long min = Long.MAX_VALUE;
        int[] a = { 2,2,4,7};
        Arrays.sort(a);
        int m = a[0];
        long totaloper = 0;
        long sum[] = new long[6];
        for(int j=0; j <= 5; j++) {
            sum[j] = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] > m) {
                    //long noper = getNumOper(a[i] - m);
                    long temp = a[i] - (m - j);
                    sum[j] = sum[j] + getNumOper(temp);
                }
            }
        }

        for(int i = 0; i <= 5; i++){
            min = Math.min(min,sum[i]);
        }
        System.out.println(min);
    }
}
