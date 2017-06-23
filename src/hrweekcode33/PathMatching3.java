package hrweekcode33;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by predave on 6/17/17.
 */
public class PathMatching3 {


    public static class Graph {
        int V;
        List<Integer> adj[] ;

        public Graph(int V) {
            this.V = V+1;
            adj = new LinkedList[this.V];
            for (int i=0; i<V+1; ++i)
                adj[i] = new LinkedList();
        }

        public void addEdge(int u, int v){
            adj[u].add(v);
            adj[v].add(u);
        }
    }
    
    
    public static class Query{
        int u;
        int v;
        
        public Query(int u, int v){
            this.u = u;
            this.v = v;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();
        String s = in.next();
        String p = in.next();
        Graph graph = new Graph(n);
        List<Query> queries = new LinkedList<>();

        for(int a0 = 0; a0 < n-1; a0++){
            int u = in.nextInt();
            int v = in.nextInt();
            graph.addEdge(u,v);
        }
        for(int a0 = 0; a0 < q; a0++){
            int u = in.nextInt();
            int v = in.nextInt();
            queries.add(new Query(u,v));
        }
        findCount(graph,queries,n,q,s,p);
    }
    static boolean found = false;
    private static void findCount(Graph graph, List<Query> queries, int n, int q, String s, String p) {
        Map<Integer,String> pre[] = new HashMap[n+1];
        for (int i=0; i<n+1; ++i)
            pre[i] = new HashMap<>();

        for(Query query : queries){

            found = false;
            List<Integer> path = new LinkedList<>();
            boolean[] visited = new  boolean[n+1];
            String result = "";
            //String reverse = pre[query.v].get(query.u) ;
           /* if(reverse != null ){
                if(!reverse.isEmpty()) {
                    for (int i = reverse.length() - 1; i >= 0; i--)
                        result = result + reverse.charAt(i);
                }
            }
            else {*/
                findShortestPath(query.u, query.v, path, visited, graph.adj);
                for (Integer p1 : path) {
                    result = result + s.charAt(p1 - 1);
                }
               // pre[query.u].put(query.v, result);
           // }
            if(result != null && !result.isEmpty()){
                int f = getNumPatterns(result,p);
                System.out.println(f);
            } else {
                System.out.println("0");
            }

        }
    }

    private static int getNumPatterns(String result, String p) {
        String concat = p + "$" + result;
        int l = concat.length();
        int Z[] = new int[l];
        int len = p.length();
        getZarr(concat, Z);
        int count = 0;
        for (int i = 0; i < l; i++)
        {
            if (Z[i] == len) {
                count++;
            }
        }
        return count;
    }

    private static void getZarr(String str, int[] Z) {

        int n = str.length();
        int L, R, k;

        L = R = 0;
        for (int i = 1; i < n; ++i) {
            if (i > R) {
                L = R = i;

                while (R < n && str.charAt(R - L) == str.charAt(R))
                    R++;
                Z[i] = R - L;
                R--;
            } else {
                k = i - L;

                if (Z[k] < R - i + 1)
                    Z[i] = Z[k];

                else {
                    L = i;
                    while (R < n && str.charAt(R - L) == str.charAt(R))
                        R++;
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }


    private static void findShortestPath(int u, int v, List<Integer> path, boolean[] visited,  List<Integer>[] adj) {

        Queue<Integer> Q = new LinkedList<>();
        Q.add(u);
        visited[u] = true;

        while(!Q.isEmpty()){
            Integer current = Q.poll() ;
            //System.out.print(current+" ");
            path.add(current);
            if(current == v) {
                return;
            }
            for(Integer n : adj[current]){
                if(!visited[n]){
                    visited[n]=true;
                    if(n == v){
                        //System.out.print(n);
                        path.add(n);
                        return;
                    }
                    Q.add(n);
                }
            }
        }
        path.clear();
    }

}
