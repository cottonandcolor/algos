package hrw32;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by predave on 5/19/17.BallsBoxes
 * We build a bipartite graph with  points. For the th kind of balls,we link an edge from  to point  with the flow limit equal to  and cost equal to 0. For the th kind of balls and the box , we link an edge from point  to point  with the flow limit equal to  and cost equal to . In order to deal with the extra cost we have to pay to make the box bigger, for a box , we link n edges from point  to point ,each with the flow limit equal to 1. As for the cost,for the  edge such that j is smaller or equal to , the cost is 0, while for the  edge, the cost is , and the subsequent edges for example the th edge, its cost is . Finally we run a max-cost flow to get the best answer. One should notice that when we find a augment path whose cost is smaller than 0, we should break the procedure of finding augment becuase we would not get a better answer.
 Now let's try to prove the correctness of this algorithm. The costs of the edges from a box  to  are descending, so if the flow that pass box  is , it has to pass the first  edges from box  to , and you can easily find that the sum of the cost is exactly equal to . The only fact it uses is that .
 Set by philipsweng

 Problem Setter's code :
 #include <bits/stdc++.h>

 using namespace std;

 const int maxn = 405;

 struct node
 {
 int to,next,flow,cost;

 node(void){}
 node(int a,int b,int c,int d) : to(a),next(b),flow(c),cost(d){}
 }e[maxn * maxn];

 int final[maxn],in[maxn],A[maxn],B[maxn][maxn],C[maxn],d[maxn],tot;
 int n,m,s,t,ans;

 void link(int u,int v,int f,int c)
 {
 e[++ tot] = node(v,final[u],f,c),final[u] = tot;
 e[++ tot] = node(u,final[v],0,-c),final[v] = tot;
 }

 int dfs(int now,int flow)
 {
 if (now == t) return ans += flow * d[now],flow;
 int use = 0;
 in[now] = 1;
 for(int i = final[now];i;i = e[i].next)
 if (!in[e[i].to] && d[e[i].to] == d[now] + e[i].cost && e[i].flow)
 {
 int tmp = dfs(e[i].to,min(flow - use,e[i].flow));
 use += tmp,e[i].flow -= tmp,e[i ^ 1].flow += tmp;
 if (use == flow) return use;
 }
 return use;
 }

 void min_cost()
 {
 for(;;)
 {
 static int que[maxn * maxn];
 for(int i = s;i <= t;i ++) d[i] = -(1 << 30),in[i] = 0;
 d[s] = 0;
 que[1] = s;
 int fi = 1,en = 1;
 for(;fi <= en;fi ++)
 {
 int u = que[fi];
 for(int i = final[u];i;i = e[i].next)
 if (e[i].flow && d[u] + e[i].cost > d[e[i].to])
 {
 d[e[i].to] = d[u] + e[i].cost;
 if (!in[e[i].to]) in[e[i].to] = 1,que[++ en] = e[i].to;
 }
 in[u] = 0;
 }
 if (d[t] <= 0) break;
 dfs(s,1 << 30);
 }
 }

 int main()
 {
 tot = 1;
 scanf("%d%d", &n, &m);
 for(int i = 1;i <= n;i ++) scanf("%d", &A[i]);
 for(int i = 1;i <= m;i ++) scanf("%d", &C[i]);
 for(int i = 1;i <= n;i ++)
 for(int j = 1;j <= m;j ++) scanf("%d", &B[i][j]);
 s = 0,t = n + m + 1;
 for(int i = 1;i <= n;i ++) link(s,i,A[i],0);
 for(int i = 1;i <= n;i ++)
 for(int j = 1;j <= m;j ++)
 link(i,n + j,1,B[i][j]);
 for(int i = 1;i <= m;i ++)
 {
 link(i + n,t,C[i],0);
 link(i + n,t,1,-1);
 for(int j = C[i] + 1;j < n;j ++)
 link(i + n,t,1,-2 * (j - C[i]) - 1);
 }
 min_cost();
 printf("%d\n", ans);
 return 0;
 }

 *
 */
public class BallsBoxes1 {
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
