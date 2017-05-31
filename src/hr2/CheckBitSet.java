package hr2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 5/2/17.
 */
public class CheckBitSet {
    static BigInteger modulo = BigInteger.valueOf((long)(Math.pow(10.0,9.0) + 7));

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        List<BigInteger> vals ;
        int n = scan.nextInt();
        int k = scan.nextInt();
        vals = new ArrayList<BigInteger>();
        for(int i = 0 ; i < n ; i++){
            vals.add(BigInteger.valueOf(scan.nextLong()));
        }
        if(vals.size() == k){
            BigInteger result = vals.get(0);
            for(int i = 1 ; i < vals.size(); i++){
                result = result.and(vals.get(i));
            }
            System.out.println(result);
            System.out.println(1);
        }
        else {
            for (int b = 62; b > 0; b--) {
                List<BigInteger> maxVals = new ArrayList<BigInteger>();

                for (int i = 0; i < vals.size(); i++) {
                    if (vals.get(i).testBit(b)) {
                        maxVals.add(vals.get(i));
                    }
                }
                if (maxVals.size() >= k) {
                    vals = maxVals;
                }
            }
            BigInteger result = vals.get(0);
            for (int i = 1; i < vals.size(); i++) {
                result = result.and(vals.get(i));
            }
            System.out.println(result);
            if (vals.size() == k) {
                System.out.println(1);
            } else {
                BigInteger size = BigInteger.valueOf(vals.size());
                BigInteger j = BigInteger.valueOf(vals.size() - k);
                BigInteger out = fact(size);
                BigInteger jfact = fact(j);
                //System.out.println("outfact="+ out);
                BigInteger kfact = fact(BigInteger.valueOf(k));
                BigInteger div = jfact.multiply(kfact).mod(modulo);
                //System.out.println("outfact="+ out + "kfact=" + kfact + "div=" + div);
                out = out.divide(jfact).mod(modulo);
                out =  out.divide(kfact);
                System.out.println(out);
            }
        }
        //calculate nck
    }

    public static BigInteger fact(BigInteger n){

        if(n == BigInteger.ONE) {
            return BigInteger.ONE;
        }
        BigInteger out = n;
        for(long i = 0 ; i < n.longValue() ; i++) {
            n = n.subtract(BigInteger.ONE);
            out = out.multiply(n).mod(modulo) ;
        }
        return out ;
    }
}
