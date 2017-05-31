package hrwomen;


import java.util.Scanner;

public class Manhattan2 {
    static int[][] nygrid ;
    static int maxtime;
    static int x;
    static int y;
    static int maxCell;
    static int maxCellX;
    static int maxCellY;


    static int maxCandies = 0;
    public static int findMaxCandies() {

        int currentX = 0 ;
        int currentY = 0 ;
        int destX = x ;
        int destY = y;
        int currTime = 0 ;




        maxCandies = nygrid[currentX][currentY];
        int maxrepeat = maxtime - ( x + y - 2 );



        while((currentX < destX || currentY < destY) && currTime < maxtime){

           if(currentY < y - 1 && (maxCellX == currentX || currentX >= x-1 || nygrid[currentX][currentY] > nygrid[currentX+1][currentY] ) ){
               currentY++;
           }
           else {
               currentX++;
           }
            if(currentX == x || currentY == y) {
                maxCandies += maxrepeat*maxCellX;
                return maxCandies;
            }
            //System.out.println(currentX + " " + currentY);
            maxCandies +=  nygrid[currentX][currentY];
            currTime++;

        }
        maxCandies += maxrepeat*maxCellX;
        return maxCandies;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        x = in.nextInt();
        y = in.nextInt();
        maxtime = in.nextInt();
        int maxrepeat = maxtime - ( x + y - 2 );

        nygrid = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int ly = in.nextInt();
                nygrid[i][j] = ly;
                if(ly > maxCell){
                    maxCell = ly;
                    maxCellX = i ;
                    maxCellY = j;
                }

            }

        }

        if(x+y - 1 > maxtime){
            System.out.println("Too late");
            return;
        }
       /* for ( int i = 0 ; i < x ; i++){
            for(int j = 0 ; j < y ; j++){
                System.out.print(nygrid[i][j] + " ");
            }
            System.out.println();
        }*/

        System.out.println(findMaxCandies());
    }

}
