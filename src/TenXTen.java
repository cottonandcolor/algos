/**
 * Created by predave on 4/27/17.
 */
public class TenXTen {


    public static void main(String[] args){

        int[][] tenten = new int[10][10];

        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                if(j < i) {
                    tenten[i][j] = tenten[j][i];
                }
                else {
                    tenten[i][j] = (i + 1) * (j + 1);
                }
            }
        }

        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                System.out.println((i+1) + " * " + (j+1) + " = " + tenten[i][j]);
            }
        }
    }
}
