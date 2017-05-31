package hrcrackc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Created by predave on 5/14/17.
 */
public class ConnectedCell {



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }

        System.out.println(findLongestConnected(grid, n , m));
    }

    private static int findLongestConnected(int[][] grid, int r , int c) {
        int longestRegion = 0;
        boolean visited[][] = new boolean[r][c];
        for(int i = 0 ; i < r ; i++) {
            for(int j = 0 ; j < c ; j++){
                longestRegion = Math.max(longestRegion, findSubLongestRegion(grid, i , j, visited, r, c));
            }
        }
        return longestRegion;
    }

    private static int findSubLongestRegion(int[][] grid, int i, int j, boolean[][] visited, int r, int c) {
        if(i < 0 || j < 0 || i >= r || j >= c) return 0 ;

        if(grid[i][j] == 0 ) return 0;

        if(visited[i][j]) return 0;

        int count = 1;
        visited[i][j] = true;
        count += findSubLongestRegion(grid, i+1, j, visited, r,c);
        count += findSubLongestRegion(grid, i, j+1, visited, r, c);
        count += findSubLongestRegion(grid, i+1, j+1, visited, r, c);
        count += findSubLongestRegion(grid, i-1, j, visited, r, c);
        count += findSubLongestRegion(grid, i, j-1, visited, r, c);
        count += findSubLongestRegion(grid, i-1, j-1, visited, r, c);
        count += findSubLongestRegion(grid, i-1, j+1, visited, r, c);
        count += findSubLongestRegion(grid, i+1, j-1, visited, r, c);

        return count;
    }
}
