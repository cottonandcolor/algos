package hrcrackc;

/**
 * Created by predave on 5/11/17.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class RunningMedian {

        private static PriorityQueue<Integer> minQueue;
        private static PriorityQueue<Integer> maxQueue;


        private static void addItem(int[] a, int i){
            //first max then min
            int maxsize = maxQueue.size();
            int minsize = minQueue.size();
            PriorityQueue<Integer> targetQ = minsize < maxsize ? minQueue : maxQueue;
            targetQ.add(i);
            if(maxsize != 0 && minsize != 0 && minQueue.peek() > maxQueue.peek()){
                int minval = minQueue.remove();
                int maxval = maxQueue.remove();
                maxQueue.add(minval);
                minQueue.add(maxval);
            }
            double median;
            maxsize = maxQueue.size();
            minsize = minQueue.size();

            if(maxsize + minsize != 1 && (maxsize + minsize) % 2 == 0){
                median = ((double)minQueue.peek() + maxQueue.peek())/2;
            } else {
                median = maxsize > minsize ? maxQueue.peek() : minQueue.peek();
            }
            System.out.println(median);
        }

        public static void main(String[] args) {
            Comparator<Integer> minComp = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if(o1.equals(o2)) return 0;
                    return o1 < o2 ? 1 : -1;
                }
            };

            Comparator<Integer> maxComp = new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if(o1.equals(o2)) return 0;
                    return o1 > o2 ? 1 : -1;
                }
            };

            minQueue = new PriorityQueue<Integer>(minComp);
            maxQueue = new PriorityQueue<Integer>(maxComp);

            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int[] a = new int[n];
            for(int a_i=0; a_i < n; a_i++){
                a[a_i] = in.nextInt();
                addItem(a, a[a_i]);
            }


        }

}
