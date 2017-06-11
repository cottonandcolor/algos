package infinitum18;

import java.util.Scanner;

/**
 * Created by predave on 6/9/17.
 */
public class PythagoreanTriple {

    static long[] pythagoreanTriple(long a){
        // Complete this function
        long[] out = new long[3];
        out[0] = a;
        if(a%2 != 0) {
            out[1] = ((a*a) - 1)/2;
            out[2] = out[1] + 1 ;
        } else {
            long m = a/2;

                for(int n = 1; n< m; n=n*2) {

                    long x = m * m - n * n;
                    long y = a;
                    long z = m * m + n * n;
                    if (x * x + y * y == z * z) {
                        out[1] = x;
                        out[2] = z;
                        break;
                    }
                    m= m/2;
                }


        }
        return out;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        long[] triple = pythagoreanTriple(a);
        for (int i = 0; i < triple.length; i++) {
            System.out.print(triple[i] + (i != triple.length - 1 ? " " : ""));
        }
        System.out.println("");


    }

}
