package hrrookie;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/**
 * Created by predave on 5/5/17.
 */
public class MaxTorism {
    static Integer[][] route ;
    static Map<Integer,Set<Integer>> nodeMap = new HashMap<Integer, Set<Integer>>();
    static Map<Integer,Boolean> visitedMap = new HashMap<Integer, Boolean>();
    static int maxCities = 1;


    public static class UF {
        public UF(int N){

        }

        public void union(int p, int q){

        }

        public boolean connected(int p , int q){
            return false;
        }

        public int find(int p){
            return 0;
        }

        public int count() {
            return 0;
        }


    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        route = new Integer[m][2];
        for(int route_i=0; route_i < m; route_i++){
            for(int route_j=0; route_j < 2; route_j++){
                route[route_i][route_j] = in.nextInt();
            }
            if(nodeMap.get(Integer.valueOf(route[route_i][0])) == null) {
                Set<Integer> neighbourList = new HashSet<Integer>();
                neighbourList.add(route[route_i][1]);
                nodeMap.put(route[route_i][0], neighbourList);
            }
            else{
                nodeMap.get(Integer.valueOf(route[route_i][0])).add(route[route_i][1]);
            }

            if(nodeMap.get(Integer.valueOf(route[route_i][1])) == null) {
                Set<Integer> neighbourList = new HashSet<Integer>();
                neighbourList.add(route[route_i][0]);
                nodeMap.put(route[route_i][1], neighbourList);
            }
            else{
                nodeMap.get(Integer.valueOf(route[route_i][1])).add(route[route_i][0]);
            }
        }
        // Write Your Code Here

        for(int route_i=0; route_i < m; route_i++) {
            if(visitedMap.get(route_i) == null) {
                int maxc = visitCities(route[route_i][0]);
                if (maxc > maxCities) {
                    maxCities = maxc;
                }
            }
        }

        System.out.println(maxCities);

    }

    public static int  visitCities(Integer node){
        if(visitedMap.get(node) == null){
            maxCities++;
            visitedMap.put(node, true);
        }
        else {
            return maxCities;
        }
        Set<Integer> neighbours = nodeMap.get(node);
        if(neighbours == null)
                return maxCities;
        for(Integer neigbour : neighbours){

                return visitCities(neigbour);
        }
        return maxCities;
    }

}
