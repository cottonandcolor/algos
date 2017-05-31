package hrw32;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by predave on 5/19/17.
 */
public class BallsBoxes {
    private static PriorityQueue<CandieCount>  pq = new PriorityQueue<CandieCount>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int totalballs = 0;
        int[] A = new int[n];
        for(int A_i=0; A_i < n; A_i++){
            A[A_i] = in.nextInt();
            totalballs += A[A_i];
        }
        int[] C = new int[m];
        for(int C_i=0; C_i < m; C_i++){
            C[C_i] = in.nextInt();
        }
        int[][] B = new int[n][m];
        for(int B_i=0; B_i < n; B_i++){
            for(int B_j=0; B_j < m; B_j++){
                B[B_i][B_j] = in.nextInt();
                if(B[B_i][B_j] != 0) {
                    CandieCount cc = new CandieCount(B_i,B_j,B[B_i][B_j]);
                    pq.add(cc);
                }
            }
        }
        // Write Your Code Here
        System.out.println(findMaxCandies(A,C,B,n,m,totalballs));
    }
    private static class CandieCount implements Comparable<CandieCount>, Comparator<CandieCount> {
        private int i ;
        private int j;
        private int count;

        public CandieCount (int i, int j , int count) {
            this.i = i;
            this.j = j;
            this.count = count;
        }

        @Override
        public int compareTo(CandieCount o) {
            if(this.count > o.count ) return -1;
            if(this.count == o.count) return 0;
            return 1;
        }

        @Override
        public int compare(CandieCount o1, CandieCount o2) {
            if(o1.count > o2.count) return -1;
            if(o2.count > o1.count) return 1;
            return 0;
        }
    }

    //each box can have atmost 1 color so when b[i][j] is picked only once so
    //keep priority queue of B
    //pick top most decrease A[i] decrease C[j] , check if B[ij] > c[j]square increase candie count , decrease n
    // if n <= 0 return, if B is empty return
    //

    private static long findMaxCandies(int[] A, int[] C, int[][] B, int n, int m, int tballs) {
        long totalC = 0;
        long totalD = 0;
        if(n == 0 || m == 0) return 0;

        while(pq.peek() != null && tballs >0) {
            CandieCount cc = pq.poll();
            if(A[cc.i] != 0 ) {
                if(C[cc.j] <= 0){
                    int t = C[cc.j] - 1;
                    int d  = (t * t) - ((t+1)*(t+1));
                    int delta = cc.count - d;
                    if(delta  > 0) {
                        totalC += cc.count;
                        A[cc.i] = A[cc.i] - 1;
                        C[cc.j] = C[cc.j] - 1;
                        tballs--;
                    }
                } else {
                    totalC += cc.count;
                    A[cc.i] = A[cc.i] - 1;
                    C[cc.j] = C[cc.j] - 1;
                    tballs--;
                }
            }
        }

        for(int i = 0 ; i< m; i++) {
            totalC = totalC - (C[i]*C[i]);
        }
        return totalC;
    }
}
