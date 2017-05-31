import java.util.Stack;

/**
 * Created by predave on 4/2/17.
 */
public class hanoi {

    static int source[] = null;
    static int aux[] = null;
    static int dest[] = null;
    //10 9 8   2en - 1
    // move 8 (disc1) source to dest  ,
    // move 9 (disc 2) source to aux ,
    // move 8 (disc 1) dest to aux .
    //move 10 (disc 3) source to dest
    //move 8 disc1 aux to source
    //move 9 disc 2 aux to dest
    //moe 8 disc1 source to dest


    public static void hanoi(int disc , String source , String aux , String dest){
       if(disc == 1){
          System.out.println("Move " + disc +  " from " + source + " to  " + dest);
       }
        else {
           hanoi(disc - 1, source , dest, aux);
           System.out.println("Move " + disc +  " from " + source + " to  " + dest);
           hanoi(disc - 1, aux , source, dest);
       }
    }

    public static void main(String[] args){
      int n = 3 ;
      source = new int[n];
        aux = new int[n -1];
        dest = new int[n];
        source[0] = 10 ;
        source[1] = 9;

        Stack discs = new Stack();
        discs.push(new Integer("10"));
        discs.push(new Integer("9"));

        hanoi(3 , "S" , "A" , "D" );

        for(int i = 0 ; i < n ; i++){
            System.out.print(dest[i] + " ");
        }
    }
}
