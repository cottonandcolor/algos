package lcode;

/**
 * Input: [1,3,2,2,5,2,3,7]
 Output: 5
 Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 */
public class LongestSS {


    public static void main(String[] args){

        int[] a = {1,3,2,2,5,2,3,7};
        //         1,1,2,3,
        System.out.println(findLongestSS(a));
    }

    private static int findLongestSS(int[] a) {
        int max = 0;
        int LIS[] = new int[a.length];
        for( int i = 0 ; i < a.length; i++) {
            LIS[i] = 1;
        }
        for(int i = 1 ; i < a.length; i++){
            for(int j = 0 ; j < i ; j++) {
                if (a[j] <= a[i] && j < i  && LIS[i] < LIS[j] + 1){
                    LIS[i] = LIS[j] + 1;
                }
            }

        }
        for (int i = 0 ; i < a.length; i++){
            max = Math.max(max,LIS[i]);
        }
        return max;
    }
}
