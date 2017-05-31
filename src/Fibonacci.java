/**
 * Created by predave on 4/2/17.
 */
public class Fibonacci {
    private static int arr[] = null;

    public static int fib(int n){
      if(n == 0) {
          arr[n] = 0;
          return 0;
      }
       else if(n == 1) {
          arr[1] = 1;
          return 1;
      }
      else
          return fib(n-1) + fib(n-2);
    }

    public static void fibiter(int n ){
        if( n == 1 ){
            arr[0] = 1;
        }
        else if(n == 2){
            arr[1] = 1;
        }
        else {
            arr[0] = 1;
            arr[1] = 1;
            for(int i = 2 ; i < n ; i++){
                arr[i] = arr[i -1] + arr[i -2];
            }
        }
        for(int i = 0 ; i < n ; i++){
            System.out.print(arr[i]+",");
        }
    }

    public static void main(String[] args){
        // 1 , 1 , 2 , 3 , 5 , 7 , 12
        int n = 10 ;
        arr = new int[n];
        fibiter(n);
    }
}
