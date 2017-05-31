package hrrookie;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by predave on 5/6/17.
 */

/**
 * #!/bin/python

 def getMaxScore(a):
 n = len(a)
 m = 1 << n
 v = [0] * m
 ss = [0] * (m // 1)
 for l in range(n):
 ml = 1 << l
 for i in range(ml, ml + ml):
 it = i - ml
 s = a[l] + ss[it]
 ss[i] = s
 rb = v[it] + s % a[l]

 j = 0
 while it > 0:
 if (it & 1) != 0:
 r = v[i - (1L << j)] + s % a[j]
 if (r > rb): rb = r;

 j += 1
 it = it >> 1
 v[i] = rb

 return v[m - 1]

 n = int(raw_input().strip())
 a = map(long, raw_input().strip().split(' '))
 maxScore = getMaxScore(a)
 print(maxScore)
 */
public class MaxScore3 {
    static long rsum = 0;
    static long score = 0;
    static long modulo = (long) (Math.pow(10, 9) + 7);
    static long dp[];
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

    static void zigZag(long arr[], int n) {
        // Flag true indicates relation "<" is expected,
        // else ">" is expected.  The first expected relation
        // is "<"
        boolean flag = true;


        for (int i = 0; i <= n - 2; i++) {
            if (flag)  /* "<" relation expected */ {
            /* If we have a situation like A > B > C,
               we get A > B < C by swapping B and C */
                if (arr[i] > arr[i + 1])
                    swap(arr,i, i + 1);
            } else /* ">" relation expected */ {
            /* If we have a situation like A < B < C,
               we get A < C > B by swapping B and C */
                if (arr[i] < arr[i + 1])
                    swap(arr,i, i + 1);
            }
            flag = !flag; /* flip flag */
        }
    }

    private static void swap(long[] a, int i, int i1) {
        long tmp = a[i];
        a[i] = a[i1];
        a[i1] = tmp;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] a = new long[n];
        dp = new long[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextLong();
        }
        Arrays.sort(a);
        zigZag(a, a.length);
        long maxScore = getMaxScore(a, n-1);
        System.out.println(maxScore);
    }
}
