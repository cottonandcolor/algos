package hrrookie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 5/5/17.
 */
public class MaxTorism1 {
    static Integer[][] route ;


    public static class UF {
        int num;
        private int[] id;
        private int[] sz;
        public UF(int N){
            num = N+1;
            id = new int[N+1];
            sz = new int[N+1];
            for(int i = 0 ; i <= N ; i ++){
                id[i] = i;
                sz[i] = 1;
            }
        }

        private int root(int i ){
            while( i != id[i]){
                i = id[id[i]];
            }
            return i;
        }

        public void union(int p, int q){
              int i = root(p);
              int j = root(q);

             if(sz[i] < sz[j]){
                 id[i] = root(j);
                 sz[j] += sz[i];
             }
            else {
                 id[j] = root(i);
                 sz[i] += sz[j];
             }
        }

        public int maxSize() {
            Arrays.sort(sz);
            return sz[sz.length-1 ];
        }


        public boolean connected(int p , int q){
            return root(p) == root(q);
        }

        public int find(int p){
            return 0;
        }

        public int count() {
            return 0;
        }


    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        route = new Integer[m][2];
        UF uf = new UF(n);

        for(int route_i=0; route_i < m; route_i++) {
            for (int route_j = 0; route_j < 2; route_j++) {
                route[route_i][route_j] = in.nextInt();
            }

            if(!uf.connected(route[route_i][0] , route[route_i][1]) ){
                uf.union(route[route_i][0] , route[route_i][1]);
            }
        }




        System.out.println(uf.maxSize());

    }



}
