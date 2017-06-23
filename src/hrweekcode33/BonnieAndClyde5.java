package hrweekcode33;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 6/16/17.
 */
public class BonnieAndClyde5 {

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

    static int dest = 0;
    private static boolean findIfPathExists(int n, int m, int u, int v, int w, Graph graph) {
        path1startindex = 0;
        path2startindex= 0;
        foundunique=false;
        dest = w;

        if(u == v || u > n || v > n || w >  n || u < 1 || w < 1 || v < 1) {
            return false;
        }
        boolean[] visited1 = new  boolean[n+1];
        List<Set<Integer>> path1 = new LinkedList<>();
        List<Set<Integer>> path2 = new LinkedList<>();
        Set<Integer> set1 = new HashSet<>();

        Map<Integer,Integer> nodecount1 = new HashMap<>();
        Map<Integer,Integer> nodecount2 = new HashMap<>();

        boolean[][] compared = new boolean[10][10];
        if(u == w ) {
            findAllP(visited1, graph.adj, w, v, v, path1, set1, path2, compared, nodecount1,nodecount2);
            return (path1.size() > 0 || path2.size() > 0);
        } else if( v == w){
            findAllP(visited1, graph.adj, w, u, u, path1, set1, path2, compared, nodecount1, nodecount2);
            return (path1.size() > 0 || path2.size() > 0);
        } else {
            findAllP(visited1, graph.adj, w, u, v, path1, set1, path2, compared, nodecount1, nodecount2);
            if(foundunique ) return true;
        }
        return false;
    }

    private static boolean noDuplicate(Set<Integer> set1, Set<Integer> set2) {
        int len1 = set1.size();
        int len2 = set2.size();
        int total = len1 + len2;
        Set<Integer> temp = new HashSet<>();
        temp.addAll(set1);
        temp.addAll(set2);
        if(temp.size() != total - 1) return false;
        else return true;
    }

    static int path1startindex = 0;
    static int path2startindex= 0;
    static boolean foundunique=false;

    private static void findAllP(boolean[] visited, List<Integer>[] adj, int u, int v1, int v2, List<Set<Integer>> path1, Set<Integer> set, List<Set<Integer>> path2, boolean[][] compared, Map<Integer, Integer> nodecount1, Map<Integer, Integer> nodecount2) {
        if(foundunique) return;
        visited[u] = true;
        set.add(u);
        if(u == v1) {
            path1.add(new HashSet<Integer>(set));
            addNodeCount(set,nodecount1);
            foundunique = comparePaths(path1,path2,true,nodecount1,nodecount2,u);
        }
        else if(u == v2) {
            path2.add(new HashSet<Integer>(set));
            addNodeCount(set,nodecount2);
            foundunique = comparePaths(path1,path2,false,nodecount1,nodecount2,u);
        }
        else if(!foundunique){
            for(Integer n : adj[u]){
                if(!visited[n]){
                    findAllP(visited, adj, n, v1, v2, path1, set, path2, compared, nodecount1, nodecount2);
                }
            }
        }
        visited[u] = false;
        set.remove(u);
    }

    private static void addNodeCount(Set<Integer> set, Map<Integer, Integer> nodecount) {
        for(Integer i : set){
            Integer val = nodecount.get(i) ;
            val = (val == null) ?  1 : val++;
            nodecount.put(i,val);
        }
    }

    private static boolean comparePaths(List<Set<Integer>> path1, List<Set<Integer>> path2, boolean comparePath1, Map<Integer, Integer> nodecount1, Map<Integer, Integer> nodecount2, int w) {
        if(path1.size() == 0 || path2.size() == 0) return false;


        int istart = path1startindex;
        int jstart = path2startindex;
        int path1len = path1.size();
        int path2len = path2.size();
        int totalpaths = path1len + path2len;
        if(comparePath1) {
            jstart = 0;
            Set<Integer> set1 = path1.get(path1len-1);
            for(Integer s : set1){
                if(s == dest ) continue;
                Integer s2 = nodecount2.get(s);
                if(s2 != null && s+s2 >= totalpaths ){
                    return false;
                }
            }
        } else{
            istart = 0;
            Set<Integer> set2 = path2.get(path2len-1);
            for(Integer s : set2){
                if( s == dest ) continue;
                Integer s1 = nodecount1.get(s);
                if(s1 != null && s+s1 >= totalpaths ){
                    return false;
                }
            }
        }



        /*for(int i = istart ; i < path1.size(); i++) {
            for(int j = jstart ; j < path2.size() ; j++) {
                Set<Integer> set1 = path1.get(i);
                Set<Integer> set2 = path2.get(j);
                if (noDuplicate(set1, set2)) {
                    return true;
                }
            }
        }*/
        if(comparePath1) path1startindex++;
        else path2startindex++;

        return true;
    }
}
