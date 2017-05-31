import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by predave on 3/19/17.
 */
public class StackMax {
    private static int currentCost = 0;
    private static int n = 0;
    private static int k = 0;
    static Pole[] poles = null;

    public static class Pole implements Comparable{

        int weight ;
        int distance;

        public Pole(int distance, int weight) {
            this.weight = weight;
            this.distance = distance;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(Object o) {
            if(this.getDistance() < ((Pole)o).getDistance())
                return -1;
            if(this.getDistance() > ((Pole)o).getDistance())
                return 1;
            else return 0;
        }
    }
    public static class PoleStack implements Comparable {
        private int[] stacks;
        private int totalCost = 0;

        public int getTotalCost(){
            return totalCost;
        }

        public PoleStack(int[] stacks) {
            this.stacks = stacks;
            findTotalCost();
        }

        private void findTotalCost() {
            for(int i = 0 ; i < stacks.length ;  i++){
                int start_pos = stacks[i] ;
                int end_pos = i+1 >= stacks.length? n  : stacks[i+1];
                int index = start_pos  + 1;
                while (index < end_pos) {
                    totalCost += (poles[index].distance - poles[start_pos].getDistance()) * poles[index].getWeight();
                    index++;
                }

            }
        }


        @Override
        public int compareTo(Object o) {
            if (((PoleStack) this).getTotalCost() < ((PoleStack) o).getTotalCost()) {
                return -1;
            } else if (((PoleStack) this).getTotalCost() > ((PoleStack) o).getTotalCost()) {
                return 1;
            }
                return 0;
        }

        public String toString() {
            String str =  String.valueOf(totalCost) +  " stacks:";
            for (int i = 0; i < stacks.length; i++){
                str += stacks[i];
            }
            return str;
        }

    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        poles = new Pole[n];

        for(int a0 = 0; a0 < n; a0++){
            int x_i = in.nextInt();
            int w_i = in.nextInt();
            poles[a0] = new Pole(x_i , w_i);
        }

        Arrays.sort(poles);


        int totalCost = 0;
        if( k == 1){
            for(int i = 1 ; i < n ; i++){
                totalCost += (poles[i].distance - poles[0].getDistance()) * poles[i].getWeight();
            }
            System.out.println(totalCost);
        }
        else {
            PriorityQueue<PoleStack> pq = new PriorityQueue<PoleStack>();
            int maxNumPolesinStack = n - k + 1;
            int current_start_index = 1;
            int[] starts = new int[k];
            int count = 0;
            starts[0] = 0 ;
            starts[1] = 1;
            pq.offer(new PoleStack(starts));
            System.out.println(pq.peek());
            int[] starts1 = new int[k];
            starts1[0] = 0;
            starts1[1] = 2;
            pq.offer(new PoleStack(starts1));
            System.out.println(pq.peek());

            int[] starts2 = new int[k];
            starts2[0] = 0;
            starts2[1] = 3;
            pq.offer(new PoleStack(starts2));
            System.out.println(pq.peek());

            /*6 2
              10 15
              12 17 -- 34
              16 18 -- 108
              18 13 -- 214 , 26
              30 10 -- 200 , 140
              32 1 -- 22  , 16  ,
              n - k  +1 ; // max in each set */
            /*
            int maxNumPolesinStack = n - k + 1;
            int start_point = 0;
            totalCost = findTotalCost(maxNumPolesinStack, start_point);
            while(maxNumPolesinStack > 1){
                maxNumPolesinStack--;
                totalCost = findTotalCost(maxNumPolesinStack, start_point);
            }
            System.out.println(currentCost);*/
        }



    }
}
