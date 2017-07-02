package lcode39;

import java.math.BigInteger;

/**
 * Created by predave on 7/1/17.
 */
public class FindDerangement {
    public static int findDerangement1(int n) {
        int modulo = 1000000007;
        int der[] = new int [n + 1];
        if(n == 0) return 1;
        if(n == 1) return 0;
        if(n == 2) return 1;

        der[0] = 1;
        der[1] = 0;
        der[2] = 1;
        //BigInteger m = BigInteger.valueOf(1000000007);
        for (int i=3; i<=n; ++i) {
            int a = der[i-1] %modulo;
            int b = der[i-2] % modulo;
            int c = (a + b) %modulo ;
           /* BigInteger b = BigInteger.valueOf(der[i-2]).mod(m);
            int c = (der[i - 1] + der[i - 2]) % modulo;
            BigInteger d = BigInteger.valueOf(i-1);
            c = c.multiply(d).mod(m);*/

            der[i] = (((i - 1)%modulo) * (c%modulo)) %modulo ;
        }
        //BigInteger r = BigInteger.valueOf(der[n]);
        int c = 14684570 % modulo + 176214841% modulo;
        long d = (12 * c ) %modulo;
        return der[n];
    }

    public static int findDerangement(int n) {
        long der[] = new long[n + 1];
        if(n == 0) return 0;
        if(n == 1) return 0;
        if(n == 2) return 1;

        der[0] = 1;
        der[1] = 0;
        der[2] = 1;

    /*if (n<2) return 0;
        long f[]=new long[n+1];
        f[1]=0;f[2]=1;
        for (int i=3;i<=n;i++) f[i]=(f[i-1]+f[i-2])*(i-1)%1000000007;
        return (int)f[n];*/

        for (int i=3; i<=n; ++i)
            der[i] = (der[i-1] + der[i-2])*(i-1)%1000000007;

        return (int)der[n];
    }

    public static void main(String[] args){
        System.out.print(findDerangement(13));
    }
}
