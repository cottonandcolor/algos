package hrweekcode33;

import java.util.Scanner;

/**
 * Created by predave on 6/14/17.
 */
public class TransformToPalindrome2 {

    public static class WeightedQuickUnionUF {
        private int[] parent;   // parent[i] = parent of i
        private int[] size;     // size[i] = number of sites in subtree rooted at i
        private int count;      // number of components

        public WeightedQuickUnionUF(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int count() {
            return count;
        }

        public int find(int p) {
            validate(p);
            while (p != parent[p])
                p = parent[p];
            return p;
        }

        private void validate(int p) {
            int n = parent.length;
            if (p < 0 || p >= n) {
                throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
            }
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            // make smaller root point to larger one
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
        }


    }

    public static int findPalindrome(int[] chars){
        int [][]LP = new int[chars.length][chars.length];
        for(int i=0;i<chars.length;i++){
            LP[i][i] = 1;
        }
        for(int sublen = 2;sublen<=chars.length;sublen++){
            for(int i=0;i<=LP.length-sublen;i++){
                int j = i+sublen-1;
                if(chars[i]==chars[j] && sublen==2){
                    LP[i][j] = 2;
                }
                else if(chars[i]==chars[j]){
                    LP[i][j] = LP[i+1][j-1]+2;
                }
                else{
                    LP[i][j] = Math.max(LP[i+1][j],LP[i][j-1]);
                }
            }
        }

        return LP[0][LP.length-1];

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n+1);

        for(int a0 = 0; a0 < k; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            if (uf.connected(x, y)) continue;
            uf.union(x, y);
        }
        int[] a = new int[m];
        for(int a_i=0; a_i < m; a_i++){
            a[a_i] = in.nextInt();
        }

        System.out.println(findlps(n,k,m,a,uf));
    }

    private static int findlps(int n, int k, int m, int[] a, WeightedQuickUnionUF uf) {
        for(int i = 0; i < a.length; i++){
            a[i] = uf.find(a[i]);
        }
        //return lps1(a,0,a.length-1);
        return findPalindrome(a);
    }
}
