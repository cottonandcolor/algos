//todo peak 2d
public class PeakFinder {
    static int arr[] = {1 , 2 , 3 , 4 , 5, 2, 1};

    public static int peak1d( int i , int j){
       /*
       int m = i+j/2
       if(arr[m]) > arr[m-1] ) && arr[m] > arr[m+1 .. peak found return m
       if(arr[m]) > arr[m-1] ) discard left of m ,m-1
          findPeak(m+1);
          if(arr[m) < arr[m-1] ) discard right of m , m+1
          findPeak(m-1)

        */
        int m = (i + j )/2;
        if((arr[m] > arr[m-1]) && arr[m] > arr[m+1]) {
            return arr[m];
        }
        else if( arr[m]  > arr[m-1]) {
            return peak1d(m , j);
        }
        else {
            return peak1d(i , m-1);
        }

    }

    public static void main(String[] args) {
        System.out.print(peak1d(0,5));

    }
}
