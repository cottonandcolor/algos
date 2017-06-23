package fb;

/**
 * Define amazing number as: its value is less than or equal to its index.
 * Given a circular array, find the starting position,
 * such that the total number of amazing numbers in the array is maximized.
 */
public class amazingnum {
    public static void main(String[] args){
        //4 2 8 2 4 5 3  -- 0
        // 1 2 3 4 5 6 7 -- 6
        //9 8 7 6 5 4 3 2 1 --0
        //0 0 0 0 0 0 0 0 0 -- 0
        //1 2 3 4 5 1 2 9 10 11 1 2 3 4 5 6
        // 2 3 1 -- result 1
        int [] a = {1,2,3,4,5,6,7};

    }


    private static int findAmazingNum(int[] a) {
        int n = a.length;
        int[] change = new int[n];

        // find for every element the interval that makes it an amazing number
        for(int i = 0; i < n; i++)
        {
            if(a[i] >= n) continue;  //number greater than n cannot be amazing
            int s = (i + 1) % n;  // 2
            int e = (n + i - a[i]) % n; //5
            change[s] ++;
            if(e + 1 < n) change[e + 1] --;  // decrement to include  highest count eg
                                             ///      1-------6
                                              //        2----5      here 6th inex is decremented as 5 appears in both ranges
        }

        int k = 0;
        int maxk = 0;
        int maxki = 0;
        for(int i = 0; i < n; i++)
        {
            k += change[i];// the preceeding start will be included in the previous start
            if(k > maxk)
            {
                maxki = i;
                maxk = k;
            }
        }
        return maxki;

    }
}
