package hrweekcode33;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 6/16/17.
 */
public class BonnieAndClyde3 {

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
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        Graph graph = new Graph(n);
        for(int a0 = 0; a0 < m; a0++){
            int u = in.nextInt();
            int v = in.nextInt();
            graph.addEdge(u,v);
            graph.addEdge(v,u);
        }
        for(int a0 = 0; a0 < q; a0++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            boolean status = findIfPathExists(n,m,u,v,w,graph);
            if(status) System.out.println("YES");
            else System.out.println("NO");
        }
        
        
    }

    private static boolean findIfPathExists(int n, int m, int u, int v, int w, Graph graph) {
        if(u == v || u > n || v > n || w >  n || u < 1 || w < 1 || v < 1) {
            return false;
        }
        boolean[] visited1 = new  boolean[n+1];
        List<Set<Integer>> path1 = new LinkedList<>();
        List<Set<Integer>> path2 = new LinkedList<>();
        Set<Integer> set1 = new HashSet<>();

        if(u == w ) {
            findAllP(visited1, graph.adj, w, v, v, path1, set1, path2);
            return (path1.size() > 0 || path2.size() > 0);
        } else if( v == w){
            findAllP(visited1, graph.adj, w, u, u, path1, set1, path2);
            return (path1.size() > 0 || path2.size() > 0);
        } else {
            findAllP(visited1, graph.adj, w, u, v, path1, set1, path2);
        }

       /* if(path1.size() > 0 && path2.size() > 0)
        {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }*/
        return(noDuplicates(path1, path2,  w));
    }

    private static boolean noDuplicates(List<Set<Integer>> path1, List<Set<Integer>> path2,  int w) {
        int p1 = path1.size();
        int p2 = path2.size();
        boolean[][] compared = new boolean[p1+1][p2+1];

        for( int i = 0 ; i < p1; i++){
            for(int j = 0 ; j < p2 ; j++) {
                //if(!compared[i][j]){
                    Set<Integer> set1 = path1.get(i);
                    Set<Integer> set2 = path2.get(j);
                    if (noDuplicate(set1, set2, w)) {
                        return true;
                    }
                    //compared[i][j]= true;

                //}
            }
        }
        return false;

    }

    private static boolean noDuplicate(Set<Integer> set1, Set<Integer> set2, int w) {
        int len1 = set1.size();
        int len2 = set2.size();
        int total = len1 + len2;
        Set<Integer> temp = new HashSet<>();
        temp.addAll(set1);
        temp.addAll(set2);
        if(temp.size() != total - 1) return false;
        else return true;
    }

    private static void findAllP(boolean[] visited, List<Integer>[] adj, int u, int v1, int v2, List<Set<Integer>> path1, Set<Integer> set, List<Set<Integer>> path2) {
        visited[u] = true;
        set.add(u);
        if(u == v1) {
            path1.add(new HashSet<Integer>(set));
        }
        else if(u == v2) {
            path2.add(new HashSet<Integer>(set));
        }
        else {
            for(Integer n : adj[u]){
                if(!visited[n]){
                    findAllP(visited, adj, n, v1, v2, path1, set, path2);
                }
            }
        }
        visited[u] = false;
        set.remove(u);
    }
}
