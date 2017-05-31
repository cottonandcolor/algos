package hrw32;

import ccode.Graph.Tree;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.*;

/**
 * Created by predave on 5/18/17.
 * First we have to note that once i and k are fixed,j is fixed too. So we come up with an approach whose times complexity is  that we simply iterate every pair of  and check whether  is a square number. If it is, let  and check if . This approach is too slow to pass the strict time limit, however, if we write a brute-force problem to check how many pairs of  satisfies that  is a square-number, it's surprising to find that the number is nearly O(n), which is very small. So our goal is to iterate every legal pair of  efficiently.
 For number i, assumed that we have  prime numbers less than , we can construct a string  for i such that if the th prime occurs in the factorization of i for odd times,, otherwise it's 0. Then if  is a square number, s[i] should be equal to s[k]. We don't need to know the exact  but to know whether two s[i] and s[k] are the same so that we could use hash. In order to get the hash for every s[i] we have to use prime seive. As for the detail you can infer to the code written by me. For every k, we simply iterate every i whose s[i] is equal to s[k], which we can apply by using map in c++.
 So the overall time complexity is . Of course, the algorithm can be improved to , but I think it's a little bit trivial and meaningless.
 Set by philipsweng

 Problem Setter's code :
 #include <bits/stdc++.h>

 using namespace std;

 typedef unsigned long long ull;

 const int maxn = 500005;

 map<ull,vector<int> > sav;
 ull f[maxn],v[maxn];
 char s[maxn];
 int pri[maxn],n;

 void pre_treat()
 {
 static bool f[maxn];
 static int stk[maxn];
 for(int i = 2,top = 0;i <= n;i ++)
 {
 if (!f[i]) stk[++ top] = i,pri[i] = i;
 for(int j = 1;i * stk[j] <= n && j <= top;j ++)
 {
 f[i * stk[j]] = 1;
 pri[i * stk[j]] = stk[j];
 if (i % stk[j] == 0) break;
 }
 }
 }

 int main()
 {
 scanf("%d", &n);
 f[0] = 1;
 for(int i = 1;i <= n;i ++) f[i] = ((f[i - 1] * 37ll + 123847) << 31) + rand();
 char c;
 for(int i = 1;i <= n;i ++)
 {
 while (c = getchar(),c < 'a' || c > 'z');
 s[i] = c;
 }
 pre_treat();
 ull ans = 0;
 for(int i = 1;i <= n;i ++)
 {
 for(int q = i;q > 1;q /= pri[q]) v[i] ^= f[pri[q]];
 if (s[i] != 'b' && sav.count(v[i]))
 {
 for(auto p : sav[v[i]])
 {
 int mid = sqrt(i * 1ll * p);
 if (s[mid] == 'b' && (s[i] == 'a' && s[p] == 'c' || s[i] == 'c' && s[p] == 'a'))
 ++ ans;
 }
 }
 sav[v[i]].push_back(i);
 }
 cout << ans << endl;
 return 0;
 }
 */
public class GeometricTrick {

    static int geometricTrick(String s){
        // Complete this function
        int len = s.length();
        TreeSet<Long> a = new TreeSet<Long>();
        TreeSet<Long> c = new TreeSet<Long>();

        long b[] = new long[len];
        int bindex = 0;
        int aindex = 0;
        int cindex = 0;
        for(int i = 1 ; i <= len ; i++){ //preprocess
            if(s.charAt(i-1) == 'b') {
                b[bindex++] = (i) * (i);
            } else if(s.charAt(i-1) == 'c'){
                c.add(Long.valueOf(i));
                cindex++;
            }
            else {
                a.add(Long.valueOf(i));
                aindex++;
            }
        }
        if( aindex < 1) return 0;
        if( cindex < 1) return 0;
        if (bindex == 0) return 0;
        int totalcount = 0;
//todo optimization iterate through min of a, b c

        for(int i = 0 ; i < bindex; i++){
            if(aindex < cindex) {
                Iterator<Long> aiter = a.iterator();
                while (aiter.hasNext()) {
                    long aval = aiter.next();
                    if (b[i] < aval) break;
                    if (b[i] % aval == 0 && c.contains(b[i] / aval)) {
                        totalcount++;
                    }
                }
            } else {
                Iterator<Long> citer = c.iterator();
                while (citer.hasNext()) {
                    long cval = citer.next();
                    if (b[i] < cval) break;
                    if (b[i] % cval == 0 && a.contains(b[i] / cval)) {
                        totalcount++;
                    }
                }
            }

        }

        return totalcount;
    }

    public static void main(String[] args) {
        //35
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int result = geometricTrick(s);
        System.out.println(result);
    }
}
