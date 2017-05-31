/**
 * Created by predave on 3/31/17.
 */
public class Sorting {
    static int arr[] = {7, 4, 1,9,11,1,3};
    //3,6               7,4,1,3,11,1,9
    static int helperarr[] = null;

    //7 4 3
    public static void quickSort(int lo , int high) {
        int i = lo;
        int j = high;
        int pivot = arr[(lo + high) / 2];

        while (i <= j) {

                while (arr[i] < pivot) {
                    i++;
                }


            if (arr[j] > pivot) {
                j--;
            }

          if (i <= j) {
            swap(i, j);
            i++;
            j--;
          }
        }

        if(i < high) {
            quickSort(i,high);
        }
        if(j > lo){
            quickSort(lo,j);
        }



    }

    public static void bubblesort(){
        for(int i = 0 ; i < arr.length ; i++) {
            for(int j = i + 1; j < arr.length ; j++) {
                if (arr[i] > arr[j])  { swap(i,j); continue;}
            }
        }
    }

    public static void insertionsort(){
            for(int i = 1 ; i < arr.length ; i++){
                int j = i ;
                while(j > 0 && arr[j-1] > arr[j]){
                    swap(j-1 , j);
                    j--;
                }
            }

    }

    public static void mergesort(int start , int end) {

        if( start < end ) {
            int mid = start + end   / 2;
            mergesort(start, mid);
            mergesort(mid + 1, end);
            merge(start, mid , end);
        }

    }

    //7 4 3

    private static void merge(int start, int mid , int end) {
        int i = start;
        int j = mid + 1;
        for(int l = start; l <= end ; l++){
            helperarr[l] = arr[l];
        }
        int k = start;
        while(i <= mid &&  j <= end) {

                if ( helperarr[i] < helperarr[j]) {
                    arr[k++] = helperarr[i];
                    i++;
                }
                else {
                    arr[k++] = helperarr[j];
                    j++;
                }
        }
        while (i <= mid) {
                arr[k++] = helperarr[i];
                i++;
        }


        while (j <= end) {
                arr[k++] = helperarr[j];
                j++;
        }


    }

    private static void swap(int i , int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void copyArray(){
        helperarr = new int[arr.length];
    }

    public static void main(String[] args){
        //bubblesort();
        //insertionsort();
        copyArray();
        //mergesort(0,arr.length - 1);
        quickSort(0,arr.length - 1);
        for(int i= 0 ; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }

    }
}
