package lcode39;

import java.math.BigInteger;

/**
 * Created by predave on 7/1/17.
 */
public class SumOfSquares {
    public static int findDerangement(int n) {
        int modulo = 1000000007;
        long der[] = new long [n + 1];
        if(n == 0) return 1;
        if(n == 1) return 0;
        if(n == 2) return 1;

        der[0] = 1;
        der[1] = 0;
        der[2] = 1;
        BigInteger m = BigInteger.valueOf(1000000007);
        for (int i=3; i<=n; ++i) {
            BigInteger a = BigInteger.valueOf(der[i-1]).mod(m);
            BigInteger b = BigInteger.valueOf(der[i-2]).mod(m);
            BigInteger c = a.add(b).mod(m);
            BigInteger d = BigInteger.valueOf(i-1);
            c = c.multiply(d).mod(m);

            der[i] = c.longValue() ;// ((i - 1) * ((der[i - 1] + der[i - 2]))) % modulo;
        }
        BigInteger r = BigInteger.valueOf(der[n]);
        return r.intValue();
    }

    public static void main(String[] args){
     System.out.print(findDerangement(13));
    }
}
