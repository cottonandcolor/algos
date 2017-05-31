package hrw32;

import java.util.Scanner;

/**
 * Created by predave on 5/17/17.
 */
public class circularwalk {
        static int circularWalk(int n, int s, int t, int r_0, int g, int seed, int p){
            // Complete this function
            if(s == t){
                return 0;
            }
            int[] a = new int[n];
            a[0] = r_0;

            for(int i = 1 ; i < n ; i++){
                a[i] = ((a[i-1] * g) + seed) % p;
            }
            if(a[s] == 0) return -1;
            computeShortestTime(n,a, s, t);
            if(found){
                return time;
            }
            return -1;
        }

    private static int time=0;
    private static boolean found = false;
    private static int computeShortestTime(int n,int[] a, int s, int t) {

        if(found ) return 0;
        boolean[] visited = new boolean[n];

        if(s == t) {
            found = true;
            return 1;
        }
        if(a[s] == 0) return -1;

        int iter = 0;

        while(s != t && !found && !(iter >= n - 1)) {
            visited[s] = true;
            int nextStart = -1;
            if (a[s] < minDist(s, t, n)) {
                time++;
                int maxDist = Integer.MIN_VALUE;

                for(int i = 1 ; i <= a[s] ; i++) {
                    int RI = getIndex(i+s, n);
                    if(visited[RI]) continue;

                    maxDist = Math.max(maxDist, a[RI]);
                    if(maxDist == a[RI] && a[RI] != 0) nextStart = RI;

                    if (a[RI] != 0 && a[RI] >= minDist(RI, t, n)) {
                        found = true;
                        time++;
                        return time;
                    }
                    int LI = getIndex(s-i, n);

                    if (a[LI] != 0 && a[LI] >= minDist(LI, t, n)) {
                        found = true;
                        time++;
                        return time;
                    }
                    maxDist = Math.max(maxDist, a[LI]);
                    if(maxDist == a[LI]) nextStart = LI;
                    if(nextStart == -1) {
                        //could not find next start
                        return -1;
                    }
                    s = nextStart;
                    visited[RI] = true;
                    visited[LI] = true;
                    iter+=2;
                }


            } else {
                time++;
                found = true;
                return time;
            }
        }

        return time;
    }

    private static int getIndex(int s, int n ) {
        if(s >= n) s = s - n ;
        if(s < 0) s = n + s;
        return s;
    }


    private static int minDist(int s, int t , int n) {
        int dist = Math.abs(t - s);
        return Math.min(n - dist , dist);
    }

    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int s = in.nextInt();
            int t = in.nextInt();
            int r_0 = in.nextInt();
            int g = in.nextInt();
            int seed = in.nextInt();
            int p = in.nextInt();
            int result = circularWalk(n, s, t, r_0, g, seed, p);
            System.out.println(result);
        }

}
