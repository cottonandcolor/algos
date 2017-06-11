package infinitum18;

import java.util.Scanner;

/**
 * Created by predave on 6/9/17.
 */
public class Tower3Coloring {
    private static long modulo = 1000000007;
    static long towerColoring(long n){
        // Complete this function
        long x = power(3,n,modulo);
        return power(3,x,modulo);
    }

    static long power(long x, long y, long p)
    {
        long res = 1;

        x = x % p;


        while (y > 0)
        {

            if (y%2 == 1)
                res = (res*x) % p;


            y = y>>1;
            x = (x*x) % p;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long result = towerColoring(n);
        System.out.println(result);
    }
}
