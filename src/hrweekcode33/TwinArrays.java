package hrweekcode33;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by predave on 6/12/17.
 */
public class TwinArrays {

    public static class Data implements  Comparable<Data>{
        Integer index;
        Integer value;

        public Data(Integer index, Integer val){
            this.index = index;
            this.value = val;
        }


        @Override
        public int compareTo(Data o) {
            if(this.value > o.value) return 1;
            else if(this.value == o.value) {
                if(this.index < o.index ) return 1;
            }
            return -1;
        }
    }

    public static class Data2 implements  Comparable<Data2>{
        Integer index;
        Integer value;

        public Data2(Integer index, Integer val){
            this.index = index;
            this.value = val;
        }


        @Override
        public int compareTo(Data2 o) {
            if(this.value > o.value) return 1;
            else if(this.value == o.value) {
                if(this.index > o.index ) return 1;
            }
            return -1;
        }
    }
    static int twinArrays(PriorityQueue<Data> ar1, PriorityQueue<Data2> ar2){
        // Complete this function
        int first = 0;
        int second = 0;
            Data data1 = ar1.poll();
            Data2 data2 = ar2.poll();
            Data data12 = ar1.poll();
            Data2 data22 = ar2.poll();

        if(data1.index != data2.index) return data1.value + data2.value;
        else {
            Integer val1 = data1.value + data22.value;
            Integer val2 = data2.value + data12.value;
            return Math.min(val1, val2);
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        PriorityQueue<Data> pq1 = new PriorityQueue<>();
        PriorityQueue<Data2> pq2 = new PriorityQueue<>();
        //int[] ar1 = new int[n];
        for(int ar1_i = 0; ar1_i < n; ar1_i++){
            pq1.add(new Data(ar1_i, in.nextInt()));
            //ar1[ar1_i] = in.nextInt();
        }
        //int[] ar2 = new int[n];
        for(int ar2_i = 0; ar2_i < n; ar2_i++){
            pq2.add(new Data2(ar2_i, in.nextInt()));
            //ar2[ar2_i] = in.nextInt();
        }
        int result = twinArrays(pq1, pq2);
        System.out.println(result);
    }
}
