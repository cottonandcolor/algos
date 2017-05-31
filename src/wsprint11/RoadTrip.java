package wsprint11;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by predave on 5/27/17.
 */
public class RoadTrip {

    static class Present {
        int g;
        int p;
        public Present(int g, int p){
            this.g = g;
            this.p = p;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int[] w = new int[n - 1];
        Present[] gp = new Present[n];
        for(int i = 0 ; i < n-1; i++){
            w[i] = scanner.nextInt();
        }
        for(int i = 0 ; i < n; i++){
            int g = scanner.nextInt();
            int p = scanner.nextInt();
            gp[i] = new Present(g,p);
        }

        //pre process
        for(int i = 0; i < w.length; i++) {
            gp[i].g = gp[i].g - w[i];
        }
        //1-2 , c=
        for(int i = 0; i < q; i++){
            int s = scanner.nextInt();
            int d = scanner.nextInt();
            System.out.println(findCost(w, gp, s, d));
        }
    }

    private static long findCost(int[] w, Present[] gp, int s, int d) {
        long cost1 = 0;
        long availFuel = 0;
        long minp = Long.MAX_VALUE;

        for(int i = s ; i < d; i++) {
            availFuel += gp[i-1].g;
            minp = Math.min(minp, gp[i-1].p);
            if(availFuel < 0) {
                cost1 += (-(availFuel)) * minp;
                availFuel = 0;
            }
        }
        return cost1;
    }
}
