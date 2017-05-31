package wsprint11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by predave on 5/26/17.
 */
public class CityConstruction2 {
    private static class Graph {
        private int V;   // No. of vertices
        public List<LinkedList<Integer>> adj; //Adjacency List

        //Constructor
        Graph(int v) {
            V = v;
            adj = new ArrayList<LinkedList<Integer>>();
            for (int i = 0; i <= v; ++i)
                adj.add(new LinkedList());
        }

        //Function to add an edge into the graph
        void addEdge(int v, int w) {
            adj.get(v).add(w);
        }

        public int getVerticeCount() {
            return V;
        }

        void addVertex(Boolean from, int v) {
            V++;
            adj.add(new LinkedList());
            if (from) {
                addEdge(V, v);
            } else {
                addEdge(v, V);
            }
        }
    }


    private static boolean[][] tc;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Graph graph = new Graph(n);
        int m = in.nextInt();
        for(int a0 = 0; a0 < m; a0++){
            int u = in.nextInt();
            int v = in.nextInt();
            graph.addEdge(u, v);
        }

        int v = graph.getVerticeCount();
        int q = in.nextInt();

        if(v < 5000) {
            tc = new boolean[5000 + 1][5000 + 1];
        }else {
            tc = new boolean[v + 1][v + 1];
        }

        for(int i = 0; i <= v ; i++) {
            tc[i][i] = true;
            LinkedList<Integer> adjs = graph.adj.get(i);
            if (adjs != null) {
                Iterator<Integer> iter = adjs.iterator();
                while (iter.hasNext()) {
                    int j = iter.next();
                    tc[i][j] = true;
                }
            }
        }

        for(int k = 0; k <=v ; k++){
            for(int i = 0; i <=v; i++){

                for (int j = 0; j <= v; j++) {
                    tc[i][j] = (tc[i][j]!=false) ||
                            ((tc[i][k]!=false) && (tc[k][j]!=false))?true:false;
                }

            }

        }
        int tclength = tc.length;

        for(int a0 = 0; a0 < q; a0++){
            int c = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();

            if(c == 1) {
                boolean from = false;
                if(y == 1) {
                    from = true;
                }
                int z = graph.getVerticeCount()+1;
                if(z > tclength){
                    boolean[][]tmp = new boolean[tclength+5000][tclength+5000];
                    System.arraycopy(tc, 0 , tmp, 0, tclength);
                    tc = tmp;
                    tclength+=5000;
                }
                graph.addVertex(from,x);
                if(!from) {
                    tc[x][z] = true;
                    //whatever connected to x is also connected to z
                    for(int i = 0 ; i <= graph.getVerticeCount(); i++){
                        if(tc[i][x]) tc[i][z] = true;
                    }
                } else {
                    //z is connected to everything x connects to
                    tc[z][x] = true;
                    for(int i = 0 ; i <= graph.getVerticeCount(); i++){
                        if(tc[x][i]) tc[z][i] = true;
                    }
                }

            } else if ( c == 2){
                //rlist.add(new reachable(x,y));
                if(tc[x][y]){
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }


    }
}
