import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;



public class maxmod {
    static int[] inputs = null;
    static int n = 0;
    static int k = 0;
    static boolean founda0 = false;
    static boolean foundb0 = false;

    private static int findLarger(int a , int b){
        Set setA = new HashSet();
        Set setB = new HashSet();

        for (int i = 0 ; i < n ; i++){
            if( a != b) {
                if (inputs[i] % k == a ) {
                    setA.add(inputs[i]);
                }
                if (inputs[i] % k == b ) {
                    setB.add(inputs[i]);
                }
                if (inputs[i] % k == 0   ) {
                    foundb0 = true;
                }

            }
            else {
                if (inputs[i] % k == a)
                    founda0 = true;
                if (inputs[i] % k == 0   ) {
                    foundb0 = true;
                }
            }
        }
        int ret =  max(setA.size(), setB.size());
        return ret;
    }

    private static int max(int a , int b){
        if(a > b) return a;
        else return b;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();

        inputs = new int[n];
        for(int a0 = 0; a0 < n; a0++){
            int grade = in.nextInt();
            inputs[a0] = grade;
            if(k == 1 && grade == 1)
            {
                System.out.println("1");
                return;
            }
            // your code goes here
        }

        //Arrays.sort(inputs);
        int[][] allpairs = new int[k/2][2];
        int index = 0;
        for(int i =1 ; i <= k/2 ; i++){

                allpairs[index][0] = i;
                allpairs[index][1] = k-i;
                //System.out.println(allpairs[index][0] + "," + allpairs[index][1]);
                index++;
        }
        int maxval = 0;

        for(int b = 0 ; b < allpairs.length ; b++){
            int val = findLarger(allpairs[b][0] , allpairs[b][1]);
            //System.out.println(val);
            maxval += val;

        }
        if(founda0) maxval++;
        if(foundb0) maxval++;
        System.out.println(maxval);

    }
}