package hrwomen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class VisitManahttan {
    //static int[][] nygrid ;
    static List<Node> landList = new ArrayList<Node>();
    static List<Node> hotelList = new ArrayList<Node>();
    static int Lmedianx = 0;
    static int Lmediany = 0;


    public static class Node {
        int x ;
        int y ;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "x:" + x +"y:" + y ;
        }
    }

    private static int smallestpath = -1;

    private static int findHotel(){
        int nodeindex = 1 ;
        for( Node node : hotelList){
            //System.out.println(node);

            int startx = node.x;
            int starty = node.y;
            int pathcost = findPathCost(startx, starty);
            if(smallestpath == -1) {
                smallestpath = pathcost;
            }
            else if (smallestpath > pathcost){
                nodeindex++;
                smallestpath = pathcost;
            }
        }


        return nodeindex;
    }

    private static int findPathCost(int startx, int starty) {
        int cost = 0;
        for(Node node: landList){
            //System.out.println(node);
            int endx = node.x;
            int endy = node.y;
            cost += Math.abs(startx - endx) + Math.abs(starty - endy) ;
            if(cost >= smallestpath && smallestpath != -1)
                break;
        }
        //cost = Math.abs(startx - Lmedianx) + Math.abs(starty - Lmediany) ;
        return cost;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();
        int nL = in.nextInt();
        int nH = in.nextInt();

        //nygrid = new int[x][y];
        for( int i = 0 ; i < nL ; i++){
            int lx = in.nextInt();
            int ly = in.nextInt();
            Lmedianx += lx;
            Lmediany += ly;

            Node node = new Node(lx,ly);
            landList.add(node);
            //nygrid[lx-1][ly-1] = 2;

            //TODO optimize
          /* for(int k = 0 ; k < y ; k++){
                if(nygrid[lx-1][k] == 0)
                {
                    nygrid[lx-1][k] = 1;
                }
            }
            for(int r = 0 ; r < x ; r++){
                if(nygrid[r][ly-1] == 0)
                {
                    nygrid[r][ly-1] = 1;
                }
            }*/
        }
        Lmedianx = Lmedianx / nL;
        Lmediany = Lmediany / nL;

        for( int i = 0 ; i < nH ; i++){
            int lx = in.nextInt();
            int ly = in.nextInt();
            Node node = new Node(lx,ly);
            hotelList.add(node);
            //nygrid[lx-1][ly-1] = 3;

           /* for(int k = 0 ; k < y ; k++){
                if(nygrid[lx-1][k] == 0)
                {
                    nygrid[lx-1][k] = 1;
                }
            }
            for(int r = 0 ; r < x ; r++){
                if(nygrid[r][ly-1] == 0)
                {
                    nygrid[r][ly-1] = 1;
                }
            }*/
        }

        /*for ( int i = 0 ; i < x ; i++){
            for(int j = 0 ; j < y ; j++){
                System.out.print(nygrid[i][j] + " ");
            }
            System.out.println();
        }*/

        System.out.println(findHotel());

    }
}
