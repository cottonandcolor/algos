package lcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by predave on 5/21/17.
 */
public class ValidSqaure {

    public static void main(String[] args){
        int[] p1 = {1, 0};
        int[] p2 = {-1,0};
        int[] p3 = {0, 1};
        int[] p4 = {0,-1};
        //String s = isValidSquare(p1,p2,p3,p4) ? "True" : "False";
        System.out.println((new ValidSqaure()).isValidSquare(p1,p2,p3,p4) ? "True" : "False");
    }

    private static int len = Integer.MIN_VALUE;
    private static int numIntersects = 0;

    private static class Point {
        int x;
        int y;

        public Point(int x, int y ){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object p){
            if(this.x == ((Point)p).x && this.y == ((Point)p).y) return true;
            return false;
        }

        @Override
        public int hashCode(){
            int hash = 7;
            hash = 71 * hash + this.x;
            hash = 71 * hash + this.y;
            return hash;
        }

        boolean isIntersectX(Point p){
            return Math.abs(this.x) == Math.abs(p.x) ;
        }

        boolean isIntersectY(Point p){
            return Math.abs(this.y) == Math.abs(p.y) ;
        }
    }
    public  boolean isValidSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        //check 4 distinct points
        Point P1 = new Point(p1[0], p1[1]);
        Point P2 = new Point(p2[0], p2[1]);
        Point P3 = new Point(p3[0], p3[1]);
        Point P4 = new Point(p4[0], p4[1]);

        Set<Point> s = new HashSet<Point>();
        s.add(P1);
        s.add(P2);
        s.add(P3);
        s.add(P4);
        if(s.size() != 4 ) return false;

        if(!(P1.isIntersectX(P2) || P1.isIntersectX(P3) || P1.isIntersectX(P4) || P2.isIntersectX(P3) || P2.isIntersectX(P4) || P3.isIntersectX(P4)))
        {
            return false;
        }
        if(!(P1.isIntersectY(P2) || P1.isIntersectY(P3) || P1.isIntersectY(P4) || P2.isIntersectY(P3) || P2.isIntersectY(P4) || P3.isIntersectY(P4)))
        {
            return false;
        }

        if(!Check(p1, p2 )){
            return false;
        }
        if(!Check(p1, p3 )){
            return false;
        }
        if(!Check(p1, p4 )){
            return false;
        }
        if(!Check(p2, p3 )){
            return false;
        }
        if(!Check(p2, p4 )){
            return false;
        }
        if(!Check(p3, p4 )){
            return false;
        }
        if(numIntersects != 4)
            return false;
        return true;
    }

    private static boolean Check(int[] p1 , int[] p2){
        if(Math.abs(p1[0]) == Math.abs(p2[0]) ) {
            numIntersects++;
            if(len == Integer.MIN_VALUE) {
                len = Math.abs(p1[1] - p2[1]);
            }
            else if(len !=  Math.abs(p1[1] - p2[1])){
                return false;
            }

        } else if(Math.abs(p1[1]) == Math.abs(p2[1]) ) {
            numIntersects++;
            if(len == Integer.MIN_VALUE) {
                len = Math.abs(p1[0] - p2[0]);
            }
            else if(len !=  Math.abs(p1[0] - p2[0])){
                return false;
            }

        }

        return true;
    }
}
