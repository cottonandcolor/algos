package lcodemock;

/**
 * Created by predave on 7/7/17.
 */
public class DungeonGame {
    public static void main(String[] args){
        DungeonGame dg = new DungeonGame();
        int[][] a = {{0,5}, {-2,-3}};
        System.out.print(dg.calculateMinimumHP(a));
    }
    public int calculateMinimumHP(int[][] d) {
        if (d == null  ) return 0;
        int r = d.length;
        int c = d[0].length;
        if(r == 0 || c == 0) return 0;
        int[][] points = new int[r][c];


        points[r - 1][c - 1] = Math.max(1 - d[r - 1][c - 1], 1);
        for (int i = r - 2; i >= 0; i--) {
            points[i][c - 1] = Math.max(points[i + 1][c - 1] - points[i][c - 1], 1);
        }
        for (int j = c - 2; j >= 0; j--) {
            points[r - 1][j] = Math.max(points[r - 1][j + 1] - d[r - 1][j], 1);
        }
        if(c ==1) {
            for (int i = r - 2; i >= 0; i--) {
                int dn = Math.max(points[i + 1][0] - d[i][0], 1);
                points[i][0] = dn;
            }
        }
        else if(r ==1) {
            for (int j = c - 2; j >= 0; j--) {
                int rt = Math.max(points[0][j + 1] - d[0][j], 1);
                points[0][0] = rt;
            }
        }
        else {
            for (int i = r - 1; i >= 0; i--) {
                for (int j = c - 1; j >= 0; j--) {
                    if (i == r-1 && j == c-1)
                    {
                        points[i][j] = Math.max(1, 1 - d[i][j]);
                    }
                    else if (i == r-1)
                    {
                        points[i][j] = Math.max(1, points[i][j+1] - d[i][j]);
                    }
                    else if (j == c-1)
                    {
                        points[i][j] = Math.max(1, points[i+1][j] - d[i][j]);
                    }
                    else
                    {
                        points[i][j] = Math.max(1, Math.min(points[i+1][j],points[i][j+1]) - d[i][j]);
                    }
                }
            }
        }

        return points[0][0];
    }

}
