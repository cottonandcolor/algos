package infinitum18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by predave on 6/10/17.
 * 18 , 39 , 4
 * 3
 2 0 1
 2 0 2
 1 0 3
 */
public class DivisorExploration3 {
    private static int modulo = 1000000007;
    static Set<Long> ndivisors ;
    static Map<Long,Set<Long>> divisorMap = new HashMap<Long,Set<Long>>();
    static long divisorExploration(int m, int a, int d){
        // Complete this function
        long n = computeN(m,a);
        ndivisors = findDivisors(n);
        divisorMap.put(n, ndivisors);

        List<Long> leafNodes = findLeafNodes(new ArrayList<>(ndivisors), d, 1);

        return findSumOfPhi(leafNodes);
    }

    private static long findSumOfPhi(List<Long> leafNodes) {
        long sum = 0;
        for(Long n : leafNodes){
            sum += phi[n.intValue()];
            //sum += n;
        }
        return sum;
    }

    static long  phi[];

    static void computeTotient(int n)
    {
        phi = new long[n+1];
        for (int i=1; i<=n; i++)
            phi[i] = i;

        // Compute other Phi values
        for (int p=2; p<=n; p++)
        {
            // If phi[p] is not computed already,
            // then number p is prime
            if (phi[p] == p)
            {
                // Phi of a prime number p is
                // always equal to p-1.
                phi[p] = p-1;

                // Update phi values of all
                // multiples of p
                for (int i = 2*p; i<=n; i += p)
                {
                    // Add contribution of p to its
                    // multiple i by multiplying with
                    // (1 - 1/p)
                    phi[i] = (phi[i]/p) * (p-1);
                }
            }
        }


    }

    private static List<Long> findLeafNodes(List<Long> ndivisors, int d, int index) {
        if(index >= d){
            return ndivisors;
        }
        List<Long> newleafs = new ArrayList<>();
        for(Long n : ndivisors){
            newleafs.addAll(findDivisors(n));
        }
        index++;
        return findLeafNodes(newleafs, d, index);
    }

    static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b%a, a);
    }

    static Map<gcdc,Long> gcdmap = new HashMap<>();
    static class gcdc {
        long a;
        long b;
        public gcdc(long a, long b){
            this.a = a;
            this.b = b;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof gcdc)) return false;

            gcdc gcd = (gcdc) o;

            if (a != gcd.a ) return false;
            return b == gcd.b;

        }

        @Override
        public int hashCode() {
            int result = (int) (a ^ (a >>> 32));
            result = 31 * result + (int) (b ^ (b >>> 32));
            return result;
        }
    }

    static Map<Long,Long> phiMap = new HashMap<>();

    static long phi( long n)
    {
        Long r = phiMap.get(n);
        if(r != null) return r;
         long result = 1;
        for (int i=2; i < n; i++) {
            Long g = gcdmap.get(new gcdc(i,n));
            if(g == null) {
                g = gcdmap.get(new gcdc(n,i));
                if(g == null){
                    g= gcd(i, n);
                }
                gcdmap.put((new gcdc(i,n)), g);
            }

            if (g == 1) {
                result++;
            }
        }
        phiMap.put(n, result);
        return result;
    }

    static Set<Long> findDivisors(long n) {
        if(divisorMap.get(n) != null){
            return divisorMap.get(n);
        }
        Set<Long> a = new HashSet<Long>();
        for (long i = 1; i <= Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                long d = n / i;
                if (d == i) {
                    a.add(i);
                } else {
                    a.add(i);
                    a.add(n / i);

                }
            }
        }
        return a;
    }
    static int[] primes;
    static void getAllPrimes(int n){
            primes = new int[n];
        int index=0;
            boolean prime[] = new boolean[n+1];
            for(int i=0;i<n;i++)
                prime[i] = true;

            for(int p = 2; p*p <=n; p++)
            {
                // If prime[p] is not changed, then it is a prime
                if(prime[p] == true)
                {
                    // Update all multiples of p
                    for(int i = p*2; i <= n; i += p)
                        prime[i] = false;
                }
            }

            // Print all prime numbers
            for(int i = 2; i <= n; i++)
            {
                if(prime[i] == true) {
                    primes[index++] = i;
                }
            }

    }
    static long computeN(int m,int a) {
        long result=1;

        for(int i = 1; i <=m; i++){
            result = (long) (result%modulo *  Math.pow(primes[i-1],(a+i))%modulo);
        }

        return result;
    }

    public static void main(String[] args) {
        getAllPrimes(1000);
        computeTotient(1000000000);
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int m = in.nextInt();
            int a = in.nextInt();
            int d = in.nextInt();
            long result = divisorExploration(m, a, d);
            System.out.println(result);
        }
    }
}
