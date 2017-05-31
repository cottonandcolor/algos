package wsprint11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by predave on 5/26/17.
 */
public class CityConstruction {

    private static class Graph {
        private int V;   // No. of vertices
        private List<LinkedList<Integer>> adj; //Adjacency List

        //Constructor
        Graph(int v)
        {
            V = v;
            adj = new ArrayList<LinkedList<Integer>>();
            for (int i=0; i<=v; ++i)
                adj.add(new LinkedList());
        }

        //Function to add an edge into the graph
        void addEdge(int v,int w)  {   adj.get(v).add(w);   }

        void addVertex( Boolean from , int v) {
            V++;
            adj.add(new LinkedList());
            if(from){
                addEdge(V, v);
            } else {
                addEdge(v, V);
            }
        }
        //prints BFS traversal from a given source s
        Boolean isReachable(int s, int d)
        {
            LinkedList<Integer>temp;

            // Mark all the vertices as not visited(By default set
            // as false)
            boolean visited[] = new boolean[V+1];

            // Create a queue for BFS
            LinkedList<Integer> queue = new LinkedList<Integer>();

            // Mark the current node as visited and enqueue it
            visited[s]=true;
            queue.add(s);

            // 'i' will be used to get all adjacent vertices of a vertex
            Iterator<Integer> i;
            while (queue.size()!=0)
            {
                // Dequeue a vertex from queue and print it
                s = queue.poll();

                int n;
                i = adj.get(s).listIterator();

                // Get all adjacent vertices of the dequeued vertex s
                // If a adjacent has not been visited, then mark it
                // visited and enqueue it
                while (i.hasNext())
                {
                    n = i.next();

                    // If this adjacent node is the destination node,
                    // then return true
                    if (n==d)
                        return true;

                    // Else, continue to do BFS
                    if (!visited[n])
                    {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }

            // If BFS is complete without visited d
            return false;
        }


    }


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
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int c = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();

            if(c == 1) {
                boolean from = false;
                if(y == 1) {
                    from = true;
                }
                graph.addVertex(from,x);
            } else if ( c == 2){
                 if(graph.isReachable(x,y)) {
                     System.out.println("Yes");
                 } else {
                     System.out.println("No");
                 }
            }
        }
    }
}
