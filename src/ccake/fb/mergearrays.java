package ccake.fb;

/**
 * Created by predave on 6/18/17.
 */
public class mergearrays {

    public static void main(String[] args){
        int[] myArray     = new int[]{3, 4, 6, 10, 11, 15};
        int[] alicesArray = new int[]{1, 5, 8, 12, 14, 19};

        int[] res = mergeArrays(myArray, alicesArray);
        if(res == null) System.out.println("[]");
        else {
            System.out.print("[");
        }
        for(int i = 0; i < res.length; i++){
            System.out.print(res[i] );
            if(i != res.length -1) System.out.print(" ,");
        }
        System.out.print("]");
    }

    private static int[] mergeArrays(int[] myArray, int[] alicesArray) {
        int m = myArray.length;
        int a = alicesArray.length;
        int t = m+a;
        if(t == 0) return null;
        if(m == 0) return alicesArray;
        if(a == 0) return myArray;

        int[] result = new int[t];
        int i=0,j = 0 ;
        int index = 0;

        while(i < m  && j < a ) {
            if (myArray[i] < alicesArray[j ]) {
                    result[index++] = myArray[i++];
            }
            else {
                    result[index++] = alicesArray[j++];
            }

        }
        while(i < m){
            result[index++] = myArray[i];
            i++;
        }
        while(j < a){
            result[index++] = alicesArray[j];
            j++;
        }
        return result;
    }
}
