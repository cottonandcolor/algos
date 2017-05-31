package hrcrackc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by predave on 5/15/17.
 */
public class BFS {


        public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

            Scanner scanner = new Scanner(System.in);
            int q = scanner.nextInt();

            for(int i = 0 ; i < q; i++) {
                Map<Integer,LinkedList<Integer>> edges = new HashMap<Integer,LinkedList<Integer>>();
                int nodes[];

                int n = scanner.nextInt();
                nodes = new int[n+1];
                int e = scanner.nextInt();

                for(int j = 0; j < e; j++) {
                    Integer start = scanner.nextInt();
                    Integer end = scanner.nextInt();
                    LinkedList<Integer> edgelist = edges.get(start);
                    if(edgelist == null ) {
                        edgelist = new LinkedList<Integer>();
                        edges.put(start, edgelist);
                    }
                    edgelist.add(end);

                    LinkedList<Integer> edgelist2 = edges.get(end);
                    if(edgelist2 == null ) {
                        edgelist2 = new LinkedList<Integer>();
                        edges.put(end, edgelist2);
                    }
                    edgelist2.add(start);
                }
                doBFS(nodes, edges, scanner.nextInt(), n, e);
                System.out.println();
            }
        }

        private static void doBFS(int[] nodes, Map<Integer, LinkedList<Integer>> edges, int start, int n, int e) {
            boolean[] visited = new boolean[n+1];
            Queue<Integer> nodeQ = new LinkedList<Integer>();
            nodeQ.add(start);
            visited[start] = true;

            bfs(nodes, edges, visited, nodeQ);
            for(int i = 1; i <= n ; i++){
                if(i == start) continue;
                if(nodes[i] == 0)
                    System.out.print("-1 ");
                else
                    System.out.print(nodes[i] + " ");

            }
        }

        private static void bfs(int[] nodes, Map<Integer, LinkedList<Integer>> edges, boolean[] visited, Queue<Integer> nodeQ) {

            while(!nodeQ.isEmpty()) {
                Integer start = nodeQ.poll();
                LinkedList<Integer> clist = edges.get(start);
                if(clist != null) {
                    for (Integer c : clist) {
                        if (!visited[c]) {
                            nodes[c] = nodes[start] + 6;
                            nodeQ.offer(c);
                            visited[c] = true;
                        }
                    }
                }
            }


         }

}
