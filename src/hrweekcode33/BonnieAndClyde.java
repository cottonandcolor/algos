package hrweekcode33;

import java.util.LinkedList;
import java.util.*;
import java.util.Scanner;

/**
 * Created by predave on 6/16/17.
 */
public class BonnieAndClyde {

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
            findIfPathExists(n,m,u,v,w,graph);
        }
        
        
    }

    private static void findIfPathExists(int n, int m, int u, int v, int w, Graph graph) {
        //find path u- w
        //find path v - w
        boolean status = false;

        boolean[] visited1 = new  boolean[n+1];
        boolean[] visited2 = new  boolean[n+1];
        boolean[][] compared = new boolean[n+1][n+1];
        visited1[u] = true;
        visited1[v] = true;
        visited2[v] = true;
        visited2[u] = true;
        List<Set<Integer>> path1 = new LinkedList<>();
        List<Set<Integer>> path2 = new LinkedList<>();
        boolean path1search=true;
        boolean path2search=true;
        while( path1search && path2search) {
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            if(path1search) {
                findAllP(visited1, graph.adj, u, w, set1);

                if (set1.size() <= 1) {
                    path1search = false;
                } else {
                    set1.remove(w);
                    path1.add(set1);
                }
            }
            if(path2search) {
                findAllP(visited2, graph.adj, v, w, set2);
                if (set2.size() <= 1) {
                    path2search = false;
                } else {
                    set2.remove(w);
                    path2.add(set2);
                }
            }
            if(path1search && path2search) {
                if (noDuplicate(set1, set2)) {
                    System.out.println("YES");
                    return;
                }
                compared[path1.size() -1 ][path2.size() -1] = true;
                compared[path2.size() -1 ][path1.size() -1 ] = true;
            }
        }

        if(noDuplicates(path1, path2, compared)){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    private static boolean noDuplicates(List<Set<Integer>> path1, List<Set<Integer>> path2, boolean[][] compared) {
        for( int i = 0 ; i < path1.size(); i++){
            for(int j = 0 ; j < path2.size() ; j++) {
                //if(!compared[i][j]){
                    Set<Integer> set1 = path1.get(i);
                    Set<Integer> set2 = path2.get(j);
                    if (noDuplicate(set1, set2)) {
                        //System.out.println("YES");
                        return true;
                    }
                //}
            }
        }
        return false;

    }

    private static boolean noDuplicate(Set<Integer> set1, Set<Integer> set2) {
        int len1 = set1.size();
        int len2 = set2.size();
        boolean found = false;
        //if(len1 < 2 || len2 < 2) return false;

        if(len1 < len2){
            for(Integer i : set1){
                if(set2.contains(i)) return false;
            }
        } else {
            for(Integer i : set2){
                if(set1.contains(i)) return false;
            }
        }
        return true;
    }

    private static void findAllP(boolean[] visited, List<Integer>[] adj, int u, int v, Set<Integer> path) {
        visited[u] = true;
        path.add(u);
        if(u == v) {
            return ;
        } else {
            for(Integer n : adj[u]){
                if(!visited[n]){
                    findAllP(visited, adj, n, v, path);
                } else if(n == v){
                    path.add(v);
                    return;
                }
            }
        }
        //visited[u] = false;
    }
}
