package hrhour22;

import java.util.Scanner;

/**
 * Created by predave on 7/2/17.
 */
public class prog2 {
    static int V;
    static int[][] GRAPH;
    static int countwalks(int graph[][], int u, int v, int k)
    {
        // Table to be filled up using DP. The value count[i][j][e]
        // will/ store count of possible walks from i to j with
        // exactly k edges
        int count[][][] = new int[V][V][k+1];

        // Loop for number of edges from 0 to k
        for (int e = 0; e <= k; e++)
        {
            for (int i = 0; i < V; i++)  // for source
            {
                for (int j = 0; j < V; j++) // for destination
                {
                    // initialize value
                    count[i][j][e] = 0;

                    // from base cases
                    if (e == 0 && i == j)
                        count[i][j][e] = 1;
                    if (e == 1 && graph[i][j]!=0)
                        count[i][j][e] = 1;

                    // go to adjacent only when number of edges
                    // is more than 1
                    if (e > 1)
                    {
                        for (int a = 0; a < V; a++) // adjacent of i
                            if (graph[i][a]!=0)
                                count[i][j][e] += count[a][j][e-1];
                    }
                }
            }
        }
        return count[u][v][k];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        V = n;
        int m = in.nextInt();
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        GRAPH = new int[n][m];

        for(int a0 = 0; a0 < m; a0++){

            int a = in.nextInt();
            int b = in.nextInt();
            int w = in.nextInt();
            if(w < min1){
                min2 = min1;
                min1 = w;
            }
            else if(w < min2){
                min2 = w;
            }

            // Write Your Code Here
        }
        int s = min1 + min2;
        System.out.print(s + " " + (n-1));
    }
}
