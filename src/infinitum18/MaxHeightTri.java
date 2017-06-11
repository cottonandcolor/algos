package infinitum18;

import java.util.*;


/**
 * Created by predave on 6/9/17.
 */
public class MaxHeightTri {
    static int lowestTriangle(double base, double area){
        // Complete this function
        double h = (2*area)/base;
        if(h%1 !=  0) h++;
        return (int)h;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int base = in.nextInt();
        int area = in.nextInt();
        int height = lowestTriangle(base, area);
        System.out.println(height);
    }
}
