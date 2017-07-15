package lcodemock;

import java.util.Arrays;

/**
 * Created by predave on 7/4/17.
 */
public class rotatearray {
    public static void main(String[] args){
        int[][] a = { {1,2}, {3,4}};
        new rotatearray().rotate(a);
        System.out.print(a);
    }

    public void rotate(int[][] arr) {
        int n = arr.length;
        int[][] ret = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                ret[i][j] = arr[n - j - 1][ i];
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = ret[i][j];
            }
        }

        /*for (int i=0; i<n; i++) //transpose
            for (int j=i; j<n; j++) {
                int tmp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = tmp;

            }*/

        /*for (int i=0; i<n; i++) //reverse row ( do reverse row ) for clockwise
            for (int j=0,  k=n-1; j<k; j++,k--) {
                int tmp = arr[j][i];
                arr[j][i] = arr[k][i];
                arr[k][i] = tmp;

            }*/

        /*for (int x = 0; x < n / 2; x++)
        {
            for (int y = x; y < n-x-1; y++)
            {
                int temp = matrix[x][y];

                matrix[x][y] = matrix[y][n-1-x];

                matrix[y][n-1-x] = matrix[n-1-x][n-1-y];

                matrix[n-1-x][n-1-y] = matrix[n-1-y][x];

                matrix[n-1-y][x] = temp;
            }
        }*/
    }


}
