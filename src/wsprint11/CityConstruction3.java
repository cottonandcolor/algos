package wsprint11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by predave on 5/26/17.
 */
public class CityConstruction3 {

    private static class reachable {
        int x,y;
        public reachable(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
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


    private static List<reachable> rlist = new ArrayList<reachable>();

    public static class TarjanSCC {

        private boolean[] marked;        // marked[v] = has v been visited?
        private int[] id;                // id[v] = id of strong component containing v
        private int[] low;               // low[v] = low number of v
        private int pre;                 // preorder number counter
        private int count;               // number of strongly-connected components
        private Stack<Integer> stack;


        /**
         * Computes the strong components of the digraph {@code G}.
         * @param G the digraph
         */
        public TarjanSCC(Graph G) {
            marked = new boolean[G.V+1];
            stack = new Stack<Integer>();
            id = new int[G.V+1];
            low = new int[G.V+1];
            for (int v = 1; v <= G.V; v++) {
                if (!marked[v]) dfs(G, v);
            }

            // check that id[] gives strong components
            assert check(G);
        }

        private void dfs(Graph G, int v) {
            marked[v] = true;
            low[v] = pre++;
            int min = low[v];
            stack.push(v);
            LinkedList<Integer> wl = G.adj.get(v);
            for (Integer w : wl) {
                if (!marked[w]) dfs(G, w);
                if (low[w] < min) min = low[w];
            }
            if (min < low[v]) {
                low[v] = min;
                return;
            }
            int w;
            do {
                w = stack.pop();
                id[w] = count;
                low[w] = G.V+1;
            } while (w != v);
            count++;
        }


        /**
         * Returns the number of strong components.
         * @return the number of strong components
         */
        public int count() {
            return count;
        }


        /**
         * Are vertices {@code v} and {@code w} in the same strong component?
         * @param  v one vertex
         * @param  w the other vertex
         * @return {@code true} if vertices {@code v} and {@code w} are in the same
         *         strong component, and {@code false} otherwise
         * @throws IllegalArgumentException unless {@code 0 <= v < V}
         * @throws IllegalArgumentException unless {@code 0 <= w < V}
         */
        public boolean stronglyConnected(int v, int w) {
            validateVertex(v);
            validateVertex(w);
            return id[v] == id[w];
        }

        /**
         * Returns the component id of the strong component containing vertex {@code v}.
         * @param  v the vertex
         * @return the component id of the strong component containing vertex {@code v}
         * @throws IllegalArgumentException unless {@code 0 <= v < V}
         */
        public int id(int v) {
            validateVertex(v);
            return id[v];
        }

        // does the id[] array contain the strongly connected components?
        private boolean check(Graph G) {
            TransitiveClosure tc = new TransitiveClosure(G);
            for (int v = 1; v <= G.V; v++) {
                for (int w = 1; w <= G.V; w++) {
                    if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
                        return false;
                }
            }
            return true;
        }

        // throw an IllegalArgumentException unless {@code 0 <= v < V}
        private void validateVertex(int v) {
            int V = marked.length;
            if (v < 1 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }

       /*
        public static void main(String[] args) {
            In in = new In(args[0]);
            Digraph G = new Digraph(in);
            TarjanSCC scc = new TarjanSCC(G);

            // number of connected components
            int m = scc.count();
            StdOut.println(m + " components");

            // compute list of vertices in each strong component
            Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
            for (int i = 0; i < m; i++) {
                components[i] = new Queue<Integer>();
            }
            for (int v = 0; v < G.V(); v++) {
                components[scc.id(v)].enqueue(v);
            }

            // print results
            for (int i = 0; i < m; i++) {
                for (int v : components[i]) {
                    StdOut.print(v + " ");
                }
                StdOut.println();
            }

        }*/

    }

    public static class TransitiveClosure {
        private DirectedDFS[] tc;  // tc[v] = reachable from v

        /**
         * Computes the transitive closure of the digraph {@code G}.
         * @param G the digraph
         */
        public TransitiveClosure(Graph G) {
            tc = new DirectedDFS[G.V+1];
            for (int v = 1; v <= G.V; v++)
                tc[v] = new DirectedDFS(G, v);
        }

        /**
         * Is there a directed path from vertex {@code v} to vertex {@code w} in the digraph?
         * @param  v the source vertex
         * @param  w the target vertex
         * @return {@code true} if there is a directed path from {@code v} to {@code w},
         *         {@code false} otherwise
         * @throws IllegalArgumentException unless {@code 0 <= v < V}
         * @throws IllegalArgumentException unless {@code 0 <= w < V}
         */
        public boolean reachable(int v, int w) {
            validateVertex(v);
            validateVertex(w);
            return tc[v].marked(w);
        }

        // throw an IllegalArgumentException unless {@code 0 <= v < V}
        private void validateVertex(int v) {
            int V = tc.length;
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }

        /**
         * Unit tests the {@code TransitiveClosure} data type.
         *
         * @param args the command-line arguments
         *
        public static void main(String[] args) {
            In in = new In(args[0]);
            Digraph G = new Digraph(in);

            TransitiveClosure tc = new TransitiveClosure(G);

            // print header
            StdOut.print("     ");
            for (int v = 0; v < G.V(); v++)
                StdOut.printf("%3d", v);
            StdOut.println();
            StdOut.println("--------------------------------------------");

            // print transitive closure
            for (int v = 0; v < G.V(); v++) {
                StdOut.printf("%3d: ", v);
                for (int w = 0; w < G.V(); w++) {
                    if (tc.reachable(v, w)) StdOut.printf("  T");
                    else                    StdOut.printf("   ");
                }
                StdOut.println();
            }
        }*/

    }

    public static class DirectedDFS {
        private boolean[] marked;  // marked[v] = true if v is reachable
        // from source (or sources)
        private int count;         // number of vertices reachable from s

        /**
         * Computes the vertices in digraph {@code G} that are
         * reachable from the source vertex {@code s}.
         * @param G the digraph
         * @param s the source vertex
         * @throws IllegalArgumentException unless {@code 0 <= s < V}
         */
        public DirectedDFS(Graph G, int s) {
            marked = new boolean[G.V+1];
            validateVertex(s);
            dfs(G, s);
        }

        /**
         * Computes the vertices in digraph {@code G} that are
         * connected to any of the source vertices {@code sources}.
         * @param G the graph
         * @param sources the source vertices
         * @throws IllegalArgumentException unless {@code 0 <= s < V}
         *         for each vertex {@code s} in {@code sources}
         */
        public DirectedDFS(Graph G, Iterable<Integer> sources) {
            marked = new boolean[G.V+1];
            validateVertices(sources);
            for (int v : sources) {
                if (!marked[v]) dfs(G, v);
            }
        }

        private void dfs(Graph G, int v) {
            count++;
            marked[v] = true;
            LinkedList<Integer> wl = G.adj.get(v);
            for (Integer w : wl) {
                if (!marked[w]) dfs(G, w);
            }
        }

        /**
         * Is there a directed path from the source vertex (or any
         * of the source vertices) and vertex {@code v}?
         * @param  v the vertex
         * @return {@code true} if there is a directed path, {@code false} otherwise
         * @throws IllegalArgumentException unless {@code 0 <= v < V}
         */
        public boolean marked(int v) {
            validateVertex(v);
            return marked[v];
        }

        /**
         * Returns the number of vertices reachable from the source vertex
         * (or source vertices).
         * @return the number of vertices reachable from the source vertex
         *   (or source vertices)
         */
        public int count() {
            return count;
        }

        // throw an IllegalArgumentException unless {@code 0 <= v < V}
        private void validateVertex(int v) {
            int V = marked.length;
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }

        // throw an IllegalArgumentException unless {@code 0 <= v < V}
        private void validateVertices(Iterable<Integer> vertices) {
            if (vertices == null) {
                throw new IllegalArgumentException("argument is null");
            }
            int V = marked.length;
            for (int v : vertices) {
                if (v < 0 || v >= V) {
                    throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
                }
            }
        }


        /**
         * Unit tests the {@code DirectedDFS} data type.
         *
         * @param args the command-line arguments
         *
        public static void main(String[] args) {

            // read in digraph from command-line argument
            In in = new In(args[0]);
            Digraph G = new Digraph(in);

            // read in sources from command-line arguments
            Bag<Integer> sources = new Bag<Integer>();
            for (int i = 1; i < args.length; i++) {
                int s = Integer.parseInt(args[i]);
                sources.add(s);
            }

            // multiple-source reachability
            DirectedDFS dfs = new DirectedDFS(G, sources);

            // print out vertices reachable from sources
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) StdOut.print(v + " ");
            }
            StdOut.println();
        }*/

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

        int v = graph.getVerticeCount();
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
                rlist.add(new reachable(x,y));
            }
        }

        TarjanSCC tscc = new TarjanSCC(graph);
        for(reachable r: rlist){
            if(tscc.stronglyConnected(r.x, r.y)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

    }
}
