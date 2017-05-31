package hr2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by predave on 4/29/17.
 */
public class InterectionGraph {


    public static class Point {
        public double x, y;
        int id ;
        int pointid;
        public Point(int id) {
            this.id = id;
        }
        public Point(double x, double y) {
            super();
            this.x = x;
            this.y = y;
        }

        public void setX(double x) {
            this.x = x;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getX() { return x;}

        public double getY() { return y;}

        public void setPointid(int pointid){
            this.pointid = pointid;
        }
    }

    static Map<Integer,Point> nodeMap = new HashMap();
    static List<Segment> edgeList = new ArrayList<Segment>();
    static Point[] pointArr ;


    public static class Segment {

        public final Point start, end;
        public  boolean isVertical;
        public  double slope, intercept;

        public Segment(Point start, Point end) {
            this.start = start;
            this.end = end;
            //set isVertical, which indicates whether this Line
            //is vertical or not on the coordinate plane
            /*if (start.x == end.x)
                isVertical = true;
            else
                isVertical = false;

            //set slope and intercept
            if (!isVertical){
                slope = (start.y - end.y) / (start.x - end.x);
                intercept = (end.x * start.y - start.x * end.y ) /(start.x - end.x);
            }
            else {
                slope = Double.MAX_VALUE;
                intercept = - Double.MAX_VALUE;
            }*/

        }

        public Point getStart() { return start;}

        public Point getEnd() { return end; }

        public void computeProperties(){
            //set isVertical, which indicates whether this Line
            //is vertical or not on the coordinate plane
            if (start.x == end.x)
                isVertical = true;
            else
                isVertical = false;

            //set slope and intercept
            if (!isVertical){
                slope = (start.y - end.y) / (start.x - end.x);
                intercept = (end.x * start.y - start.x * end.y ) /(start.x - end.x);
            }
            else {
                slope = Double.MAX_VALUE;
                intercept = - Double.MAX_VALUE;
            }

        }
    }

    public static class IntersectionCheceker {

        public final Segment segment1, segment2;
        private Boolean hasIntersection;

        public IntersectionCheceker(Segment segment1, Segment segment2) {
            this.segment1 = segment1;
            this.segment2 = segment2;
        }

        public IntersectionCheceker(double x1, double y1, double x2, double y2,
                                    double x3, double y3, double x4, double y4) {
            Point start1 = new Point(x1, y1);
            Point end1 = new Point(x2, y2);
            Point start2 = new Point(x3, y3);
            Point end2 = new Point(x4, y4);
            this.segment1 = new Segment(start1, end1);
            this.segment2 = new Segment(start2, end2);
        }

        public boolean hasIntersection() {
            if (hasIntersection != null)
                return hasIntersection;

            if (segment1.isVertical) {
                if ((segment2.start.x - segment1.start.x) * (segment2.end.x - segment1.start.x) > 0)
                    hasIntersection = false;
                else {
                    double fx_at_segment1startx = segment1.slope * segment1.start.x + segment1.intercept;
                    double smaller, larger;
                    if (segment1.start.x < segment1.end.x) {
                        smaller = segment1.start.x;
                        larger = segment1.end.x;
                    } else {
                        larger = segment1.start.x;
                        smaller = segment1.end.x;
                    }
                    if (smaller <= fx_at_segment1startx && fx_at_segment1startx <= larger)
                        hasIntersection = true;
                    else
                        hasIntersection = false;
                }
            } else if (segment2.isVertical) {
                hasIntersection = new IntersectionCheceker(segment2, segment1).hasIntersection();
            } else { //both segment1 and segment2 are not vertical
                if (segment1.slope == segment2.slope)
                    hasIntersection = false;
                else {
                    double x1 = segment1.start.x;
                    double y1 = segment1.start.y;
                    double x2 = segment1.end.x;
                    double y2 = segment1.end.y;
                    double x3 = segment2.start.x;
                    double y3 = segment2.start.y;
                    double x4 = segment2.end.x;
                    double y4 = segment2.end.y;
                    double x = ((x4 * y3 - y4 * x3) / (x4 - x3) - (x2 * y1 - y2 * x1) / (x2 - x1))
                            / ((y2 - y1) / (x2 - x1) - (y4 - y3) / (x4 - x3));

                    double smaller, larger;
                    if (x1 < x2) {
                        smaller = x1;
                        larger = x2;
                    } else {
                        smaller = x2;
                        larger = x1;
                    }
                    if (smaller <= x && x <= larger)
                        hasIntersection = true;
                    else
                        hasIntersection = false;
                }
            }
            return hasIntersection;
        }
    }

        public static void main(String[] args) {
            // e1 e2 e3
            //

            IntersectionCheceker checker1 = new IntersectionCheceker(6, 2, 3, 6, 7, 5, 3, 3);
            boolean hi1 = checker1.hasIntersection();
            System.out.println("IntersectionCheceker.main() 64 hi1 = " + hi1);

            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            for(int i = 0 ; i < n ; i++){
                nodeMap.put(Integer.valueOf(i+1) , new Point(i+1));
            }
            for(int a0 = 0; a0 < n-1; a0++){
                // An edge connects nodes 'u' and 'v':
                int u = in.nextInt();
                int v = in.nextInt();
                edgeList.add(new Segment(nodeMap.get(Integer.valueOf(u)) , nodeMap.get(Integer.valueOf(v))));
                // Write Your Code Here
            }
            pointArr = new Point[n];
            for(int a0 = 0; a0 < n; a0++){
                // Cartesian coordinate:
                int x = in.nextInt();
                int y = in.nextInt();
                // Write Your Code Here
                pointArr[a0] = new Point(x, y);
            }

            for(int i = 0 ; i < edgeList.size() ; i++){
                for(int j = 0 ; j < pointArr.length ; j++){
                    Point start = edgeList.get(i).getStart();
                    start.setPointid(j);
                    start.setX(pointArr[j].getX());
                    start.setY(pointArr[j].getY());

                    Point end = edgeList.get(i).getEnd();
                    end.setPointid(j+1);
                    end.setX(pointArr[j+1].getX());
                    end.setY(pointArr[j+1].getY());
                }
            }

        }
}
