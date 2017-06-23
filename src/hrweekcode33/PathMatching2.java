package hrweekcode33;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by predave on 6/17/17.
 */
public class PathMatching2 {


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

        for(Query query : queries){
            found = false;
            List<Integer> path = new LinkedList<>();
            boolean[] visited = new  boolean[n+1];
            findShortestPath(query.u,query.v,path,visited,graph.adj);
            String result = null;
            for(Integer p1 : path)
            {
                result = result + s.charAt(p1-1);
            }
            if(result != null){
                String[] sp = result.split(p);
                System.out.println(sp.length);
            } else {
                System.out.println("0");
            }

        }
    }


    private static void findShortestPath(int u, int v, List<Integer> path, boolean[] visited,  List<Integer>[] adj) {
        if(found) return;
        visited[u] = true;
        path.add(u);
        if(u == v) {
            found = true;
            return;

        }

        for(Integer n : adj[u]){
            if(!visited[n]){
                findShortestPath(n, v, path,visited, adj);
            }
        }

        //visited[u] = false;
        //path.remove(path.size()-2);
    }

}
